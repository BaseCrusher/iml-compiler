package ch.fhnw.cpib.compiler.parser.abstracts;

import ch.fhnw.cpib.compiler.parser.IAbstractNode;

public class AbsAssignmentCommand implements IAbstractNode {
    private final IAbstractNode absExpr1;
    private final IAbstractNode absExpr2;

    public AbsAssignmentCommand(IAbstractNode absExpr1, IAbstractNode absExpr2) {
        this.absExpr1 = absExpr1;
        this.absExpr2 = absExpr2;
    }
}
