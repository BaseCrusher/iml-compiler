package ch.fhnw.cpib.compiler.tokens.enums.types;

import java.util.Arrays;

public enum Inttypes implements IType {
    INT32,
    INT64,
    INT1024;

    public static IType getByName(String name){
        String uppercaseName = name.toUpperCase();
        return Arrays.stream(Inttypes.values())
                .filter(s -> s.name().equals(uppercaseName))
                .findFirst().orElse(null);
    }
}
