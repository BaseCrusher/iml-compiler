package ch.fhnw.cpib.compiler.parser.abstracts;

import ch.fhnw.cpib.compiler.error.GrammarError;
import ch.fhnw.cpib.compiler.parser.IAbstractNode;
import ch.fhnw.cpib.compiler.tokens.enums.types.IType;
import ch.fhnw.cpib.compiler.vm.ICodeArray;

import java.util.List;

public class AbsCallCommand implements IAbstractNode {
    private final String identifier;
    private final List<IAbstractNode> absExprList;
    private final List<String> absOptGlobInits;

    public AbsCallCommand(String identifier, List<IAbstractNode> absExprList, List<String> absOptGlobInits) {
        this.identifier = identifier;
        this.absExprList = absExprList;
        this.absOptGlobInits = absOptGlobInits;
    }

    @Override
    public IType check() throws GrammarError {
        return null;
    }

    @Override
    public int code(int loc) throws ICodeArray.CodeTooSmallError {
        return 0;
    }
}
