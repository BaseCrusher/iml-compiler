package ch.fhnw.cpib.compiler.parser.nts;

import ch.fhnw.cpib.compiler.error.GrammarError;
import ch.fhnw.cpib.compiler.parser.INtsParser;
import ch.fhnw.cpib.compiler.parser.Parser;
import ch.fhnw.cpib.compiler.tokens.IToken;

import static ch.fhnw.cpib.compiler.tokens.enums.AttributeTerminals.CHANGEMODE;
import static ch.fhnw.cpib.compiler.tokens.enums.AttributeTerminals.FLOWMODE;
import static ch.fhnw.cpib.compiler.tokens.enums.AttributeTerminals.IDENT;
import static ch.fhnw.cpib.compiler.tokens.enums.KeywordTerminals.RPAREN;

public class OptProgParamRepCommaProgParam implements INtsParser {
    private final IToken token;
    private INtsParser progParam;
    private INtsParser repCommaProgParam;
    private INtsParser epsilon;
    private final String string;

    public OptProgParamRepCommaProgParam() throws GrammarError {
        token = Parser.consume(IDENT, CHANGEMODE, FLOWMODE, RPAREN);
        if (token.hasTerminal(IDENT)
            || token.hasTerminal(CHANGEMODE)
            || token.hasTerminal(FLOWMODE)) {
            progParam = new ProgParam();
            repCommaProgParam = new RepCommaProgParam();
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

    public INtsParser getProgParam() {
        return progParam;
    }

    public INtsParser getRepCommaProgParam() {
        return repCommaProgParam;
    }

    public INtsParser getEpsilon() {
        return epsilon;
    }

    public void setEpsilon(INtsParser epsilon) {
        this.epsilon = epsilon;
    }
}
