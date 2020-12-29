package ch.fhnw.cpib.compiler.parser.abstracts;

import ch.fhnw.cpib.compiler.error.GrammarError;
import ch.fhnw.cpib.compiler.parser.IAbstractNode;
import ch.fhnw.cpib.compiler.parser.nts.Identifier;
import ch.fhnw.cpib.compiler.tokens.enums.types.IType;
import ch.fhnw.cpib.compiler.vm.ICodeArray;
import ch.fhnw.cpib.compiler.vm.IInstructions;

import static ch.fhnw.cpib.compiler.codeGenerator.CodeGenerator.codeArray;
import static ch.fhnw.cpib.compiler.tokens.enums.types.Inttypes.INT32;

public class AbsArrLenExpr implements IAbstractNode {

    private Identifier identifier;

    public AbsArrLenExpr(Identifier identifier) {

        this.identifier = identifier;
    }

    @Override
    public IType check() {
        return INT32;
    }

    @Override
    public int code(int loc) throws ICodeArray.CodeTooSmallError {
        identifier.getEnvironment().getStartAddress();
        codeArray.put(loc, new IInstructions.LoadImInt(0)); //TODO
        return 0;
    }
}
