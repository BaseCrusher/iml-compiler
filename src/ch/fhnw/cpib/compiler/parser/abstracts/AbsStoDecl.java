package ch.fhnw.cpib.compiler.parser.abstracts;

import ch.fhnw.cpib.compiler.parser.IAbstractNode;

public class AbsStoDecl implements IAbstractNode {


    private final IAbstractNode optChangemode;
    private final IAbstractNode typedIdent;

    public AbsStoDecl(IAbstractNode optChangemode, IAbstractNode typedIdent) {
        this.optChangemode = optChangemode;
        this.typedIdent = typedIdent;
    }
}
