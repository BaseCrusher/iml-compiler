package ch.fhnw.cpib.compiler.parser.nts;

import ch.fhnw.cpib.compiler.error.GrammarError;
import ch.fhnw.cpib.compiler.parser.INtsParser;
import ch.fhnw.cpib.compiler.parser.Parser;
import ch.fhnw.cpib.compiler.tokens.IToken;

import static ch.fhnw.cpib.compiler.tokens.enums.AttributeTerminals.CHANGEMODE;
import static ch.fhnw.cpib.compiler.tokens.enums.AttributeTerminals.IDENT;

public class OptChangemode implements INtsParser {
    private final IToken token;
    private INtsParser epsilon;
    private final String string;

    public OptChangemode() throws GrammarError {
        token = Parser.consume(CHANGEMODE, IDENT);
        if (token.hasTerminal(CHANGEMODE)) {
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
