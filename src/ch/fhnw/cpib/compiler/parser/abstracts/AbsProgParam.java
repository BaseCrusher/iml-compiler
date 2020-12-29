package ch.fhnw.cpib.compiler.parser.abstracts;

import ch.fhnw.cpib.compiler.error.GrammarError;
import ch.fhnw.cpib.compiler.error.TypeCheckError;
import ch.fhnw.cpib.compiler.parser.IAbstractNode;
import ch.fhnw.cpib.compiler.parser.nts.OptChangemode;
import ch.fhnw.cpib.compiler.parser.nts.OptFlowmode;
import ch.fhnw.cpib.compiler.tokens.enums.types.IType;
import ch.fhnw.cpib.compiler.vm.ICodeArray;

public class AbsProgParam implements IAbstractNode {
    private final OptFlowmode optFlowmode;
    private final OptChangemode optChangemode;
    private final IAbstractNode absTypedIdent;

    public AbsProgParam(OptFlowmode optFlowmode, OptChangemode optChangemode, IAbstractNode absTypedIdent) {

        this.optFlowmode = optFlowmode;
        this.optChangemode = optChangemode;
        this.absTypedIdent = absTypedIdent;
    }

    @Override
    public IType check() throws TypeCheckError {
        return absTypedIdent.check();
    }

    @Override
    public int code(int loc) throws ICodeArray.CodeTooSmallError {
        return loc;
    }
}
