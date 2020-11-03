package ch.fhnw.cpib.compiler.parser.nts;

import ch.fhnw.cpib.compiler.error.GrammarError;
import ch.fhnw.cpib.compiler.parser.INtsParser;
import ch.fhnw.cpib.compiler.parser.Parser;
import ch.fhnw.cpib.compiler.tokens.IToken;

import static ch.fhnw.cpib.compiler.tokens.enums.AttributeTerminals.*;
import static ch.fhnw.cpib.compiler.tokens.enums.KeywordTerminals.*;

public class OptElseCpsCmd implements INtsParser {
    private IToken token;
    private INtsParser cpsCmd;
    private INtsParser epsilon;
    private String string;

    public OptElseCpsCmd() throws GrammarError {
        token = Parser.consume(ELSE, ENDIF);
        if (token.hasTerminal(ELSE)) {
            cpsCmd = null;
            string = token.getTerminal().toString() + " " + cpsCmd.toString();
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

    public INtsParser getCpsCmd() {
        return cpsCmd;
    }

    public INtsParser getEpsilon() {
        return epsilon;
    }
}
