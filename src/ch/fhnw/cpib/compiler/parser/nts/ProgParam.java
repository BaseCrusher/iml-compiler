package ch.fhnw.cpib.compiler.parser.nts;

import ch.fhnw.cpib.compiler.error.GrammarError;
import ch.fhnw.cpib.compiler.parser.INtsParser;
import ch.fhnw.cpib.compiler.parser.Parser;
import ch.fhnw.cpib.compiler.tokens.IToken;

import static ch.fhnw.cpib.compiler.tokens.enums.AttributeTerminals.CHANGEMODE;
import static ch.fhnw.cpib.compiler.tokens.enums.AttributeTerminals.FLOWMODE;
import static ch.fhnw.cpib.compiler.tokens.enums.AttributeTerminals.IDENT;

public class ProgParam implements INtsParser {
    private final IToken token;
    private INtsParser optFlowmode;
    private INtsParser optChangemode;
    private INtsParser typedIdent;

    public ProgParam() throws GrammarError {
        token = Parser.consume(IDENT, CHANGEMODE, FLOWMODE);
        optFlowmode = new OptFlowmode();
        optChangemode = new OptChangemode();
        typedIdent = new TypedIdent();
    }

    public String toString() {
        return token.getTerminal().toString() + " " + optFlowmode.toString() + " " + optChangemode.toString() + " " + typedIdent.toString();
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

    public INtsParser getTypedIdent() {
        return typedIdent;
    }
}
