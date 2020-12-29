package ch.fhnw.cpib.compiler.parser.abstracts;

import ch.fhnw.cpib.compiler.error.GrammarError;
import ch.fhnw.cpib.compiler.parser.IAbstractNode;
import ch.fhnw.cpib.compiler.parser.nts.OptChangemode;
import ch.fhnw.cpib.compiler.parser.nts.OptFlowmode;
import ch.fhnw.cpib.compiler.parser.nts.OptMechmode;
import ch.fhnw.cpib.compiler.tokens.enums.types.IType;
import ch.fhnw.cpib.compiler.vm.ICodeArray;

public class AbsParam implements IAbstractNode {
    private final OptFlowmode optFlowmode;
    private final OptMechmode optMechmode;
    private final OptChangemode optChangemode;
    private final IAbstractNode absTypedIdent;

    public AbsParam(OptFlowmode optFlowmode, OptMechmode optMechmode, OptChangemode optChangemode, IAbstractNode absTypedIdent) {

        this.optFlowmode = optFlowmode;
        this.optMechmode = optMechmode;
        this.optChangemode = optChangemode;
        this.absTypedIdent = absTypedIdent;
    }

    @Override
    public IType check() {
        return null;
    }

    @Override
    public int code(int loc) throws ICodeArray.CodeTooSmallError {
        return loc;
    }
}
