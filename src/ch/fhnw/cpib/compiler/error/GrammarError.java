package ch.fhnw.cpib.compiler.error;

import ch.fhnw.cpib.compiler.tokens.IToken;

public class GrammarError extends Throwable {

    public GrammarError(String errorMessage) {
        super(errorMessage);
    }

    public GrammarError(IToken token) {
        super("Unexpected token: "
                + token.getTerminal() + " at line: "
                + token.getLine() + " and column: "
                + token.getColumn());
    }
}
