package ch.fhnw.cpib.compiler.parser.abstracts;

import ch.fhnw.cpib.compiler.parser.IAbstractNode;
import ch.fhnw.cpib.compiler.tokens.IToken;
import ch.fhnw.cpib.compiler.tokens.enums.types.IType;
import ch.fhnw.cpib.compiler.vm.ICodeArray;
import ch.fhnw.cpib.compiler.vm.IInstructions;

import static ch.fhnw.cpib.compiler.codeGenerator.CodeGenerator.codeArray;
import static ch.fhnw.cpib.compiler.tokens.enums.types.BoolTypes.BOOL;

public class AbsBoolLiteralExpr implements IAbstractNode {
    private IToken literal;

    public AbsBoolLiteralExpr(IToken literal) {
        this.literal = literal;
    }

    @Override
    public IType check() {
        return BOOL;
    }

    @Override
    public int code(int loc) throws ICodeArray.CodeTooSmallError {
        var value = 0;
        if (Boolean.parseBoolean(literal.getValue())) {
            value = 1;
        }
        codeArray.put(loc, new IInstructions.LoadImInt(value));
        return loc + 1;
    }

    public boolean lvalue() {
        return false;
    }
}
