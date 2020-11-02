package ch.fhnw.cpib.compiler.parser;

import ch.fhnw.cpib.compiler.error.GrammarError;
import ch.fhnw.cpib.compiler.parser.nts.Program;
import ch.fhnw.cpib.compiler.tokens.IToken;
import ch.fhnw.cpib.compiler.tokens.ITokenList;
import ch.fhnw.cpib.compiler.tokens.enums.ITerminal;
import ch.fhnw.cpib.compiler.tokens.enums.KeywordTerminals;

public class Parser implements IParser {

    private static ITokenList tokenList;
    public static IToken consume(ITerminal expectedTerminal) throws GrammarError {
        IToken token = tokenList.nextToken();
        ITerminal terminal = token.getTerminal();
        if (terminal != expectedTerminal) throw new GrammarError(
                "terminal expected: " + expectedTerminal.toString() +
                        ", terminal found: " + terminal.toString() +
                        "at line: " +token.getLine() +
                        "and column: " + token.getColumn());
        return token;
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
        INtsParser program = new Program();

        concreteTree.setProgram((Program) program.processNts());

        return concreteTree;
    }

}
