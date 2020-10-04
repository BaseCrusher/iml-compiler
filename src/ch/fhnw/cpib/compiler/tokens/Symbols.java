package ch.fhnw.cpib.compiler.tokens;

public enum Symbols {
    LPAREN("("),
    RPAREN(")"),
    COMMA(","),
    SEMICOLON(";"),
    COLON(":"),
    BECOMES(":="),
    TIMES("*"),
    PLUS("+"),
    MINUS("-"),
    EQ("="),
    NE("/="),
    LT("<"),
    GT(">"),
    LE("<="),
    GE(">=");

    /*
    SLASH("/"),
    BACKSLASH("\\"),
    EXCLAMARK("!"),
    LBRACE("{"),
    RBRACE("}"),
    LSQBRACK("["),
    RSQBRACK("]");
    */

    private String charVal;

    private Symbols(String s) {
        this.charVal = s;
    }

    public static boolean contains(int value) {
        for (Symbols s : values()) {
            return value == s.charVal;
        }
        return false;
    }

}
