package ch.fhnw.cpib.compiler.parser.abstracts;

import ch.fhnw.cpib.compiler.parser.IAbstractNode;
import ch.fhnw.cpib.compiler.parser.nts.Identifier;
import ch.fhnw.cpib.compiler.tokens.enums.types.IType;
import ch.fhnw.cpib.compiler.vm.ICodeArray;

public class AbsArrExpr implements IAbstractNode {
    private final Identifier identifier;
    private final IAbstractNode exp;
    private String optInit;

    public AbsArrExpr(Identifier identifier, IAbstractNode exp, String optInit) {
        this.identifier = identifier;
        this.exp = exp;
        this.optInit = optInit;
    }
    public AbsArrExpr(Identifier identifier, IAbstractNode exp) {
        this.identifier = identifier;
        this.exp = exp;
    }

    @Override
    public IType check() {

        return null;
    }

    @Override
    public int code(int loc) throws ICodeArray.CodeTooSmallError {
        return 0;
    }
}
