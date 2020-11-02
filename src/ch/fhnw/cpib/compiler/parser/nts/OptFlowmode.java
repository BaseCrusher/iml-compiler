package ch.fhnw.cpib.compiler.parser.nts;

import ch.fhnw.cpib.compiler.error.GrammarError;
import ch.fhnw.cpib.compiler.parser.INtsParser;
import ch.fhnw.cpib.compiler.parser.Parser;
import ch.fhnw.cpib.compiler.tokens.IToken;

import static ch.fhnw.cpib.compiler.tokens.enums.AttributeTerminals.*;

public class OptFlowmode  implements INtsParser {
    private IToken token;
    private INtsParser epsilon;
    private String string;

    public OptFlowmode() throws GrammarError {
        token = Parser.consume(FLOWMODE, MENCHMODE, IDENT, CHANGEMODE);
        if (token.hasTerminal(FLOWMODE)) {
            string = token.getTerminal().toString();
        }
        else {
            epsilon = new Epsilon();
            string = epsilon.toString();
        }
    }

    public IToken getToken() {
        return token;
    }

    public void setToken(IToken token) {
        this.token = token;
    }

    public INtsParser getEpsilon() {
        return epsilon;
    }

    public void setEpsilon(INtsParser epsilon) {
        this.epsilon = epsilon;
    }
}
