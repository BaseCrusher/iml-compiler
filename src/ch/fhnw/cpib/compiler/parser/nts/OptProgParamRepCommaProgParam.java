package ch.fhnw.cpib.compiler.parser.nts;

import ch.fhnw.cpib.compiler.error.GrammarError;
import ch.fhnw.cpib.compiler.parser.IAbstractNode;
import ch.fhnw.cpib.compiler.parser.INtsParser;
import ch.fhnw.cpib.compiler.parser.IToAbsNodeList;
import ch.fhnw.cpib.compiler.parser.Parser;
import ch.fhnw.cpib.compiler.tokens.IToken;

import java.util.List;

import static ch.fhnw.cpib.compiler.tokens.enums.AttributeTerminals.CHANGEMODE;
import static ch.fhnw.cpib.compiler.tokens.enums.AttributeTerminals.FLOWMODE;
import static ch.fhnw.cpib.compiler.tokens.enums.AttributeTerminals.IDENT;
import static ch.fhnw.cpib.compiler.tokens.enums.KeywordTerminals.RPAREN;

public class OptProgParamRepCommaProgParam implements INtsParser, IToAbsNodeList {
    private final IToken token;
    private INtsParser progParam;
    private INtsParser repCommaProgParam;
    private INtsParser epsilon;
    private final String string;

    public OptProgParamRepCommaProgParam() throws GrammarError {
        token = Parser.getCurrentToken();
        if (token.hasTerminal(IDENT, CHANGEMODE, FLOWMODE)) {
            progParam = new ProgParam();
            repCommaProgParam = new RepCommaProgParam();
            string = progParam.toString() + repCommaProgParam.toString();
        }
        else if (token.hasTerminal(RPAREN)) {
            epsilon = new Epsilon();
            string = epsilon.toString();
        }
        else {
            throw new GrammarError(token);
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

    @Override
    public List<IAbstractNode> toAbsSyn() {
        return null;
    }
}
