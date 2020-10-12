package ch.fhnw.cpib.compiler.tokens.enums;

import ch.fhnw.cpib.compiler.tokens.enums.modes.ChangeModes;

public enum KeywordTerminals implements ITerminal {
    PROGRAM,
    ENDPROGRAM,
    CALL,
    IF,
    ELSE,
    ELSEIF,
    ENDIF,
    WHILE,
    DO,
    ENDWHILE,
    BECOMES,
    DEBUGIN,
    DEBUGOUT,
    FUN,
    ENDFUN,
    GLOBAL,
    LOCAL,
    INIT,
    PROC,
    ENDPROC,
    RETURNS,
    SKIP,
    THEN,
    SENTINEL;

    public static KeywordTerminals getByName(String name){
        KeywordTerminals mode = null;
        try {
            mode = KeywordTerminals.valueOf(name);
        }catch (IllegalArgumentException ignored){}
        return mode;
    }

}
