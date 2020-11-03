package ch.fhnw.cpib.compiler.tokens.enums.operators;

import java.util.Arrays;

import ch.fhnw.cpib.compiler.tokens.enums.dictionary.Symbols;

public enum AddOperators implements IAddOperator {
    PLUS,
    MINUS;

    public static boolean contains(Symbols symbol) {
        for (AddOperators op : values()) {
            if (symbol.name().equals(op.name())) {
                return true;
            }
        }
        return false;
    }

    public static AddOperators getByName(String name) {
        String uppercaseName = name.toUpperCase();
        return Arrays.stream(AddOperators.values())
                .filter(s -> s.name().equals(uppercaseName))
                .findFirst().orElse(null);
    }

}
