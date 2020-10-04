package ch.fhnw.cpib.compiler.tokens;

public class BaseToken implements IToken {
    private final Terminal terminal;

    public BaseToken(Terminal t) {
        terminal = t;
    }

    @Override
    public Terminal getTerminal() {
        return null;
    }
}
