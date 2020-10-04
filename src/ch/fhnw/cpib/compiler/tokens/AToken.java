package ch.fhnw.cpib.compiler.tokens;

public abstract class AToken implements IToken {
    private final Terminal terminal;

    public AToken(Terminal t) {
        terminal = t;
    }

    @Override
    public Terminal getTerminal() {
        return terminal;
    }
}
