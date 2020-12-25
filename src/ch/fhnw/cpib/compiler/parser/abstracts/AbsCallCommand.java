package ch.fhnw.cpib.compiler.parser.abstracts;

import ch.fhnw.cpib.compiler.parser.IAbstractNode;

public class AbsCallCommand implements IAbstractNode {
    private final String identifier;
    private final IAbstractNode absExprList;
    private final IAbstractNode absOptGlobInits;

    public AbsCallCommand(String identifier, IAbstractNode absExprList, IAbstractNode absOptGlobInits) {
        this.identifier = identifier;
        this.absExprList = absExprList;
        this.absOptGlobInits = absOptGlobInits;
    }
}
