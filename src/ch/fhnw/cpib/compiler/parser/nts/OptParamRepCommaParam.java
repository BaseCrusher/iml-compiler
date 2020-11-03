package ch.fhnw.cpib.compiler.parser.nts;

import ch.fhnw.cpib.compiler.error.GrammarError;
import ch.fhnw.cpib.compiler.parser.INtsParser;
import ch.fhnw.cpib.compiler.parser.Parser;
import ch.fhnw.cpib.compiler.tokens.IToken;

import static ch.fhnw.cpib.compiler.tokens.enums.AttributeTerminals.*;
import static ch.fhnw.cpib.compiler.tokens.enums.KeywordTerminals.*;

public class OptParamRepCommaParam implements INtsParser {
    private IToken token;
    private INtsParser param;
    private INtsParser repCommaParam;
    private INtsParser epsilon;
    private String string;

    public OptParamRepCommaParam() throws GrammarError {
        token = Parser.consume(IDENT, CHANGEMODE, MENCHMODE, FLOWMODE, RPAREN);
        if (token.hasTerminal(RPAREN)) {
            epsilon = new Epsilon();
            string = epsilon.toString();
        }
        else {
            param = null;
            repCommaParam = null;
            string = token.getTerminal().toString() + " " + param.toString() + " " + repCommaParam.toString();
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

    public INtsParser getParam() {
        return param;
    }

    public void setParam(INtsParser param) {
        this.param = param;
    }

    public INtsParser getRepCommaParam() {
        return repCommaParam;
    }

    public void setRepCommaParam(INtsParser repCommaParam) {
        this.repCommaParam = repCommaParam;
    }

    public INtsParser getEpsilon() {
        return epsilon;
    }

    public void setEpsilon(INtsParser epsilon) {
        this.epsilon = epsilon;
    }
}
