package ch.fhnw.cpib.compiler.parser.abstracts;

import ch.fhnw.cpib.compiler.error.CodeGenError;
import ch.fhnw.cpib.compiler.error.GrammarError;
import ch.fhnw.cpib.compiler.error.TypeCheckError;
import ch.fhnw.cpib.compiler.parser.IAbstractNode;
import ch.fhnw.cpib.compiler.tokens.enums.types.BoolTypes;
import ch.fhnw.cpib.compiler.tokens.enums.types.IType;
import ch.fhnw.cpib.compiler.tokens.enums.types.Inttypes;
import ch.fhnw.cpib.compiler.vm.ICodeArray;
import ch.fhnw.cpib.compiler.vm.IInstructions;

import static ch.fhnw.cpib.compiler.codeGenerator.CodeGenerator.codeArray;
import static ch.fhnw.cpib.compiler.tokens.enums.types.VoidType.VOID;

public class AbsDyadicExpr implements IAbstractNode {
    private final String operator;
    private final IAbstractNode leftAbsExpr;
    private final IAbstractNode rightAbsExpr;

    public AbsDyadicExpr(String operator, IAbstractNode leftAbsExpr, IAbstractNode rightAbsExpr) {
        this.operator = operator;
        this.leftAbsExpr = leftAbsExpr;
        this.rightAbsExpr = rightAbsExpr;
    }

    @Override
    public IType check() throws TypeCheckError {
        IType leftType = leftAbsExpr.check();
        IType rightType = rightAbsExpr.check();
        if (leftType != rightType) {
            throw new TypeCheckError("left and right type need to have matching types");
        }
        if (leftType == VOID) {
            throw new TypeCheckError("left type cant be of type void.");
        }

        switch (operator) {
            case "CON_OR":
            case "CON_AND":
                if (leftType == BoolTypes.BOOL) {
                    return BoolTypes.BOOL;
                }
                else {
                    throw new TypeCheckError("Operator type mismatch");
                }
            case "GE":
            case "LE":
            case "GT":
            case "LT":
                if (leftType == Inttypes.INT32 || leftType == Inttypes.INT64 || leftType == Inttypes.INT1024 ) {
                    return BoolTypes.BOOL;
                }
                else {
                    throw new TypeCheckError("Operator type mismatch");
                }
            case "PLUS":
            case "MINUS":
            case "DIV_E":
            case "MOD_E":
            case "DIV_F":
            case "MOD_F":
            case "DIV_T":
            case "MOD_T":
            case "TIMES":
                if (leftType == Inttypes.INT32 || leftType == Inttypes.INT64 || leftType == Inttypes.INT1024 ) {
                    return leftType;
                }
                else {
                    throw new TypeCheckError("Operator type mismatch");
                }
            case "EQ":
            case "NEQ":
                return BoolTypes.BOOL;
            default:
                throw new TypeCheckError("Operator is not diadic");
        }
    }

    @Override
    public int code(int loc) throws ICodeArray.CodeTooSmallError, CodeGenError {
        int leftLoc = leftAbsExpr.code(loc);
        codeArray.put(leftLoc, new IInstructions.Deref());
        leftLoc++;
        int rightLoc = rightAbsExpr.code(leftLoc);
        switch (operator) {
            case "CON_OR":
            case "TIMES":
                codeArray.put(rightLoc, new IInstructions.MultInt());
                rightLoc++;
                break;
            case "CON_AND":
            case "PLUS":
                codeArray.put(rightLoc, new IInstructions.AddInt());
                rightLoc++;
                break;
            case "GE":
                codeArray.put(rightLoc, new IInstructions.GeInt());
                rightLoc++;
                break;
            case "LE":
                codeArray.put(rightLoc, new IInstructions.LeInt());
                rightLoc++;
                break;
            case "GT":
                codeArray.put(rightLoc, new IInstructions.GtInt());
                rightLoc++;
                break;
            case "LT":
                codeArray.put(rightLoc, new IInstructions.LtInt());
                rightLoc++;
                break;
            case "MINUS":
                codeArray.put(rightLoc, new IInstructions.SubInt());
                rightLoc++;
                break;
            case "DIV_T":
                codeArray.put(rightLoc, new IInstructions.DivTruncInt());
                rightLoc++;
                break;
            case "MOD_T":
                codeArray.put(rightLoc, new IInstructions.ModTruncInt());
                rightLoc++;
                break;
            case "EQ":
                codeArray.put(rightLoc, new IInstructions.EqInt());
                rightLoc++;
                break;
            case "NEQ":
                codeArray.put(rightLoc, new IInstructions.NeInt());
                rightLoc++;
                break;
            default:
                throw new Error("Operator not supported by vm.");
        }
        codeArray.put(rightLoc, new IInstructions.Store());
        return rightLoc + 1;
    }

    public boolean lvalue() {
        return false;
    }
}
