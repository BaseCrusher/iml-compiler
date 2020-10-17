package ch.fhnw.cpib.compiler.tokens.enums.modes;

import java.util.Arrays;

public enum MechModes implements IMechMode {
    
    COPY,
    REF;

    public static IMechMode getByName(String name){
        String uppercaseName = name.toUpperCase();
        return Arrays.stream(MechModes.values())
                .filter(s -> s.name().equals(uppercaseName))
                .findFirst().orElse(null);
    }
}
