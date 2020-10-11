package ch.fhnw.cpib.compiler.tokens.enums.operators;

import ch.fhnw.cpib.compiler.tokens.enums.dictionary.Symbols;

public enum RelOperators implements IRelOperator {
    EQ,
    NEQ,
    GT,
    LT,
    GE,
    LE;

    public static boolean contains (Symbols symbol) {
        for (RelOperators op : values()) {
            if (symbol.name().equals(op.name())) {
                return true;
            }
        }
        return false;
    }
}
