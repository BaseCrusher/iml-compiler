package ch.fhnw.cpib.compiler.parser.abstracts;

import ch.fhnw.cpib.compiler.error.CodeGenError;
import ch.fhnw.cpib.compiler.error.GrammarError;
import ch.fhnw.cpib.compiler.error.TypeCheckError;
import ch.fhnw.cpib.compiler.parser.IAbstractNode;
import ch.fhnw.cpib.compiler.parser.nts.Expr;
import ch.fhnw.cpib.compiler.tokens.enums.types.IType;
import ch.fhnw.cpib.compiler.vm.ICodeArray;
import ch.fhnw.cpib.compiler.vm.IInstructions;
import ch.fhnw.cpib.compiler.vm.IVirtualMachine;

import static ch.fhnw.cpib.compiler.codeGenerator.CodeGenerator.codeArray;
import static ch.fhnw.cpib.compiler.tokens.enums.types.BoolTypes.BOOL;
import static ch.fhnw.cpib.compiler.tokens.enums.types.Inttypes.INT1024;
import static ch.fhnw.cpib.compiler.tokens.enums.types.Inttypes.INT32;
import static ch.fhnw.cpib.compiler.tokens.enums.types.Inttypes.INT64;
import static ch.fhnw.cpib.compiler.tokens.enums.types.VoidType.VOID;

public class AbsInputCommand implements IAbstractNode {
    private final Expr expr;
    private final IAbstractNode absExpr;

    public AbsInputCommand(Expr expr) {
        this.expr = expr;
        this.absExpr = expr.toAbsSyn();
    }

    @Override
    public IType check() throws TypeCheckError {
        absExpr.check();
        return VOID;
    }

    @Override
    public int code(int loc) throws ICodeArray.CodeTooSmallError, CodeGenError {
        int loc1 = absExpr.code(loc);
        try {
            // retrieve type from expression using check() as a hack :)
            IType runtimeType = expr.toAbsSyn().check();
            String var = expr.getIdentifier().getIdent().getValue();
            if (runtimeType.toString().equals(BOOL.name())) {
                codeArray.put(loc1, new IInstructions.InputBool(var));
            } else if (runtimeType.toString().equals(INT32.name())
                    || runtimeType.toString().equals(INT64.name())
                    || runtimeType.toString().equals(INT1024.name())) {
                codeArray.put(loc1, new IInstructions.InputInt(var));
            } else {
                throw new IVirtualMachine.ExecutionError("Unsupported type " + runtimeType.toString());
            }
        } catch (TypeCheckError | IVirtualMachine.ExecutionError e) {
            e.printStackTrace();
        }
        return loc1 + 1;
    }
}
