package ch.fhnw.cpib.compiler.parser.nts;

import ch.fhnw.cpib.compiler.error.GrammarError;
import ch.fhnw.cpib.compiler.parser.INtsParser;
import ch.fhnw.cpib.compiler.parser.Parser;
import ch.fhnw.cpib.compiler.tokens.IToken;


import static ch.fhnw.cpib.compiler.tokens.enums.AttributeTerminals.*;
import static ch.fhnw.cpib.compiler.tokens.enums.KeywordTerminals.*;

public class OptProgParamRepCommaProgParam implements INtsParser {
    private IToken token;
    private INtsParser progParam;
    private INtsParser repCommaProgParam;
    private INtsParser epsilon;
    private String string;

    public OptProgParamRepCommaProgParam() throws GrammarError {
        token = Parser.consume(IDENT, CHANGEMODE, FLOWMODE, RPAREN);
        if (token.hasTerminal(IDENT)
            || token.hasTerminal(CHANGEMODE)
            || token.hasTerminal(FLOWMODE)) {
            progParam = null;
            repCommaProgParam = null;
            string = token.getTerminal().toString() + " " +  progParam.toString() + " " + repCommaProgParam.toString();
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

    public INtsParser getProgParam() {
        return progParam;
    }

    public void setProgParam(INtsParser progParam) {
        this.progParam = progParam;
    }

    public INtsParser getRepCommaProgParam() {
        return repCommaProgParam;
    }

    public void setRepCommaProgParam(INtsParser repCommaProgParam) {
        this.repCommaProgParam = repCommaProgParam;
    }

    public INtsParser getEpsilon() {
        return epsilon;
    }

    public void setEpsilon(INtsParser epsilon) {
        this.epsilon = epsilon;
    }
}
