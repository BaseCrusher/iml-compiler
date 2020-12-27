package ch.fhnw.cpib.compiler.parser;

import ch.fhnw.cpib.compiler.parser.nts.OptChangemode;
import ch.fhnw.cpib.compiler.parser.nts.OptFlowmode;
import ch.fhnw.cpib.compiler.parser.nts.OptMechmode;
import ch.fhnw.cpib.compiler.tokens.IToken;

public class Variable {
    private String ident;
    private OptFlowmode flowMode;
    private OptMechmode mechmode;
    private OptChangemode changeMode;
    private IToken type;
    private int relAddress;

    public Variable(String ident, OptFlowmode flowMode, OptChangemode changeMode, IToken type, int relAddress) {
        this.ident = ident;
        this.flowMode = flowMode;
        this.changeMode = changeMode;
        this.type = type;
        this.relAddress = relAddress;
    }

    public Variable(String ident, OptChangemode changeMode, IToken type, int relAddress) {
        this.ident = ident;
        this.changeMode = changeMode;
        this.type = type;
        this.relAddress = relAddress;
    }

    public Variable(String ident, OptFlowmode flowMode, OptMechmode mechmode, OptChangemode changeMode, IToken type, int relAddress) {
        this.ident = ident;
        this.flowMode = flowMode;
        this.mechmode = mechmode;
        this.changeMode = changeMode;
        this.type = type;
        this.relAddress = relAddress;
    }

    public String getIdent() {
        return ident;
    }

    public IToken getType() {
        return type;
    }

    public int getRelAddress() {
        return relAddress;
    }

    public OptFlowmode getFlowMode() {
        return flowMode;
    }

    public OptMechmode getMechmode() {
        return mechmode;
    }

    public OptChangemode getChangeMode() {
        return changeMode;
    }
}
