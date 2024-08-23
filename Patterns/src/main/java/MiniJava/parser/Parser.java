package MiniJava.parser;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Stack;

import MiniJava.Log.Log;
import MiniJava.codeGenerator.CodeGenerator;
import MiniJava.errorHandler.ErrorHandler;
import MiniJava.scanner.lexicalAnalyzer;
import MiniJava.scanner.token.Token;

public class Parser {
    private ArrayList<Rule> rules;
    private Stack<Integer> parsStack;
    private ParseTable parseTable;
    private lexicalAnalyzer lexicalAnalyzer;
    private CodeGenerator cg;

    public Parser() {
        parsStack = new Stack<Integer>();
        getParsStack().push(0);
        try {
            parseTable = new ParseTable(Files.readAllLines(Paths.get("src/main/resources/parseTable")).get(0));
        } catch (Exception e) {
            e.printStackTrace();
        }
        rules = new ArrayList<Rule>();
        try {
            for (String stringRule : Files.readAllLines(Paths.get("src/main/resources/Rules"))) {
                rules.add(new Rule(stringRule));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        cg = new CodeGenerator();
    }
    public lexicalAnalyzer getLexicalAnalyzer() {
        return lexicalAnalyzer;
    }

    // Setter method for lexicalAnalyzer
    public void setLexicalAnalyzer(lexicalAnalyzer lexicalAnalyzer) {
        this.lexicalAnalyzer = lexicalAnalyzer;
    }

    public ArrayList<Rule> getRules() {
        return rules;
    }

    public void setRules(ArrayList<Rule> rules) {
        this.rules = rules;
    }

    public Stack<Integer> getParsStack() {
        return parsStack;
    }

    public void setParsStack(Stack<Integer> parsStack) {
        this.parsStack = parsStack;
    }

    public ParseTable getParseTable() {
        return parseTable;
    }

    public void setParseTable(ParseTable parseTable) {
        this.parseTable = parseTable;
    }

    public CodeGenerator getCg() {
        return cg;
    }

    public void setCg(CodeGenerator cg) {
        this.cg = cg;
    }


    public void startParse(java.util.Scanner sc) {
        lexicalAnalyzer = new lexicalAnalyzer(sc);
        Token lookAhead = getLexicalAnalyzer().getNextToken();
        boolean finish = false;
        Action currentAction;
        while (!finish) {
            try {
                Log.print(/*"lookahead : "+*/ lookAhead.toString() + "\t" + getParsStack().peek());
//                Log.print("state : "+ parsStack.peek());
                currentAction = getParseTable().getActionTable(getParsStack().peek(), lookAhead);
                Log.print(currentAction.toString());
                //Log.print("");

                switch (currentAction.action) {
                    case shift:
                        getParsStack().push(currentAction.number);
                        lookAhead = getLexicalAnalyzer().getNextToken();

                        break;
                    case reduce:
                        Rule rule = getRules().get(currentAction.number);
                        for (int i = 0; i < rule.RHS.size(); i++) {
                            getParsStack().pop();
                        }

                        Log.print(/*"state : " +*/ getParsStack().peek() + "\t" + rule.LHS);
//                        Log.print("LHS : "+rule.LHS);
                        getParsStack().push(getParseTable().getGotoTable(getParsStack().peek(), rule.LHS));
                        Log.print(/*"new State : " + */getParsStack().peek() + "");
//                        Log.print("");
                        try {
                            getCg().semanticFunction(rule.semanticAction, lookAhead);
                        } catch (Exception e) {
                            Log.print("Code Genetator Error");
                        }
                        break;
                    case accept:
                        finish = true;
                        break;
                }
                Log.print("");
            } catch (Exception ignored) {
                ignored.printStackTrace();
//                boolean find = false;
//                for (NonTerminal t : NonTerminal.values()) {
//                    if (parseTable.getGotoTable(parsStack.peek(), t) != -1) {
//                        find = true;
//                        parsStack.push(parseTable.getGotoTable(parsStack.peek(), t));
//                        StringBuilder tokenFollow = new StringBuilder();
//                        tokenFollow.append(String.format("|(?<%s>%s)", t.name(), t.pattern));
//                        Matcher matcher = Pattern.compile(tokenFollow.substring(1)).matcher(lookAhead.toString());
//                        while (!matcher.find()) {
//                            lookAhead = lexicalAnalyzer.getNextToken();
//                        }
//                    }
//                }
//                if (!find)
//                    parsStack.pop();
            }
        }
        if (!ErrorHandler.hasError()) getCg().printMemory();
    }
}
