package ch.fhnw.cpib.compiler.tokens.enums.modes;

import ch.fhnw.cpib.compiler.tokens.enums.Inttypes;

public enum MechModes implements IMechMode {
    
    COPY,
    REF;

    public static IMechMode getByName(String name){
        MechModes mode = null;
        try {
            mode = MechModes.valueOf(name);
        }catch (IllegalArgumentException ignored){}
        return mode;
    }
}
