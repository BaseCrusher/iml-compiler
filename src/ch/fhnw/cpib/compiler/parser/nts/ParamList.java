package ch.fhnw.cpib.compiler.parser.nts;

import ch.fhnw.cpib.compiler.error.GrammarError;
import ch.fhnw.cpib.compiler.parser.INtsParser;
import ch.fhnw.cpib.compiler.parser.Parser;
import ch.fhnw.cpib.compiler.tokens.IToken;

import static ch.fhnw.cpib.compiler.tokens.enums.AttributeTerminals.*;
import static ch.fhnw.cpib.compiler.tokens.enums.KeywordTerminals.*;

public class ParamList implements INtsParser {
    private IToken token;
    private INtsParser optParamRepCommaParam;
    private String string;

    public ParamList() throws GrammarError {
        token = Parser.consume(LPAREN);
        optParamRepCommaParam = null;
        Parser.consume(RPAREN);
        string = "( " + optParamRepCommaParam.toString() + " )";
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

    public INtsParser getOptParamRepCommaParam() {
        return optParamRepCommaParam;
    }

    public void setOptParamRepCommaParam(INtsParser optParamRepCommaParam) {
        this.optParamRepCommaParam = optParamRepCommaParam;
    }
}
