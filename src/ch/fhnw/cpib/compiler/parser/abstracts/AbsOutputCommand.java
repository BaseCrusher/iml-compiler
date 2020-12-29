package ch.fhnw.cpib.compiler.parser.abstracts;

import ch.fhnw.cpib.compiler.error.GrammarError;
import ch.fhnw.cpib.compiler.parser.IAbstractNode;
import ch.fhnw.cpib.compiler.tokens.enums.types.IType;
import ch.fhnw.cpib.compiler.vm.ICodeArray;

public class AbsOutputCommand implements IAbstractNode {
    private final IAbstractNode absExpr;

    public AbsOutputCommand(IAbstractNode absExpr) {
        this.absExpr = absExpr;
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
