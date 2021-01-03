package ch.fhnw.cpib.compiler.parser.abstracts;

import ch.fhnw.cpib.compiler.error.GrammarError;
import ch.fhnw.cpib.compiler.error.TypeCheckError;
import ch.fhnw.cpib.compiler.parser.IAbstractNode;
import ch.fhnw.cpib.compiler.tokens.enums.types.IType;
import ch.fhnw.cpib.compiler.tokens.enums.types.Types;
import ch.fhnw.cpib.compiler.vm.ICodeArray;

public class AbsTypedIdent implements IAbstractNode {

    private final String ident;
    private final String type;
    private final IAbstractNode optArrDecl;

    public AbsTypedIdent(String ident, String type, IAbstractNode optArrDecl) {
        this.ident = ident;
        this.type = type;
        this.optArrDecl = optArrDecl;
    }

    @Override
    public IType check() throws TypeCheckError {
        if (optArrDecl != null) {
            return optArrDecl.check();

        }
        return Types.getByName(type);
    }

    @Override
    public int code(int loc) throws ICodeArray.CodeTooSmallError {
        return loc;
    }
}
