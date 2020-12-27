package ch.fhnw.cpib.compiler.parser.nts;

import ch.fhnw.cpib.compiler.error.GrammarError;
import ch.fhnw.cpib.compiler.parser.IAbstractNode;
import ch.fhnw.cpib.compiler.parser.INtsParser;
import ch.fhnw.cpib.compiler.parser.IToAbsNodeList;
import ch.fhnw.cpib.compiler.parser.Parser;
import ch.fhnw.cpib.compiler.tokens.IToken;

import java.util.List;

import static ch.fhnw.cpib.compiler.tokens.enums.KeywordTerminals.COMMA;
import static ch.fhnw.cpib.compiler.tokens.enums.KeywordTerminals.RPAREN;

public class RepCommaProgParam implements INtsParser, IToAbsNodeList {
    private final IToken token;
    private INtsParser progParam;
    private INtsParser repCommaProgParam;
    private INtsParser epsilon;
    private final String string;

    public RepCommaProgParam() throws GrammarError {
        token = Parser.getCurrentToken();
        if (token.hasTerminal(COMMA)) {
            Parser.consume(COMMA);
            progParam = new ProgParam();
            repCommaProgParam = new RepCommaProgParam();
            string = ", " + progParam.toString() + repCommaProgParam.toString();
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

    @Override
    public List<IAbstractNode> toAbsSyn() {
        return null;
    }
}
