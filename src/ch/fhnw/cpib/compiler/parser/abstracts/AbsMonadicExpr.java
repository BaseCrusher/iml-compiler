package ch.fhnw.cpib.compiler.parser.abstracts;

import ch.fhnw.cpib.compiler.error.CodeGenError;
import ch.fhnw.cpib.compiler.error.GrammarError;
import ch.fhnw.cpib.compiler.error.TypeCheckError;
import ch.fhnw.cpib.compiler.parser.IAbstractNode;
import ch.fhnw.cpib.compiler.tokens.enums.types.IType;
import ch.fhnw.cpib.compiler.vm.ICodeArray;
import ch.fhnw.cpib.compiler.vm.IInstructions;

import static ch.fhnw.cpib.compiler.codeGenerator.CodeGenerator.codeArray;
import static ch.fhnw.cpib.compiler.tokens.enums.operators.AddOperators.MINUS;
import static ch.fhnw.cpib.compiler.tokens.enums.operators.MonoOperators.NOT;
import static ch.fhnw.cpib.compiler.tokens.enums.types.BoolTypes.BOOL;
import static ch.fhnw.cpib.compiler.tokens.enums.types.Inttypes.INT1024;
import static ch.fhnw.cpib.compiler.tokens.enums.types.Inttypes.INT32;
import static ch.fhnw.cpib.compiler.tokens.enums.types.Inttypes.INT64;
import static ch.fhnw.cpib.compiler.tokens.enums.types.VoidType.VOID;

public class AbsMonadicExpr implements IAbstractNode {

    private String operator;
    private IAbstractNode factor;

    public AbsMonadicExpr(String operator, IAbstractNode factor) {
        this.operator = operator;
        this.factor = factor;
    }

    @Override
    public IType check() throws TypeCheckError {
        IType type = factor.check();
        if (type == VOID) throw new AssertionError("Unexpected VOID");

        if (operator.equals(MINUS.name())) {
            if (type == INT32 || type == INT64 || type == INT1024) {
                return type;
            } else {
                throw new TypeCheckError("Unexpected type" + type.toString() + " for unary MINUS");
            }
        }
        else if (operator.equals(NOT.name())) {
            if (type == BOOL) {
                return type;
            } else {
                throw new TypeCheckError("Unexpected type" + type.toString() + " for operator NOT");
            }
        }
        else throw new TypeCheckError(operator + " is not a monadic expression");
    }

    @Override
    public int code(int loc) throws ICodeArray.CodeTooSmallError, CodeGenError {
        int loc1 = factor.code(loc);

        if (operator.equals(MINUS.name())) {
            codeArray.put(loc1, new IInstructions.NegInt());
            return loc1 + 1;
        }
        else if (operator.equals(NOT.name())) {
            codeArray.put(loc1, new IInstructions.LoadImInt(0));
            int loc2 = loc1 + 1;
            codeArray.put(loc2, new IInstructions.EqInt());
            return loc2 + 1;
        }
        else throw new  AssertionError(operator + " is not a monadic expression");
    }

    public boolean lvalue() {
        return false;
    }
}
