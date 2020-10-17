package ch.fhnw.cpib.compiler.tokens.enums;

import ch.fhnw.cpib.compiler.tokens.enums.modes.ChangeModes;

public enum KeywordTerminals implements ITerminal {
    PROGRAM,
    DO,
    ENDPROGRAM,
    CALL,
    IF,
    ELSE,
    ELSEIF,
    ENDIF,
    WHILE,
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
    NOT,
    SENTINEL;

    public static KeywordTerminals getByName(String name){
        KeywordTerminals mode = null;
        try {
            mode = KeywordTerminals.valueOf(name);
        }catch (IllegalArgumentException ignored){}
        return mode;
    }

}
