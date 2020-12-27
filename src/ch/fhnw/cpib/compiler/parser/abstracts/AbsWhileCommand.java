package ch.fhnw.cpib.compiler.parser.abstracts;

import ch.fhnw.cpib.compiler.parser.IAbstractNode;

import java.util.List;

public class AbsWhileCommand implements IAbstractNode {
    private final IAbstractNode absExpr;
    private final IAbstractNode absCpsCmd;

    public AbsWhileCommand(IAbstractNode absExpr, List<IAbstractNode> absCpsCmd) {
        this.absExpr = absExpr;
        this.absCpsCmd = absCpsCmd;
    }
}
