package hello.services;

import hello.exceptions.NotExistException;
import hello.pojos.Cards;
import hello.pojos.Rates;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class RateService {

    private static final String COMMA = ",";
    private final Map mapInteresActivo = new HashMap<String,Float>();
    private final Map mapTarjetas = new HashMap<String,Float>();
    private final String PATH_TIPOS = "src/main/resources/tipos.txt";
    private final String PATH_TARJETAS = "src/main/resources/tarjetas.txt";


    public RateService() throws FileNotFoundException {

        try {
            List<Rates> list = processInputFile(PATH_TIPOS);
            list.stream().forEach(e-> mapInteresActivo.put(e.getYear_month(),e.getRate()));
            System.out.println("There are " + mapInteresActivo.values().size() + " elements on TAE map. " + list.size() + " elements on list. ");
            //System.out.println("Listing TAE file.");
            //mapInteresActivo.forEach((k,v)-> System.out.println(k.toString() + " " + v.toString()));

            List<Cards> listCards = processInputCardsFile(PATH_TARJETAS);
            listCards.stream().forEach(e-> mapTarjetas.put(e.getCard_name(),e.getRate()));
            System.out.println("There are " + mapTarjetas.values().size() + " elements on CARD map. " + listCards.size() + " elements on list. ");
            //System.out.println("Listing cards!");
            //mapTarjetas.forEach((k,v)-> System.out.println(k.toString() + " " + v.toString()));
            System.out.println("Rate/TAE service Initialized!");
        }catch (FileNotFoundException e) {
            //Nothing to do...
            System.out.println(e.getMessage());
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
            System.out.println("taeBank: " + taeBank);
            taeByCard = getTAEByCard(card);
            System.out.println("taeByCard: " + taeByCard);
            calculated = taeByCard > taeBank * 2;
            return calculated;
        }catch(Exception e){
            String someMsg = "year: " + year + " month: " + month + " card: " + card + " taeBank: " + taeBank + " taeByCard: " + taeByCard + ". Probablemente el año, el mes o la tarjeta NO están en el sistema." ;
            System.out.println(someMsg);
            throw new NotExistException(someMsg);
        }
    }
    private final static List<Rates> processInputFile(String inputFilePath) throws FileNotFoundException {

        List<Rates> inputList = new ArrayList<Rates>();
        try {
            File inputF = new File(inputFilePath);
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

    private final static List<Cards> processInputCardsFile(String inputFilePath) throws FileNotFoundException {

        List<Cards> inputList = new ArrayList<Cards>();
        try {
            File inputF = new File(inputFilePath);
            InputStream inputFS = new FileInputStream(inputF);
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
