package hello;

import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

    private static String PATH_TO_TIPOS;
    private static String PATH_TO_CARDS;

    public static void main(String[] args) {

        if (args.length < 2) showUsage();
        for(int i =0;i< args.length;i++) {
            System.out.println("arg[" + i + "]: " + args[i]);
        }
        PATH_TO_TIPOS=args[0];
        PATH_TO_CARDS=args[1];
        SpringApplication.run(Application.class, args);
    }

    private static void showUsage(){
        System.out.println("java -jar PATH_TO_JAR_File PATH_TO_TIPOS.FILE PATH_TO_CARDS_FILE");
        System.exit(-1);
    }
    public static String getPathToTipos() {
        return PATH_TO_TIPOS;
    }

    public static String getPathToCards() {
        return PATH_TO_CARDS;
    }

    public static void setPathToTipos(String pathToTipos) {
        PATH_TO_TIPOS = pathToTipos;
    }

    public static void setPathToCards(String pathToCards) {
        PATH_TO_CARDS = pathToCards;
    }
}
