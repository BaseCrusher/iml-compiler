package ch.fhnw.cpib.compiler.parser.nts;

import ch.fhnw.cpib.compiler.error.GrammarError;
import ch.fhnw.cpib.compiler.parser.INtsParser;
import ch.fhnw.cpib.compiler.tokens.IToken;

import static ch.fhnw.cpib.compiler.tokens.enums.AttributeTerminals.CHANGEMODE;

public class OptChangemode implements INtsParser {
    private final IToken token;
    private INtsParser epsilon;
    private final String string;

    public OptChangemode(IToken token) {
        this.token = token;
        if (this.token.hasTerminal(CHANGEMODE)) {
            string = this.token.getTerminal().toString();
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

    public INtsParser getEpsilon() {
        return epsilon;
    }
}
