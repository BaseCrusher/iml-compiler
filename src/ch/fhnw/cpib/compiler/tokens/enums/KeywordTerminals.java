package ch.fhnw.cpib.compiler.tokens.enums;

import java.util.Arrays;

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
    LPAREN,
    COMMA,
    RPAREN,
    COLON,
    SEMICOLON,
    PIPE,
    LBRACK,
    RBRACK,
    SENTINEL;

    public static KeywordTerminals getByName(String name){
        String uppercaseName = name.toUpperCase();
        return Arrays.stream(KeywordTerminals.values())
                .filter(s -> s.name().equals(uppercaseName))
                .findFirst().orElse(null);
    }

}
