package ch.fhnw.cpib.compiler.parser.nts;

import ch.fhnw.cpib.compiler.error.GrammarError;
import ch.fhnw.cpib.compiler.parser.INtsParser;
import ch.fhnw.cpib.compiler.parser.Parser;
import ch.fhnw.cpib.compiler.tokens.IToken;

import static ch.fhnw.cpib.compiler.tokens.enums.AttributeTerminals.CHANGEMODE;
import static ch.fhnw.cpib.compiler.tokens.enums.AttributeTerminals.FLOWMODE;
import static ch.fhnw.cpib.compiler.tokens.enums.AttributeTerminals.IDENT;
import static ch.fhnw.cpib.compiler.tokens.enums.AttributeTerminals.MECHMODE;

public class OptFlowmode  implements INtsParser {
    private final IToken token;
    private INtsParser epsilon;
    private final String string;

    public OptFlowmode() throws GrammarError {
        token = Parser.getCurrentToken();
        if (token.hasTerminal(FLOWMODE)) {
            Parser.consume(FLOWMODE);
            string = token.toString();
        }
        else if (token.hasTerminal(MECHMODE, IDENT, CHANGEMODE)) {
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

    public INtsParser getEpsilon() {
        return epsilon;
    }
}
