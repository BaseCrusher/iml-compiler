package ch.fhnw.cpib.compiler.parser.nts;

import ch.fhnw.cpib.compiler.error.GrammarError;
import ch.fhnw.cpib.compiler.parser.INtsParser;
import ch.fhnw.cpib.compiler.parser.Parser;
import ch.fhnw.cpib.compiler.tokens.IToken;

import static ch.fhnw.cpib.compiler.tokens.enums.AttributeTerminals.CHANGEMODE;
import static ch.fhnw.cpib.compiler.tokens.enums.AttributeTerminals.FLOWMODE;
import static ch.fhnw.cpib.compiler.tokens.enums.AttributeTerminals.IDENT;
import static ch.fhnw.cpib.compiler.tokens.enums.AttributeTerminals.MECHMODE;

public class Param implements INtsParser {
    private final IToken token;
    private INtsParser optFlowmode;
    private INtsParser optMechmode;
    private INtsParser optChangemode;
    private INtsParser typedIdent;
    private final String string;

    public Param() throws GrammarError {
        token = Parser.consume(IDENT, CHANGEMODE, MECHMODE, FLOWMODE);
        optFlowmode = new OptFlowmode(token);
        optMechmode = new OptMechmode();
        optChangemode = new OptChangemode();
        typedIdent = new TypedIdent();

        string = token.getTerminal().toString() + " " + optFlowmode.toString() + " " + optMechmode.toString() + " " + optChangemode.toString() + " " + typedIdent.toString();
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

    public INtsParser getOptMechmode() {
        return optMechmode;
    }

    public INtsParser getOptChangemode() {
        return optChangemode;
    }

    public INtsParser getTypedIdent() {
        return typedIdent;
    }
}
