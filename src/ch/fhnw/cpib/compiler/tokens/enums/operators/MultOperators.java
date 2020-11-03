package ch.fhnw.cpib.compiler.tokens.enums.operators;

import java.util.Arrays;

import ch.fhnw.cpib.compiler.tokens.enums.dictionary.Symbols;

public enum MultOperators implements IMultOperator {
    TIMES("times");

    private final String name;

    MultOperators(String name) {

        this.name = name;
    }

    public static MultOperators getByName(String name){
        return Arrays.stream(MultOperators.values())
                .filter(s -> s.name.equals(name))
                .findFirst().orElse(null);
    }

    public static MultOperators contains(Symbols symbol){
        if (symbol == Symbols.TIMES)
            return MultOperators.TIMES;
        return null;
    }

}
