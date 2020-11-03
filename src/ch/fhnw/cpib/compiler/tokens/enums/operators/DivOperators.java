package ch.fhnw.cpib.compiler.tokens.enums.operators;

import java.util.Arrays;

import ch.fhnw.cpib.compiler.tokens.enums.dictionary.Symbols;

public enum DivOperators implements IDivOperator {
    DIV_E("divE"),
    MOD_E("modE"),
    DIV_F("divF"),
    MOD_F("modF"),
    DIV_T("divT"),
    MOD_T("modT");
    private final String name;

    DivOperators(String name) {

        this.name = name;
    }

    public static DivOperators getByName(String name){
        return Arrays.stream(DivOperators.values())
                .filter(s -> s.name.equals(name))
                .findFirst().orElse(null);
    }

    public static DivOperators contains (Symbols symbol) {
        for (DivOperators op : values()) {
            if (symbol.name().equals(op.name())) {
                return op;
            }
        }
        return null;
    }
}
