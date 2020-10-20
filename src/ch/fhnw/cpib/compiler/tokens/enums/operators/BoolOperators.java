package ch.fhnw.cpib.compiler.tokens.enums.operators;

import java.util.Arrays;

public enum BoolOperators implements IBoolOperator{
    NOT,
    CON_AND,
    CON_OR;

    public static BoolOperators getByName(String name){
        String uppercaseName = name.toUpperCase();
        return Arrays.stream(BoolOperators.values())
                .filter(s -> s.name().equals(uppercaseName))
                .findFirst().orElse(null);
    }
}
