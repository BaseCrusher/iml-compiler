package ch.fhnw.cpib.compiler.parser;

import ch.fhnw.cpib.compiler.parser.nts.OptChangemode;
import ch.fhnw.cpib.compiler.parser.nts.OptFlowmode;
import ch.fhnw.cpib.compiler.parser.nts.OptMechmode;
import ch.fhnw.cpib.compiler.tokens.IToken;
import ch.fhnw.cpib.compiler.tokens.enums.modes.MechModes;

import static ch.fhnw.cpib.compiler.tokens.enums.modes.MechModes.COPY;

public class Variable {
    private String ident;
    private OptFlowmode flowMode;
    private OptMechmode mechmode;
    private OptChangemode changeMode;
    private IToken type;
    private int relAddress;
    private final boolean isArray;
    private int offset = 1;

    public Variable(String ident, OptFlowmode flowMode, OptChangemode changeMode, IToken type, int relAddress) {
        this.ident = ident;
        this.flowMode = flowMode;
        this.changeMode = changeMode;
        this.type = type;
        this.relAddress = relAddress;
        this.isArray = false;
    }

    public Variable(String ident, OptChangemode changeMode, IToken type, int relAddress) {
        this.ident = ident;
        this.changeMode = changeMode;
        this.type = type;
        this.relAddress = relAddress;
        this.isArray = false;
    }

    public Variable(String ident, OptFlowmode flowMode, OptMechmode mechmode, OptChangemode changeMode, IToken type, int relAddress) {
        this.ident = ident;
        this.flowMode = flowMode;
        this.mechmode = mechmode;
        this.changeMode = changeMode;
        this.type = type;
        this.relAddress = relAddress;
        this.isArray = false;
    }

    public Variable(String ident, IToken type, int relAddress) {
        this.ident = ident;
        this.type = type;
        this.relAddress = relAddress;
        this.isArray = false;
    }

    public Variable(String ident, OptFlowmode flowMode, OptMechmode mechmode, OptChangemode changeMode, IToken type, int relAddress, boolean isArray) {
        this.ident = ident;
        this.flowMode = flowMode;
        this.mechmode = mechmode;
        this.changeMode = changeMode;
        this.type = type;
        this.relAddress = relAddress;
        this.isArray = isArray;
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

    public int getOffset() {
        return offset;
    }

    public boolean isArray() {
        return isArray;
    }
}
