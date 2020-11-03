package ch.fhnw.cpib.compiler.parser.nts;

import ch.fhnw.cpib.compiler.error.GrammarError;
import ch.fhnw.cpib.compiler.parser.INtsParser;
import ch.fhnw.cpib.compiler.parser.Parser;
import ch.fhnw.cpib.compiler.tokens.IToken;

import static ch.fhnw.cpib.compiler.tokens.enums.AttributeTerminals.*;
import static ch.fhnw.cpib.compiler.tokens.enums.KeywordTerminals.*;

public class Param implements INtsParser {
    private IToken token;
    private INtsParser optFlowmode;
    private INtsParser optMechmode;
    private INtsParser optChangemode;
    private INtsParser typedIdent;
    private String string;

    public Param() throws GrammarError {
        token = Parser.consume(IDENT, CHANGEMODE, MENCHMODE, FLOWMODE);
        optFlowmode = null;
        optMechmode = null;
        optChangemode = null;
        typedIdent = null;

        string = token.getTerminal().toString() + " " + optFlowmode.toString() + " " + optMechmode.toString() + " " + optChangemode.toString() + " " + typedIdent.toString();
    }

    @Override
    public String toString() {
        return string;
    }

    public IToken getToken() {
        return token;
    }

    public void setToken(IToken token) {
        this.token = token;
    }

    public INtsParser getOptFlowmode() {
        return optFlowmode;
    }

    public void setOptFlowmode(INtsParser optFlowmode) {
        this.optFlowmode = optFlowmode;
    }

    public INtsParser getOptMechmode() {
        return optMechmode;
    }

    public void setOptMechmode(INtsParser optMechmode) {
        this.optMechmode = optMechmode;
    }

    public INtsParser getOptChangemode() {
        return optChangemode;
    }

    public void setOptChangemode(INtsParser optChangemode) {
        this.optChangemode = optChangemode;
    }

    public INtsParser getTypedIdent() {
        return typedIdent;
    }

    public void setTypedIdent(INtsParser typedIdent) {
        this.typedIdent = typedIdent;
    }
}
