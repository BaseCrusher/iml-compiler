package ch.fhnw.cpib.compiler.parser.abstracts;

import java.util.List;

import ch.fhnw.cpib.compiler.error.CodeGenError;
import ch.fhnw.cpib.compiler.error.TypeCheckError;
import ch.fhnw.cpib.compiler.parser.IAbstractNode;
import ch.fhnw.cpib.compiler.parser.Routine;
import ch.fhnw.cpib.compiler.parser.nts.Identifier;
import ch.fhnw.cpib.compiler.tokens.enums.types.IType;
import ch.fhnw.cpib.compiler.vm.ICodeArray;
import ch.fhnw.cpib.compiler.vm.IInstructions;

import static ch.fhnw.cpib.compiler.codeGenerator.CodeGenerator.codeArray;
import static ch.fhnw.cpib.compiler.parser.abstracts.AbsProgram.callMap;
import static ch.fhnw.cpib.compiler.tokens.enums.types.VoidType.VOID;

public class RoutineCall implements IAbstractNode {

    private final Identifier identifier;
    private final List<IAbstractNode> exprList;

    public RoutineCall(Identifier identifier, List<IAbstractNode> exprList) {
        this.identifier = identifier;
        this.exprList = exprList;
    }

    @Override
    public IType check() throws TypeCheckError {
        for (IAbstractNode param : exprList) {
            param.check();
        }
        Routine routine = identifier.getEnvironment().getRoutine(identifier.getIdent().getValue());
        if (routine == null) throw new TypeCheckError("Routine " + identifier.getIdent().getValue() + " was not declared");
        return routine.getReturnType();
    }

    @Override
    public int code(int loc) throws CodeGenError, ICodeArray.CodeTooSmallError {
        Routine routine = identifier.getEnvironment().getRoutine(identifier.getIdent().getValue());
        if (routine == null) {
            throw new CodeGenError("Routine not found in environment " + identifier.getIdent().getValue());
        }
        boolean isFunction = routine.getReturnType() != VOID;
        if (isFunction) {
            codeArray.put(loc, new IInstructions.AllocBlock(1));
            loc++;
        }

        for (IAbstractNode expr : exprList) {
            loc = expr.code(loc);
        }

        callMap.put(loc, identifier.getIdent().getValue());
        return loc + 1;

    }
}
