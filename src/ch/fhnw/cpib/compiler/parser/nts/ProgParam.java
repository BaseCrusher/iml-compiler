package ch.fhnw.cpib.compiler.parser.nts;

import ch.fhnw.cpib.compiler.error.GrammarError;
import ch.fhnw.cpib.compiler.parser.INtsParser;
import ch.fhnw.cpib.compiler.parser.Parser;
import ch.fhnw.cpib.compiler.tokens.IToken;

import static ch.fhnw.cpib.compiler.tokens.enums.AttributeTerminals.*;

public class ProgParam implements INtsParser {
    private IToken token;
    private INtsParser optFlowmode;
    private INtsParser optChangemode;
    private INtsParser typedIdent;

    public ProgParam() throws GrammarError {
        token = Parser.consume(IDENT, CHANGEMODE, FLOWMODE);
        optFlowmode = null;
        optChangemode = null;
        typedIdent = null;
    }

    public String toString() {
        return token.getTerminal().toString() + " " + optFlowmode.toString() + " " + optChangemode.toString() + " " + typedIdent.toString();
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
