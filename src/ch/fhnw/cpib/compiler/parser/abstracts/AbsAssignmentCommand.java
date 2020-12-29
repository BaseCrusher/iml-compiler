package ch.fhnw.cpib.compiler.parser.abstracts;

import ch.fhnw.cpib.compiler.error.CodeGenError;
import ch.fhnw.cpib.compiler.error.GrammarError;
import ch.fhnw.cpib.compiler.error.TypeCheckError;
import ch.fhnw.cpib.compiler.parser.IAbstractNode;
import ch.fhnw.cpib.compiler.tokens.enums.types.IType;
import ch.fhnw.cpib.compiler.vm.ICodeArray;
import ch.fhnw.cpib.compiler.vm.IInstructions;

import static ch.fhnw.cpib.compiler.codeGenerator.CodeGenerator.codeArray;
import static ch.fhnw.cpib.compiler.tokens.enums.types.BoolTypes.BOOL;
import static ch.fhnw.cpib.compiler.tokens.enums.types.VoidType.VOID;

public class AbsAssignmentCommand implements IAbstractNode {
    private final IAbstractNode assignmentVar;
    private final IAbstractNode expr;
    private IType varType;

    public AbsAssignmentCommand(IAbstractNode assignmentVar, IAbstractNode expr) {
        this.assignmentVar = assignmentVar;
        this.expr = expr;
    }

    @Override
    public IType check() throws TypeCheckError {
        varType = assignmentVar.check();
        IType exprType = expr.check();
        if (varType != exprType) {
            throw new TypeCheckError("Assignment var " + varType.toString() + " is not compatible with expression var " + exprType);
        }
        return VOID;
    }

    @Override
    public int code(int loc) throws ICodeArray.CodeTooSmallError, CodeGenError {
        loc = assignmentVar.code(loc);
        return expr.code(loc);
    }
}
