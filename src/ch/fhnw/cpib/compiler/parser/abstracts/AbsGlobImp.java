package ch.fhnw.cpib.compiler.parser.abstracts;

import ch.fhnw.cpib.compiler.error.GrammarError;
import ch.fhnw.cpib.compiler.parser.IAbstractNode;
import ch.fhnw.cpib.compiler.parser.nts.OptFlowmode;
import ch.fhnw.cpib.compiler.tokens.enums.modes.ChangeModes;
import ch.fhnw.cpib.compiler.tokens.enums.types.IType;
import ch.fhnw.cpib.compiler.vm.ICodeArray;

import static ch.fhnw.cpib.compiler.tokens.enums.types.VoidType.VOID;

public class AbsGlobImp implements IAbstractNode {

    private final OptFlowmode absFlowMode;
    private final ChangeModes absChangeMode;
    private final String ident;

    public AbsGlobImp(OptFlowmode absFlowMode, ChangeModes absChangeMode, String ident) {

        this.absFlowMode = absFlowMode;
        this.absChangeMode = absChangeMode;
        this.ident = ident;
    }

    @Override
    public IType check() {
        return VOID;
    }

    @Override
    public int code(int loc) throws ICodeArray.CodeTooSmallError {
        return loc;
    }
}
