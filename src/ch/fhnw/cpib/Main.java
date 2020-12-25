package ch.fhnw.cpib;

import ch.fhnw.cpib.compiler.Scanner;
import ch.fhnw.cpib.compiler.error.GrammarError;
import ch.fhnw.cpib.compiler.error.LexicalError;
import ch.fhnw.cpib.compiler.parser.IConcreteTree;
import ch.fhnw.cpib.compiler.parser.Parser;
import ch.fhnw.cpib.compiler.tokens.ITokenList;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws IOException {
        Path p = Path.of("example_programs/Euclid.iml");
        StringBuilder sb = new StringBuilder();
        Files.lines(p).forEach((String s) -> { sb.append(s).append("\n"); } );
        String content = sb.toString();

        Scanner s = new Scanner();
        try {
            ITokenList bla = s.scan(content);

            System.out.println(bla);

            Parser parser = new Parser(bla);
            IConcreteTree concreteTree = parser.parse();
            System.out.println(concreteTree);
        }
        catch (LexicalError | GrammarError ex){
            System.out.println(ex);
        }
    }
}
