package ch.fhnw.cpib.compiler.tokens.terminals;

public enum AttributeTerminals implements ITerminal {
    LITERAL("LITERAL"), IDENT("IDENT");

    private String toString;

    AttributeTerminals(String literal) {
        this.toString = literal;
    }

    @Override
    public String toString() {
        return toString;
    }
}
