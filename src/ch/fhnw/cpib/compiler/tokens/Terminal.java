package ch.fhnw.cpib.compiler.tokens;

public enum Terminal {
    LITERAL("LITERAL");

    private String toString;

    Terminal(String literal) {
        this.toString = literal;
    }

    @Override
    public String toString() {
        return toString;
    }
}
