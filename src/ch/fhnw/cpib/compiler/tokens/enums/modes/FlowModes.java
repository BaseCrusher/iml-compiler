package ch.fhnw.cpib.compiler.tokens.enums.modes;

import java.util.Arrays;

public enum FlowModes implements IFlowMode {
    
    IN,
    OUT,
    INOUT;

    public static IFlowMode getByName(String name){
        String uppercaseName = name.toUpperCase();
        return Arrays.stream(FlowModes.values())
                .filter(s -> s.name().equals(uppercaseName))
                .findFirst().orElse(null);
    }
}
