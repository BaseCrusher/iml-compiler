package ch.fhnw.cpib.compiler.tokens.enums.modes;

import java.util.Arrays;

public enum ChangeModes implements IChangeMode {

    CONST,
    VAR;

    public static ChangeModes getByName(String name){
        String uppercaseName = name.toUpperCase();
        return Arrays.stream(ChangeModes.values())
                .filter(s -> s.name().equals(uppercaseName))
                .findFirst().orElse(null);
    }
}
