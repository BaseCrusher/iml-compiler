package ch.fhnw.cpib.compiler.parser.nts;

import ch.fhnw.cpib.compiler.error.GrammarError;
import ch.fhnw.cpib.compiler.parser.INtsParser;
import ch.fhnw.cpib.compiler.parser.Parser;
import ch.fhnw.cpib.compiler.tokens.IToken;

import static ch.fhnw.cpib.compiler.tokens.enums.KeywordTerminals.COMMA;
import static ch.fhnw.cpib.compiler.tokens.enums.KeywordTerminals.RPAREN;

public class RepCommaProgParam implements INtsParser {
    private final IToken token;
    private INtsParser progParam;
    private INtsParser repCommaProgParam;
    private INtsParser epsilon;
    private final String string;

    public RepCommaProgParam() throws GrammarError {
        token = Parser.consume(COMMA, RPAREN);
        if (token.hasTerminal(COMMA)) {
            progParam = new ProgParam(token);
            repCommaProgParam = new RepCommaProgParam();
            string = token.getTerminal().toString() + " " + progParam.toString() + " " + repCommaProgParam.toString();
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
}
