package ch.fhnw.cpib.compiler.tokens.enums.operators;

import ch.fhnw.cpib.compiler.tokens.enums.dictionary.Symbols;

import java.util.Arrays;

public enum MonoOperators implements IMonoOperator {
    NOT,
    PLUS,
    MINUS;

    public static boolean contains (Symbols symbol) {
        for (MonoOperators op : values()) {
            if (symbol.name().equals(op.name())) {
                return true;
            }
        }
        return false;
    }

    public static MonoOperators getByName(String name){
        String uppercaseName = name.toUpperCase();
        return Arrays.stream(MonoOperators.values())
                .filter(s -> s.name().equals(uppercaseName))
                .findFirst().orElse(null);
    }
}
