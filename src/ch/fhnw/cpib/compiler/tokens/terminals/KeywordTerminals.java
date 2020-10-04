package ch.fhnw.cpib.compiler.tokens.terminals;

public enum KeywordTerminals implements ITerminal {
    WHILE("WHILE"), DO("DO"), BECOMES("BECOMES"), ENDWHILE("ENDWHILE"),
    SENTINEL("SENTINEL");

    private String toString;

    KeywordTerminals(String terminal) {
        this.toString = terminal;
    }

    @Override
    public String toString() {
        return toString;
    }
}
