package ch.fhnw.cpib.compiler.tokens.enums;

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


    public static boolean contains(String value) {
        for (KeywordTerminals kt : values()) {
            return kt.toString().equals(value);
        }
        return false;
    }


}
