package ch.fhnw.cpib.compiler.parser.nts;

import ch.fhnw.cpib.compiler.error.GrammarError;
import ch.fhnw.cpib.compiler.parser.INtsParser;
import ch.fhnw.cpib.compiler.parser.Parser;
import ch.fhnw.cpib.compiler.tokens.IToken;

import static ch.fhnw.cpib.compiler.tokens.enums.AttributeTerminals.IDENT;
import static ch.fhnw.cpib.compiler.tokens.enums.KeywordTerminals.*;
import static ch.fhnw.cpib.compiler.tokens.enums.KeywordTerminals.*;

public class OptGlobalCpsDecl implements INtsParser {
    private IToken token;
    private INtsParser cpsDecl;
    private INtsParser epsilon;
    private String string;

    public OptGlobalCpsDecl() throws GrammarError {
        token = Parser.consume(GLOBAL, DO);
        if (token.hasTerminal(GLOBAL)) {
            cpsDecl = null;
            string = token.getTerminal().toString() + " " + cpsDecl.toString();
        }
        else {
            epsilon = new Epsilon();
            string = epsilon.toString();
        }
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

    public INtsParser getCpsDecl() {
        return cpsDecl;
    }

    public void setCpsDecl(INtsParser cpsDecl) {
        this.cpsDecl = cpsDecl;
    }

    public INtsParser getEpsilon() {
        return epsilon;
    }

    public void setEpsilon(INtsParser epsilon) {
        this.epsilon = epsilon;
    }
}
