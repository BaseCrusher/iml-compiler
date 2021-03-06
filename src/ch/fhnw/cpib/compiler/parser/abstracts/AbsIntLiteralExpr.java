package ch.fhnw.cpib.compiler.parser.abstracts;

import ch.fhnw.cpib.compiler.parser.IAbstractNode;
import ch.fhnw.cpib.compiler.tokens.AttributeToken;
import ch.fhnw.cpib.compiler.tokens.IToken;
import ch.fhnw.cpib.compiler.tokens.enums.types.IType;
import ch.fhnw.cpib.compiler.vm.ICodeArray;
import ch.fhnw.cpib.compiler.vm.IInstructions;

import static ch.fhnw.cpib.compiler.codeGenerator.CodeGenerator.codeArray;
import static ch.fhnw.cpib.compiler.tokens.enums.types.Inttypes.INT32;

public class AbsIntLiteralExpr implements IAbstractNode {
    private final AttributeToken literal;

    public AbsIntLiteralExpr(IToken literal) {
        this.literal = (AttributeToken) literal;
    }

    @Override
    public IType check() {
        return INT32;
    }

    @Override
    public int code(int loc) throws ICodeArray.CodeTooSmallError {
        codeArray.put(loc, new IInstructions.LoadImInt(Integer.parseInt(literal.getValue())));
        return loc + 1;
    }

    public boolean lvalue() {
        return false;
    }

    public AttributeToken getLiteral() {
        return literal;
    }
}
