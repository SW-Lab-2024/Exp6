package MiniJava.parser;

import java.util.Scanner;

public class ParsingFacade {
    private Parser parser;

    public ParsingFacade() {
        parser = new Parser();
    }

    public void parse(Scanner scanner) {
        parser.startParse(scanner);
    }
}
