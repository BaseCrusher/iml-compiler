package ch.fhnw.cpib.compiler.parser.nts;

import ch.fhnw.cpib.compiler.error.GrammarError;
import ch.fhnw.cpib.compiler.parser.IAbstractNode;
import ch.fhnw.cpib.compiler.parser.INtsParser;
import ch.fhnw.cpib.compiler.parser.IToAbsNode;
import ch.fhnw.cpib.compiler.parser.Parser;
import ch.fhnw.cpib.compiler.tokens.IToken;

import static ch.fhnw.cpib.compiler.tokens.enums.AttributeTerminals.MECHMODE;

public class OptMechmode implements INtsParser, IToAbsNode {
    private final IToken token;
    private INtsParser epsilon;
    private final String string;

    public OptMechmode() throws GrammarError {
        token = Parser.getCurrentToken();
        if (token.hasTerminal(MECHMODE)) {
            Parser.consume(MECHMODE);
            string = token.toString();
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

    @Override
    public IAbstractNode toAbsSyn() {
        return null;
    }
}
