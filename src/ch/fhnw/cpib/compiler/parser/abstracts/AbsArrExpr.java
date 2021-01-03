package ch.fhnw.cpib.compiler.parser.abstracts;

import ch.fhnw.cpib.compiler.error.CodeGenError;
import ch.fhnw.cpib.compiler.error.TypeCheckError;
import ch.fhnw.cpib.compiler.parser.Environment;
import ch.fhnw.cpib.compiler.parser.IAbstractNode;
import ch.fhnw.cpib.compiler.parser.Variable;
import ch.fhnw.cpib.compiler.parser.nts.Identifier;
import ch.fhnw.cpib.compiler.tokens.enums.types.IType;
import ch.fhnw.cpib.compiler.tokens.enums.types.Types;
import ch.fhnw.cpib.compiler.vm.ICodeArray;
import ch.fhnw.cpib.compiler.vm.IInstructions;

import static ch.fhnw.cpib.compiler.codeGenerator.CodeGenerator.codeArray;
import static ch.fhnw.cpib.compiler.tokens.enums.KeywordTerminals.BECOMES;
import static ch.fhnw.cpib.compiler.tokens.enums.types.Inttypes.INT32;

public class AbsArrExpr implements IAbstractNode {
    private final Identifier identifier;
    private boolean isAssignment = false;
    private final IAbstractNode exp;
    private String optInit;

    public AbsArrExpr(Identifier identifier, IAbstractNode exp, String optInit) {
        this.identifier = identifier;
        this.exp = exp;
        this.optInit = optInit;
    }
    public AbsArrExpr(Identifier identifier, IAbstractNode exp) {
        this.identifier = identifier;
        this.exp = exp;
    }

    public AbsArrExpr(Identifier identifier, IAbstractNode exp, boolean isAssignment) {
        this.identifier = identifier;
        this.exp = exp;
        this.isAssignment = isAssignment;
    }

    @Override
    public IType check() throws TypeCheckError {
        Variable variable = identifier.getEnvironment().getVariable(identifier.getIdent().getValue());
        if (variable.getType() == null) throw new TypeCheckError("undefined " + identifier.getIdent().getValue());
        IType exprType = exp.check();
        if (!exprType.equals(INT32)) {
            throw new TypeCheckError("expression in array is of wrong type");
        }
        return Types.getByName(variable.getType().getValue());
    }

    @Override
    public int code(int loc) throws ICodeArray.CodeTooSmallError, CodeGenError {
        Environment env = identifier.getEnvironment();
        // Check if in bounds of array
        loc = exp.code(loc);
        codeArray.put(loc, new IInstructions.LoadAddrRel(env.getRelAddress(identifier.getIdent().getValue())));
        loc++;
        codeArray.put(loc, new IInstructions.Deref());
        loc++;
        codeArray.put(loc, new IInstructions.GtInt());
        loc++;
        codeArray.put(loc, new IInstructions.LoadImInt(0));
        loc++;
        loc = exp.code(loc);
        codeArray.put(loc, new IInstructions.GeInt());
        loc++;
        codeArray.put(loc, new IInstructions.AddInt());
        loc ++;
        codeArray.put(loc, new IInstructions.CondJump(loc + 2));
        loc++;
        codeArray.put(loc, new IInstructions.ArrayOutOfBoundsException());
        loc++;

        // Get value at index
        codeArray.put(loc, new IInstructions.LoadAddrRel(env.getRelAddress(identifier.getIdent().getValue())));
        loc++;
        loc = exp.code(loc);
        codeArray.put(loc, new IInstructions.AddInt());
        loc++;
        if (!isAssignment) {
            codeArray.put(loc, new IInstructions.Deref());
            loc++;
        }
        return loc;
    }
}
