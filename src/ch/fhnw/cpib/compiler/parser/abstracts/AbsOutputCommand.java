package ch.fhnw.cpib.compiler.parser.abstracts;

import ch.fhnw.cpib.compiler.parser.IAbstractNode;

public class AbsOutputCommand implements IAbstractNode {
    private final IAbstractNode absExpr;

    public AbsOutputCommand(IAbstractNode absExpr) {
        this.absExpr = absExpr;
    }
}
