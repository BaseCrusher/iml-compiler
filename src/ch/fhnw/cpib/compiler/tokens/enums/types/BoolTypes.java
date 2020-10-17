package ch.fhnw.cpib.compiler.tokens.enums.types;

import java.util.Arrays;

public enum BoolTypes implements IType {
    BOOL;

    public static IType getByName(String name){
        String uppercaseName = name.toUpperCase();
        return Arrays.stream(BoolTypes.values())
                .filter(s -> s.name().equals(uppercaseName))
                .findFirst().orElse(null);
    }
}
