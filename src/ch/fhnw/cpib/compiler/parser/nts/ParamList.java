package ch.fhnw.cpib.compiler.parser.nts;

import ch.fhnw.cpib.compiler.error.GrammarError;
import ch.fhnw.cpib.compiler.parser.INtsParser;
import ch.fhnw.cpib.compiler.parser.Parser;
import ch.fhnw.cpib.compiler.tokens.IToken;

import static ch.fhnw.cpib.compiler.tokens.enums.KeywordTerminals.LPAREN;
import static ch.fhnw.cpib.compiler.tokens.enums.KeywordTerminals.RPAREN;

public class ParamList implements INtsParser {
    private final IToken token;
    private INtsParser optParamRepCommaParam;
    private final String string;

    public ParamList() throws GrammarError {
        token = Parser.consume(LPAREN);
        optParamRepCommaParam = new OptParamRepCommaParam();
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

    public INtsParser getOptParamRepCommaParam() {
        return optParamRepCommaParam;
    }
}
