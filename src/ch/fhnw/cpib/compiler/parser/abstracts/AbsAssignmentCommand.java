package ch.fhnw.cpib.compiler.parser.abstracts;

import ch.fhnw.cpib.compiler.error.GrammarError;
import ch.fhnw.cpib.compiler.parser.IAbstractNode;
import ch.fhnw.cpib.compiler.tokens.enums.types.IType;
import ch.fhnw.cpib.compiler.vm.ICodeArray;

public class AbsAssignmentCommand implements IAbstractNode {
    private final IAbstractNode absExpr1;
    private final IAbstractNode absExpr2;

    public AbsAssignmentCommand(IAbstractNode absExpr1, IAbstractNode absExpr2) {
        this.absExpr1 = absExpr1;
        this.absExpr2 = absExpr2;
    }

    @Override
    public IType check() throws GrammarError {
        return null;
    }

    @Override
    public int code(int loc) throws ICodeArray.CodeTooSmallError {
        return loc;
    }
}
