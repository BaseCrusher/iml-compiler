package ch.fhnw.cpib.compiler.parser.abstracts;

import ch.fhnw.cpib.compiler.error.GrammarError;
import ch.fhnw.cpib.compiler.error.TypeCheckError;
import ch.fhnw.cpib.compiler.parser.IAbstractNode;
import ch.fhnw.cpib.compiler.parser.nts.OptChangemode;
import ch.fhnw.cpib.compiler.parser.nts.OptFlowmode;
import ch.fhnw.cpib.compiler.parser.nts.OptMechmode;
import ch.fhnw.cpib.compiler.parser.nts.TypedIdent;
import ch.fhnw.cpib.compiler.tokens.enums.types.IType;
import ch.fhnw.cpib.compiler.vm.ICodeArray;

import static ch.fhnw.cpib.compiler.codeGenerator.CodeGenerator.codeArray;
import static ch.fhnw.cpib.compiler.tokens.enums.modes.MechModes.COPY;
import static ch.fhnw.cpib.compiler.tokens.enums.modes.MechModes.REF;

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
    public IType check() throws TypeCheckError {
        if (((AbsTypedIdent)absTypedIdent).getOptArrDecl() != null) {
            if (!optMechmode.getToken().getValue().equals(REF.name())) {
                throw new TypeCheckError("Arrays must be REF");
            }
        }
        return absTypedIdent.check();
    }

    @Override
    public int code(int loc) throws ICodeArray.CodeTooSmallError {
        return loc;
    }

    public OptMechmode getOptMechmode() {
        return optMechmode;
    }
}
