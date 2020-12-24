package ch.fhnw.cpib.compiler.parser.nts;

import ch.fhnw.cpib.compiler.error.GrammarError;
import ch.fhnw.cpib.compiler.parser.INtsParser;
import ch.fhnw.cpib.compiler.tokens.IToken;

public class ProgParam implements INtsParser {
    private final IToken token;
    private INtsParser optFlowmode;
    private INtsParser optChangemode;
    private INtsParser typedIdent;
    private INtsParser epsilon;
    private String string;

    public ProgParam(IToken token) throws GrammarError {
        this.token = token;
        optFlowmode = new OptFlowmode(this.token);
        optChangemode = new OptChangemode(this.token);
        typedIdent = new TypedIdent(this.token);
    }

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

    public INtsParser getTypedIdent() {
        return typedIdent;
    }
}
