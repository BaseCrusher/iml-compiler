package ch.fhnw.cpib.compiler.parser;

import ch.fhnw.cpib.compiler.tokens.IToken;

public class Variable {
    private String ident;
    private IAbstractNode flowMode;
    private IAbstractNode mechmode;
    private IAbstractNode changeMode;
    private IToken type;
    private int relAddress;

    public Variable(String ident, IAbstractNode flowMode, IAbstractNode changeMode, IToken type, int relAddress) {
        this.ident = ident;
        this.flowMode = flowMode;
        this.changeMode = changeMode;
        this.type = type;
        this.relAddress = relAddress;
    }

    public Variable(String ident, IAbstractNode changeMode, IToken type, int relAddress) {
        this.ident = ident;
        this.changeMode = changeMode;
        this.type = type;
        this.relAddress = relAddress;
    }

    public Variable(String ident, IAbstractNode flowMode, IAbstractNode mechmode, IAbstractNode changeMode, IToken type, int relAddress) {
        this.ident = ident;
        this.flowMode = flowMode;
        this.mechmode = mechmode;
        this.changeMode = changeMode;
        this.type = type;
        this.relAddress = relAddress;
    }
}
