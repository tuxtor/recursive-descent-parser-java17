package com.vorozco;

import picocli.CommandLine;

import java.io.BufferedReader;
import java.io.File;
import java.io.StringReader;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.Callable;

@CommandLine.Command(name = "lexer", mixinStandardHelpOptions = true, version="0.0.1",
        description = "mi-primer-lexer-jamg1906")
public class App implements Callable<Integer>
{
    @CommandLine.Option(names = {"-f", "--file"}, description = "File to read", required = false, defaultValue = "test.txt")
    private File file;

    private List<Token> tokens = new ArrayList<>();

    @Override
    public Integer call() throws Exception {

        BufferedReader bfr = Files.newBufferedReader(file.toPath());
        MyLexer lexer = new MyLexer(bfr);
        Token token = lexer.yylex();
        while ( token.getTokenType()!= TokenConstants.EOF){
            tokens.add(token);
            try{
                System.out.println(token);
                token = lexer.yylex();
            }catch (Exception e){
                System.out.println(e.getMessage());
                token = lexer.yylex();
            }
        }
        RDP rdp = new RDP(tokens);
        boolean valido = rdp.parse();
        System.out.println("Valido: " + valido);
        return 0;
    }




    public static void main( String[] args )
    {
        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
    }

}
