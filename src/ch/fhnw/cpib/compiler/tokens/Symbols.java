package ch.fhnw.cpib.compiler.tokens;

public enum Symbols {
    EQUALS('='),
    LT('<'),
    GT('>'),
    PLUS('+'),
    MINUS('-'),
    SLASH('/'),
    ASTERISK('*'),
    EXCLAMARK('!'),
    SEMICOLON(';'),
    COLON(':'),
    COMMA(','),
    LBRACK('('),
    RBRACK(')'),
    LBRACE('{'),
    RBRACE('}'),
    LSQBRACK('['),
    RSQBRACK(']');

    private int charVal;

    private Symbols(int charVal) {
        this.charVal = charVal;
    }

    public static boolean contains(int value) {
        for (Symbols s : values()) {
            return value == s.charVal;
        }
        return false;
    }

}
