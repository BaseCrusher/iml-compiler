package ch.fhnw.cpib.compiler.parser.abstracts;

import ch.fhnw.cpib.compiler.parser.IAbstractNode;

public class AbsInputCommand implements IAbstractNode {
    private final IAbstractNode absExpr;

    public AbsInputCommand(IAbstractNode absExpr) {
        this.absExpr = absExpr;
    }
}
