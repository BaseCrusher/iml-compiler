package ch.fhnw.cpib.compiler.parser.nts;

import ch.fhnw.cpib.compiler.error.GrammarError;
import ch.fhnw.cpib.compiler.parser.INtsParser;
import ch.fhnw.cpib.compiler.parser.Parser;
import ch.fhnw.cpib.compiler.tokens.IToken;

import static ch.fhnw.cpib.compiler.tokens.enums.KeywordTerminals.COMMA;
import static ch.fhnw.cpib.compiler.tokens.enums.KeywordTerminals.DO;
import static ch.fhnw.cpib.compiler.tokens.enums.KeywordTerminals.LOCAL;

public class RepCommaGlobImp implements INtsParser {
    private final IToken token;
    private INtsParser globImps;
    private INtsParser epsilon;
    private final String string;

    public RepCommaGlobImp() throws GrammarError {
        token = Parser.consume(COMMA, DO, LOCAL);
        if (token.hasTerminal(COMMA)) {
            globImps = new GlobImps();
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

    public INtsParser getGlobImps() {
        return globImps;
    }

    public INtsParser getEpsilon() {
        return epsilon;
    }
}
