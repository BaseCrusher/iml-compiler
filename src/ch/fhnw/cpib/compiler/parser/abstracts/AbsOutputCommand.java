package ch.fhnw.cpib.compiler.parser.abstracts;

import ch.fhnw.cpib.compiler.error.TypeCheckError;
import ch.fhnw.cpib.compiler.parser.IAbstractNode;
import ch.fhnw.cpib.compiler.parser.nts.Expr;
import ch.fhnw.cpib.compiler.tokens.enums.types.IType;
import ch.fhnw.cpib.compiler.vm.ICodeArray;
import ch.fhnw.cpib.compiler.vm.IInstructions;

import static ch.fhnw.cpib.compiler.codeGenerator.CodeGenerator.codeArray;
import static ch.fhnw.cpib.compiler.tokens.enums.types.BoolTypes.BOOL;
import static ch.fhnw.cpib.compiler.tokens.enums.types.VoidType.VOID;

public class AbsOutputCommand implements IAbstractNode {
    private final Expr expr;
    private final IAbstractNode absExpr;
    private IType type;

    public AbsOutputCommand(Expr expr) {
        this.expr = expr;
        this.absExpr = expr.toAbsSyn();
    }

    @Override
    public IType check() throws TypeCheckError {
        type = absExpr.check();
        return VOID;
    }

    @Override
    public int code(int loc) throws ICodeArray.CodeTooSmallError {
        if (type.equals(BOOL)) {
            codeArray.put(loc, new IInstructions.OutputBool("output"));
            return loc + 1;
        }
        codeArray.put(loc, new IInstructions.OutputInt("output"));
        return loc + 1;
    }
}
