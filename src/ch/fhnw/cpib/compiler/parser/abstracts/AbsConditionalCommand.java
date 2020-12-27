package ch.fhnw.cpib.compiler.parser.abstracts;

import ch.fhnw.cpib.compiler.parser.IAbstractNode;

import java.util.List;

public class AbsConditionalCommand implements IAbstractNode {
    private final IAbstractNode absExpr;
    private final List<IAbstractNode> thenCommand;
    private final List<IAbstractNode> elseCommand;

    public AbsConditionalCommand(IAbstractNode absExpr, List<IAbstractNode> thenCommand, List<IAbstractNode> elseCommand) {
        this.absExpr = absExpr;
        this.thenCommand = thenCommand;
        this.elseCommand = elseCommand;
    }

}
