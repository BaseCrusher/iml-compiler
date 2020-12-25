 package ch.fhnw.cpib.compiler.parser;

import ch.fhnw.cpib.compiler.error.GrammarError;
import ch.fhnw.cpib.compiler.parser.nts.Program;
import ch.fhnw.cpib.compiler.tokens.IToken;
import ch.fhnw.cpib.compiler.tokens.ITokenList;
import ch.fhnw.cpib.compiler.tokens.enums.ITerminal;

import java.util.Arrays;

public class Parser implements IParser {

    private static ITokenList tokenList;
    private static IToken currentToken;
    public static IToken consume(ITerminal... expectedTerminals) throws GrammarError {
        
        ITerminal terminal = currentToken.getTerminal();

        for(ITerminal expectedTerminal : expectedTerminals) {
            if (terminal == expectedTerminal) {
                IToken consumedToken = currentToken;
                currentToken = tokenList.nextToken();
                return consumedToken;
            }
        }
        
        throw new Error("Error wrong consume.");
    }

    private IConcreteTree concreteTree;

    public Parser(ITokenList tokenList) throws GrammarError {
        if (!tokenList.hasSentinel()) {
            throw new GrammarError("Token list has no Sentinel");
        }
        Parser.tokenList = tokenList;
        concreteTree = new ConcreteTree();
    }

    @Override
    public IConcreteTree parse() throws GrammarError {
        currentToken = tokenList.nextToken();
        
        Program program = new Program();

        concreteTree.setProgram(program);

        return concreteTree;
    }
    
    public static IToken getCurrentToken() {
        return currentToken;
    }

}
