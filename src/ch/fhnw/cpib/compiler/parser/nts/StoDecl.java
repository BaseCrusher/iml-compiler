package ch.fhnw.cpib.compiler.parser.nts;

import ch.fhnw.cpib.compiler.error.GrammarError;
import ch.fhnw.cpib.compiler.parser.INtsParser;
import ch.fhnw.cpib.compiler.parser.Parser;
import ch.fhnw.cpib.compiler.tokens.IToken;

import static ch.fhnw.cpib.compiler.tokens.enums.AttributeTerminals.*;


public class StoDecl implements INtsParser {
    private IToken token;
    private INtsParser optChangemode;
    private INtsParser typedIdent;
    private String string;

    public StoDecl() throws GrammarError {
        token = Parser.consume(IDENT, CHANGEMODE);
        optChangemode = null;
        typedIdent = null;
        string = token.getTerminal().toString() + " " + optChangemode.toString() + " " + typedIdent.toString();
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
