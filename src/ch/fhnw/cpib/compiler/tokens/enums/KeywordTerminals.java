package ch.fhnw.cpib.compiler.tokens.enums;

public enum KeywordTerminals implements ITerminal {
    WHILE,
    DO,
    BECOMES,
    ENDWHILE,
    SENTINEL;
    
    
    public static boolean contains(String value) {
        for (KeywordTerminals kt : values()) {
            return kt.toString().equals(value);
        }
        return false;
    }
    
    
}
