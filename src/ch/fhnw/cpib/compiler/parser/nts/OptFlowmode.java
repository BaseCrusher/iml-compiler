package ch.fhnw.cpib.compiler.parser.nts;

import ch.fhnw.cpib.compiler.parser.INtsParser;
import ch.fhnw.cpib.compiler.tokens.IToken;

import static ch.fhnw.cpib.compiler.tokens.enums.AttributeTerminals.FLOWMODE;

public class OptFlowmode  implements INtsParser {
    private final IToken token;
    private INtsParser epsilon;
    private final String string;

    public OptFlowmode(IToken token) {
        this.token = token;
        if (token.hasTerminal(FLOWMODE)) {
            string = token.getTerminal().toString();
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
