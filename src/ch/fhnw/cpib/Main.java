package ch.fhnw.cpib;

import ch.fhnw.cpib.compiler.Scanner;
import ch.fhnw.cpib.compiler.codeGenerator.CodeGenerator;
import ch.fhnw.cpib.compiler.error.CodeGenError;
import ch.fhnw.cpib.compiler.error.GrammarError;
import ch.fhnw.cpib.compiler.error.LexicalError;
import ch.fhnw.cpib.compiler.parser.AbstractTree;
import ch.fhnw.cpib.compiler.parser.IAbstractTree;
import ch.fhnw.cpib.compiler.parser.IConcreteTree;
import ch.fhnw.cpib.compiler.parser.Parser;
import ch.fhnw.cpib.compiler.tokens.ITokenList;
import ch.fhnw.cpib.compiler.vm.ICodeArray;
import ch.fhnw.cpib.compiler.vm.IVirtualMachine;
import ch.fhnw.cpib.compiler.vm.VirtualMachine;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static ch.fhnw.cpib.compiler.codeGenerator.CodeGenerator.codeArray;

public class Main {

    public static void main(String[] args) throws IOException {
        Path p = Path.of("example_programs/Add17.iml");
        StringBuilder sb = new StringBuilder();
        Files.lines(p).forEach((String s) -> { sb.append(s).append("\n"); } );
        String content = sb.toString();

        Scanner s = new Scanner();
        try {
            ITokenList bla = s.scan(content);

            Parser parser = new Parser(bla);
            IConcreteTree concreteTree = parser.parse();
            IAbstractTree abstractTree = new AbstractTree(concreteTree);
            System.out.println(concreteTree);
            System.out.println(abstractTree);

            CodeGenerator codeGenerator = new CodeGenerator();
            try {
                codeGenerator.code(abstractTree);
            } catch (ICodeArray.CodeTooSmallError | CodeGenError codeTooSmallError) {
                codeTooSmallError.printStackTrace();
            }
            try {
                VirtualMachine virtualMachine = new VirtualMachine(codeArray, 1024);
            } catch (IVirtualMachine.ExecutionError executionError) {
                executionError.printStackTrace();
            }

        }
        catch (LexicalError | GrammarError ex){
            System.out.println(ex);
        }
    }
}
