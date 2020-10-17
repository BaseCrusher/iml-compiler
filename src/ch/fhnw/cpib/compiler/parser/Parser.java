package ch.fhnw.cpib.compiler.parser;

import ch.fhnw.cpib.compiler.error.GrammarError;
import ch.fhnw.cpib.compiler.tokens.IToken;
import ch.fhnw.cpib.compiler.tokens.ITokenList;
import ch.fhnw.cpib.compiler.tokens.enums.ITerminal;
import ch.fhnw.cpib.compiler.tokens.enums.KeywordTerminals;

public class Parser implements IParser {

    private ITokenList tokenList;
    private IToken token;
    private ITerminal terminal;

    public Parser(ITokenList tokenList) throws GrammarError {
        if (!tokenList.hasSentinel()) {
            throw new GrammarError("Token list has no Sentinel");
        }
        this.tokenList = tokenList;
        token = tokenList.nextToken();    }

    @Override
    public IToken consume(ITerminal expectedTerminal) throws GrammarError {
        if (terminal != expectedTerminal) throw new GrammarError(
                "terminal expected: " + expectedTerminal.toString() +
                        ", terminal found: " + terminal.toString());
        IToken consumedToken = token;
        if (terminal != KeywordTerminals.SENTINEL) {
            token = tokenList.nextToken();
            terminal = token.getTerminal();
        }
        return consumedToken;
    }

    @Override
    public ParseTree parse() throws GrammarError {
        return null;
    }
}
