package ch.fhnw.cpib.compiler.parser.abstracts;

import ch.fhnw.cpib.compiler.error.GrammarError;
import ch.fhnw.cpib.compiler.parser.IAbstractNode;
import ch.fhnw.cpib.compiler.tokens.enums.types.IType;
import ch.fhnw.cpib.compiler.vm.ICodeArray;

import java.util.List;

public class AbsWhileCommand implements IAbstractNode {
    private final IAbstractNode absExpr;
    private final List<IAbstractNode> absCpsCmd;

    public AbsWhileCommand(IAbstractNode absExpr, List<IAbstractNode> absCpsCmd) {
        this.absExpr = absExpr;
        this.absCpsCmd = absCpsCmd;
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
