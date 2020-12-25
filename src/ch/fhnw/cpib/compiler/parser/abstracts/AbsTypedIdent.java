package ch.fhnw.cpib.compiler.parser.abstracts;

import ch.fhnw.cpib.compiler.parser.IAbstractNode;

public class AbsTypedIdent implements IAbstractNode {

    private final String ident;
    private final String type;
    private final IAbstractNode optArrDecl;

    public AbsProgram(String ident, String type, IAbstractNode optArrDecl) {
        this.ident = ident;
        this.type = type;
        this.optArrDecl = optArrDecl;
    }

}
