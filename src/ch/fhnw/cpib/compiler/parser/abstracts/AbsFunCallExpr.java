package ch.fhnw.cpib.compiler.parser.abstracts;

import ch.fhnw.cpib.compiler.error.GrammarError;
import ch.fhnw.cpib.compiler.parser.IAbstractNode;
import ch.fhnw.cpib.compiler.tokens.enums.types.IType;

public class AbsFunCallExpr implements IAbstractNode {


    private final RoutineCall routineCall;

    public AbsFunCallExpr(RoutineCall routineCall) {

        this.routineCall = routineCall;
    }

    @Override
    public IType check() throws GrammarError {
        return this.routineCall.check();
    }

    @Override
    public int code(int loc) {
        return loc;
    }

    public boolean lvalue() {
        return false;
    }
}
