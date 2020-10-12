package ch.fhnw.cpib.compiler.tokens.enums;

import ch.fhnw.cpib.compiler.tokens.enums.modes.ChangeModes;

public enum Inttypes implements IInttype {
    INT32,
    INT64,
    INT1024;

    public static IInttype getByName(String name){
        Inttypes mode = null;
        try {
            mode = Inttypes.valueOf(name);
        }catch (IllegalArgumentException ignored){}
        return mode;
    }
}
