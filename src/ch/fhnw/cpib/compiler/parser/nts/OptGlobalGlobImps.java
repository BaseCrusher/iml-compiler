package ch.fhnw.cpib.compiler.parser.nts;

import ch.fhnw.cpib.compiler.error.GrammarError;
import ch.fhnw.cpib.compiler.parser.IAbstractNode;
import ch.fhnw.cpib.compiler.parser.INtsParser;
import ch.fhnw.cpib.compiler.parser.Parser;
import ch.fhnw.cpib.compiler.tokens.IToken;

import java.util.ArrayList;
import java.util.List;

import static ch.fhnw.cpib.compiler.tokens.enums.KeywordTerminals.DO;
import static ch.fhnw.cpib.compiler.tokens.enums.KeywordTerminals.GLOBAL;
import static ch.fhnw.cpib.compiler.tokens.enums.KeywordTerminals.LOCAL;

public class OptGlobalGlobImps implements INtsParser {
    private final IToken token;
    private GlobImps globImps;
    private Epsilon epsilon;
    private final String string;

    public OptGlobalGlobImps() throws GrammarError {
        token = Parser.getCurrentToken();
        if (token.hasTerminal(GLOBAL)) {
            Parser.consume(GLOBAL);
            globImps = new GlobImps();
            string = token.getTerminal().toString() + " " + globImps.toString();
        }
        else if (token.hasTerminal(DO, LOCAL)) {
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

    public INtsParser getGlobImps() {
        return globImps;
    }

    public INtsParser getEpsilon() {
        return epsilon;
    }

    public List<IAbstractNode> toAbsSyn() {
        if (epsilon == null) {
            return globImps.toAbsSyn();
        }
        return new ArrayList<>();
    }
}
