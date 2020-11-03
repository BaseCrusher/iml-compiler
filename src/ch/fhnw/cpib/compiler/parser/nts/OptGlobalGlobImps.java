package ch.fhnw.cpib.compiler.parser.nts;

import ch.fhnw.cpib.compiler.error.GrammarError;
import ch.fhnw.cpib.compiler.parser.INtsParser;
import ch.fhnw.cpib.compiler.parser.Parser;
import ch.fhnw.cpib.compiler.tokens.IToken;

import static ch.fhnw.cpib.compiler.tokens.enums.AttributeTerminals.*;
import static ch.fhnw.cpib.compiler.tokens.enums.KeywordTerminals.*;

public class OptGlobalGlobImps implements INtsParser {
    private IToken token;
    private INtsParser globImps;
    private INtsParser epsilon;
    private String string;

    public OptGlobalGlobImps() throws GrammarError {
        token = Parser.consume(GLOBAL, DO, LOCAL);
        if (token.hasTerminal(GLOBAL)) {
            globImps = null;
            string = token.getTerminal().toString() + " " + globImps.toString();
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

    public INtsParser getGlobImps() {
        return globImps;
    }

    public void setGlobImps(INtsParser globImps) {
        this.globImps = globImps;
    }

    public INtsParser getEpsilon() {
        return epsilon;
    }

    public void setEpsilon(INtsParser epsilon) {
        this.epsilon = epsilon;
    }
}
