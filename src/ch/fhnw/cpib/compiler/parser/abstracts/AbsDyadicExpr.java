package ch.fhnw.cpib.compiler.parser.abstracts;

import ch.fhnw.cpib.compiler.error.GrammarError;
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
    public IType check() throws GrammarError {
        IType leftType = leftAbsExpr.check();
        IType rightType = rightAbsExpr.check();
        if (leftType != rightType) {
            throw new GrammarError("left and right type need to have matching types");
        }
        if (leftType == VOID) {
            throw new GrammarError("left type cant be of type void.");
        }

        switch (operator) {
            case "CON_OR":
            case "CON_AND":
                if (leftType == BoolTypes.BOOL) {
                    return BoolTypes.BOOL;
                }
                else {
                    throw new GrammarError("Operator type mismatch");
                }
            case "GE":
            case "LE":
            case "GT":
            case "LT":
                if (leftType == Inttypes.INT32 || leftType == Inttypes.INT64 || leftType == Inttypes.INT1024 ) {
                    return BoolTypes.BOOL;
                }
                else {
                    throw new GrammarError("Operator type mismatch");
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
                    throw new GrammarError("Operator type mismatch");
                }
            case "EQ":
            case "NEQ":
                return BoolTypes.BOOL;
            default:
                throw new GrammarError("Operator is not diadic");
        }
    }

    @Override
    public int code(int loc) throws ICodeArray.CodeTooSmallError {
        int leftLoc = leftAbsExpr.code(loc);
        int rightLoc = rightAbsExpr.code(leftLoc);
        switch (operator) {
            case "CON_OR":
            case "TIMES":
                codeArray.put(rightLoc, new IInstructions.MultInt());
                return rightLoc + 1;
            case "CON_AND":
            case "PLUS":
                codeArray.put(rightLoc, new IInstructions.AddInt());
                return rightLoc + 1;
            case "GE":
                codeArray.put(rightLoc, new IInstructions.GeInt());
                return rightLoc + 1;
            case "LE":
                codeArray.put(rightLoc, new IInstructions.LeInt());
                return rightLoc + 1;
            case "GT":
                codeArray.put(rightLoc, new IInstructions.GtInt());
                return rightLoc + 1;
            case "LT":
                codeArray.put(rightLoc, new IInstructions.LtInt());
                return rightLoc + 1;
            case "MINUS":
                codeArray.put(rightLoc, new IInstructions.SubInt());
                return rightLoc + 1;
            case "DIV_T":
                codeArray.put(rightLoc, new IInstructions.DivTruncInt());
                return rightLoc + 1;
            case "MOD_T":
                codeArray.put(rightLoc, new IInstructions.ModTruncInt());
                return rightLoc + 1;
            case "EQ":
                codeArray.put(rightLoc, new IInstructions.EqInt());
                return rightLoc + 1;
            case "NEQ":
                codeArray.put(rightLoc, new IInstructions.NeInt());
                return rightLoc + 1;
            default:
                throw new Error("Operator not supported by vm.");
        }
    }
}
