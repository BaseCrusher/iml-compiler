package ch.fhnw.cpib.compiler.parser.nts;

import ch.fhnw.cpib.compiler.error.GrammarError;
import ch.fhnw.cpib.compiler.parser.INtsParser;
import ch.fhnw.cpib.compiler.parser.Parser;
import ch.fhnw.cpib.compiler.tokens.IToken;

import static ch.fhnw.cpib.compiler.tokens.enums.AttributeTerminals.*;
import static ch.fhnw.cpib.compiler.tokens.enums.KeywordTerminals.*;

public class GlobImp implements INtsParser {
    private IToken token;
    private INtsParser optFlowmode;
    private INtsParser optChangemode;
    private IToken identifier;
    private String string;

    public GlobImp() throws GrammarError {
        token = Parser.consume(IDENT, CHANGEMODE, FLOWMODE);
        optFlowmode = null;
        optChangemode = null;
        identifier = Parser.consume(IDENT);
        string = token.getTerminal().toString() + " " + optFlowmode.toString() + " " + optChangemode.toString() + " " + identifier.getTerminal().toString();
    }

    @Override
    public String toString() {
        return string;
    }

    public IToken getToken() {
        return token;
    }

    public INtsParser getOptFlowmode() {
        return optFlowmode;
    }

    public INtsParser getOptChangemode() {
        return optChangemode;
    }

    public IToken getIdentifier() {
        return identifier;
    }
}
