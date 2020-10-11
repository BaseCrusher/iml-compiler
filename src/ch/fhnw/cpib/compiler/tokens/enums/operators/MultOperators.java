package ch.fhnw.cpib.compiler.tokens.enums.operators;

import ch.fhnw.cpib.compiler.tokens.enums.dictionary.Symbols;

public enum MultOperators {
    TIMES,
    DIV_E,
    MOD_E,
    DIV_F,
    MOD_F,
    DIV_T,
    MOD_T;

    public static boolean contains (Symbols symbol) {
        for (MultOperators op : values()) {
            if (symbol.name().equals(op.name())) {
                return true;
            }
        }
        return false;
    }
    
}
