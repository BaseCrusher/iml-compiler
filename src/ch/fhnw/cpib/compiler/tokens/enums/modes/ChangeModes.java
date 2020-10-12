package ch.fhnw.cpib.compiler.tokens.enums.modes;

import ch.fhnw.cpib.compiler.tokens.enums.Inttypes;
import ch.fhnw.cpib.compiler.tokens.enums.dictionary.Symbols;

public enum ChangeModes implements IChangeMode {

    CONST,
    VAR;

    public static ChangeModes getByName(String name){
        ChangeModes mode = null;
        try {
            mode = ChangeModes.valueOf(name);
        }catch (IllegalArgumentException ignored){}
        return mode;
    }
}
