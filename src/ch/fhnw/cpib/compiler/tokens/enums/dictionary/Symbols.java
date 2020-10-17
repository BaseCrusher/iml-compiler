package ch.fhnw.cpib.compiler.tokens.enums.dictionary;

public enum Symbols {
    LPAREN("("),
    COMMA(","),
    RPAREN(")"),
    COLON(":"),
    SEMICOLON(";"),
    BECOMES(":="),
    AND("/\\?"),
    OR("\\/?"),
    EQ("="),
    NE("/="),
    LT("<"),
    GT(">"),
    LE("<="),
    GE(">="),
    PLUS("+"),
    MINUS("-"),
    TIMES("*"),
    LBRACK("["),
    RBRACK("]"),
    PIPE("|"),
    COMMENT("//");

    private final String charVal;

    Symbols(String s) {
        this.charVal = s;
    }

    public static int contains(String value) {
        int num = 0;
        for (Symbols s : values()) {
            if (s.charVal.contains(value)) {
                num++;
            }
        }
        return num;
    }

    public static Symbols getByName(String name){
        Symbols symbol = null;

        try {
            symbol = Symbols.valueOf(name);
        }catch (IllegalArgumentException ignored){}
        return symbol;
    }

}
