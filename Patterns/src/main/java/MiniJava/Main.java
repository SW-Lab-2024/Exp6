package MiniJava;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import MiniJava.errorHandler.ErrorHandler;
import MiniJava.parser.ParsingFacade;


public class Main {
    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(new File("src/main/resources/code"));
            ParsingFacade parsingFacade = new ParsingFacade();
            // start parsing
            parsingFacade.parse(scanner);
            scanner.close();
        } catch (FileNotFoundException e) {
            ErrorHandler.printError(e.getMessage());
        }
    }
}
