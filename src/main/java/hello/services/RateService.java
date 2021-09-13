package hello.services;

import hello.Application;
import hello.exceptions.NotExistException;
import hello.pojos.Cards;
import hello.pojos.Rates;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/*
This class is crying out to get all that private functionality out into an abstract class to just show the public methods. 
https://stackoverflow.com/questions/23882308/private-abstract-classes-in-java
*/
@Service
public class RateService {

    private static final Logger logger = LoggerFactory.getLogger(RateService.class);

    private static final String COMMA = ",";
    private final Map mapInteresActivo = new HashMap<String,Float>();
    private final Map mapTarjetas = new HashMap<String,Float>();

    // internal files does not work within a docker container. Using these files for testing purposes...
    private  static String PATH_TIPOS = "src/test/resources/tipos.txt";
    private  static String PATH_TARJETAS = "src/test/resources/tarjetas.txt";


    public RateService() throws FileNotFoundException {

        try {
            List<Rates> list = processInputFile();
            list.stream().forEach(e-> mapInteresActivo.put(e.getYear_month(),e.getRate()));
            logger.info("There are " + mapInteresActivo.values().size() + " elements on TAE map. " + list.size() + " elements on list. ");
            
            List<Cards> listCards = processInputCardsFile();
            listCards.stream().forEach(e-> mapTarjetas.put(e.getCard_name(),e.getRate()));
            logger.info("There are " + mapTarjetas.values().size() + " elements on CARD map. " + listCards.size() + " elements on list. ");
            logger.info("Rate/TAE service Initialized!");
        }catch (FileNotFoundException e) {
            //Nothing to do... CRASH if files are not located.
            logger.info(e.getMessage());
        }
    }

    private Float getTAE (String year,String month) throws NotExistException {

        final String year_month = year + "_" + month;
        try{
            return (Float) mapInteresActivo.get(year_month);
        }catch (Exception e){
            throw new NotExistException("Not registered " + year_month);
        }

    }

    private Float getTAEByCard(String card) throws NotExistException{
        try {
            return (Float) mapTarjetas.get(card);
        }catch (Exception e){
            throw new NotExistException("Not registered " + card);
        }
    }

    public boolean isALoanSharkCard(String year,String month,String card) throws NotExistException{
        Float taeBank = null;
        Float taeByCard = null;
        boolean calculated=false;

        try {
            taeBank = getTAE(year, month);
            logger.info("taeBank: " + taeBank);
            taeByCard = getTAEByCard(card);
            logger.info("taeByCard: " + taeByCard);
            calculated = taeByCard > taeBank * 2;
            return calculated;
        }catch(Exception e){
            String someMsg = "year: " + year + " month: " + month + " card: " + card + " taeBank: " + taeBank + " taeByCard: " + taeByCard + ". Probablemente el año, el mes o la tarjeta NO están en el sistema." ;
            logger.info(someMsg);
            throw new NotExistException(someMsg);
        }
    }
    private final static List<Rates> processInputFile() throws FileNotFoundException {

        String inputFilePath= Application.getPathToTipos();
        if (inputFilePath == null) inputFilePath = PATH_TIPOS;
        logger.info("Using inputFilePath: " + inputFilePath);
        List<Rates> inputList = new ArrayList<Rates>();
        try {
            File inputF = new File(inputFilePath);
            //InputStream inputFS = RateService.class.getClassLoader().getResourceAsStream(inputFilePath);
            InputStream inputFS = new FileInputStream(inputF);
            BufferedReader br = new BufferedReader(new InputStreamReader(inputFS));
            // skip the header of the csv
            inputList = br.lines().skip(1).map(mapToItem).collect(Collectors.toList());
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return inputList;
    }

    private final static List<Cards> processInputCardsFile() throws FileNotFoundException {
        String inputFilePath=Application.getPathToCards();
        if (inputFilePath == null) inputFilePath = PATH_TARJETAS;

        logger.info("Using inputFilePath: " + inputFilePath);
        List<Cards> inputList = new ArrayList<Cards>();
        try {
            File inputF = new File(inputFilePath);
            InputStream inputFS = new FileInputStream(inputF);
            //InputStream inputFS =RateService.class.getClassLoader().getResourceAsStream(inputFilePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(inputFS));
            // skip the header of the csv
            inputList = br.lines().skip(1).map(mapToCard).collect(Collectors.toList());
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return inputList;
    }


    private static final Function<String, Rates> mapToItem = (line) -> {
        String[] p = line.split(COMMA);// a CSV has comma separated lines
        Rates item = new Rates(p[0] + "_" + p[1],Float.parseFloat(p[2]));
        return item;

    };

    private static final Function<String, Cards> mapToCard = (line) -> {
        String[] p = line.split(COMMA);// a CSV has comma separated lines
        Cards item = new Cards(p[0],Float.parseFloat(p[1]));
        return item;

    };

}
