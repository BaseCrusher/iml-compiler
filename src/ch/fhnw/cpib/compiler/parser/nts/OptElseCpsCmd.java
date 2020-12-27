package ch.fhnw.cpib.compiler.parser.nts;

import ch.fhnw.cpib.compiler.error.GrammarError;
import ch.fhnw.cpib.compiler.parser.*;
import ch.fhnw.cpib.compiler.tokens.IToken;

import java.util.ArrayList;
import java.util.List;

import static ch.fhnw.cpib.compiler.tokens.enums.KeywordTerminals.ELSE;
import static ch.fhnw.cpib.compiler.tokens.enums.KeywordTerminals.ENDIF;

public class OptElseCpsCmd implements INtsParser {
    private final IToken token;
    private CpsCmd cpsCmd;
    private Epsilon epsilon;
    private final String string;

    public OptElseCpsCmd(Environment environment) throws GrammarError {
        token = Parser.getCurrentToken();
        if (token.hasTerminal(ELSE)) {
            Parser.consume(ELSE);
            cpsCmd = new CpsCmd(environment);
            string = token.getTerminal().toString() + " " + cpsCmd.toString();
        }
        else if (token.hasTerminal(ENDIF)) {
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

    public INtsParser getCpsCmd() {
        return cpsCmd;
    }

    public INtsParser getEpsilon() {
        return epsilon;
    }

    public List<IAbstractNode> toAbsSyn() {
        if (epsilon == null) {
            return cpsCmd.toAbsSyn();
        }
        return new ArrayList<>();
    }
}
