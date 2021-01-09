package ch.fhnw.cpib.compiler.parser.abstracts;

import ch.fhnw.cpib.compiler.error.CodeGenError;
import ch.fhnw.cpib.compiler.error.GrammarError;
import ch.fhnw.cpib.compiler.error.TypeCheckError;
import ch.fhnw.cpib.compiler.parser.IAbstractNode;
import ch.fhnw.cpib.compiler.parser.Routine;
import ch.fhnw.cpib.compiler.parser.Variable;
import ch.fhnw.cpib.compiler.parser.nts.Identifier;
import ch.fhnw.cpib.compiler.tokens.IToken;
import ch.fhnw.cpib.compiler.tokens.enums.types.IType;
import ch.fhnw.cpib.compiler.vm.ICodeArray;

import java.util.List;

import static ch.fhnw.cpib.compiler.tokens.enums.types.VoidType.VOID;

public class AbsCallCommand implements IAbstractNode {
    private final Identifier identifier;
    private final List<IAbstractNode> absExprList;
    private final List<String> absOptGlobInits;
    private final RoutineCall routineCall;

    public AbsCallCommand(Identifier identifier, List<IAbstractNode> absExprList, List<String> absOptGlobInits) {
        this.identifier = identifier;
        this.absExprList = absExprList;
        this.absOptGlobInits = absOptGlobInits;
        routineCall = new RoutineCall(identifier, absExprList);
    }

    @Override
    public IType check() throws TypeCheckError {
        for (IAbstractNode expr : absExprList) {
            expr.check();
        }
        return VOID;
    }

    @Override
    public int code(int loc) throws ICodeArray.CodeTooSmallError, CodeGenError {
        loc = routineCall.code(loc);
        return loc;
    }
}
