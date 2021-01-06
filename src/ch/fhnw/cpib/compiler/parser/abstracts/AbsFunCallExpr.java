package ch.fhnw.cpib.compiler.parser.abstracts;

import ch.fhnw.cpib.compiler.error.CodeGenError;
import ch.fhnw.cpib.compiler.error.TypeCheckError;
import ch.fhnw.cpib.compiler.parser.IAbstractNode;
import ch.fhnw.cpib.compiler.tokens.enums.types.IType;
import ch.fhnw.cpib.compiler.vm.ICodeArray;

public class AbsFunCallExpr implements IAbstractNode {


    private final RoutineCall routineCall;

    public AbsFunCallExpr(RoutineCall routineCall) {

        this.routineCall = routineCall;
    }

    @Override
    public IType check() throws TypeCheckError {
        return this.routineCall.check();
    }

    @Override
    public int code(int loc) throws ICodeArray.CodeTooSmallError, CodeGenError {
        loc = routineCall.code(loc);
        return loc;
    }

    public boolean lvalue() {
        return false;
    }
}
