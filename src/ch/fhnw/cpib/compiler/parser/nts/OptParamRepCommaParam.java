package ch.fhnw.cpib.compiler.parser.nts;

import ch.fhnw.cpib.compiler.error.GrammarError;
import ch.fhnw.cpib.compiler.parser.INtsParser;
import ch.fhnw.cpib.compiler.parser.Parser;
import ch.fhnw.cpib.compiler.tokens.IToken;

import static ch.fhnw.cpib.compiler.tokens.enums.AttributeTerminals.CHANGEMODE;
import static ch.fhnw.cpib.compiler.tokens.enums.AttributeTerminals.FLOWMODE;
import static ch.fhnw.cpib.compiler.tokens.enums.AttributeTerminals.IDENT;
import static ch.fhnw.cpib.compiler.tokens.enums.AttributeTerminals.MENCHMODE;
import static ch.fhnw.cpib.compiler.tokens.enums.KeywordTerminals.RPAREN;

public class OptParamRepCommaParam implements INtsParser {
    private final IToken token;
    private INtsParser param;
    private INtsParser repCommaParam;
    private INtsParser epsilon;
    private final String string;

    public OptParamRepCommaParam() throws GrammarError {
        token = Parser.consume(IDENT, CHANGEMODE, MENCHMODE, FLOWMODE, RPAREN);
        if (token.hasTerminal(RPAREN)) {
            epsilon = new Epsilon();
            string = epsilon.toString();
        }
        else {
            param = new Param();
            repCommaParam = new RepCommaParam();
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

    public INtsParser getParam() {
        return param;
    }

    public INtsParser getRepCommaParam() {
        return repCommaParam;
    }

    public INtsParser getEpsilon() {
        return epsilon;
    }
}
