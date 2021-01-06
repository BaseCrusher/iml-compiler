package ch.fhnw.cpib.compiler.parser.abstracts;

import ch.fhnw.cpib.compiler.error.CodeGenError;
import ch.fhnw.cpib.compiler.error.TypeCheckError;
import ch.fhnw.cpib.compiler.parser.IAbstractNode;
import ch.fhnw.cpib.compiler.tokens.enums.types.IType;
import ch.fhnw.cpib.compiler.vm.ICodeArray;

import static ch.fhnw.cpib.compiler.tokens.enums.types.VoidType.VOID;

public class AbsArrDecl implements IAbstractNode {

    private final IAbstractNode arrDecl;

    public AbsArrDecl(IAbstractNode arrDecl) {
        this.arrDecl = arrDecl;
    }

    @Override
    public IType check() throws TypeCheckError {
        if (arrDecl != null) {
            return arrDecl.check();
        }
        return VOID;
    }

    @Override
    public int code(int loc) throws ICodeArray.CodeTooSmallError, CodeGenError {
        loc = arrDecl.code(loc);
        return loc;
    }
}
