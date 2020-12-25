package ch.fhnw.cpib.compiler.parser.abstracts;

import ch.fhnw.cpib.compiler.parser.IAbstractNode;

public class AbsConditionalCommand implements IAbstractNode {
    private final IAbstractNode absExpr;
    private final IAbstractNode thenCommand;
    private final IAbstractNode elseCommand;

    public AbsConditionalCommand(IAbstractNode absExpr, IAbstractNode thenCommand, IAbstractNode elseCommand) {
        this.absExpr = absExpr;
        this.thenCommand = thenCommand;
        this.elseCommand = elseCommand;
    }
}
