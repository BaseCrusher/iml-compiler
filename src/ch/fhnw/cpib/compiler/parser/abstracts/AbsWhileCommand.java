package ch.fhnw.cpib.compiler.parser.abstracts;

import ch.fhnw.cpib.compiler.error.GrammarError;
import ch.fhnw.cpib.compiler.error.TypeCheckError;
import ch.fhnw.cpib.compiler.parser.IAbstractNode;
import ch.fhnw.cpib.compiler.tokens.enums.types.IType;
import ch.fhnw.cpib.compiler.vm.ICodeArray;

import java.util.List;

import static ch.fhnw.cpib.compiler.tokens.enums.types.VoidType.VOID;

public class AbsWhileCommand implements IAbstractNode {
    private final IAbstractNode absExpr;
    private final List<IAbstractNode> absCpsCmd;

    public AbsWhileCommand(IAbstractNode absExpr, List<IAbstractNode> absCpsCmd) {
        this.absExpr = absExpr;
        this.absCpsCmd = absCpsCmd;
    }

    @Override
    public IType check() throws TypeCheckError {
        absExpr.check();
        for (IAbstractNode cmd : absCpsCmd) {
            cmd.check();
        }
        return VOID;
    }

    @Override
    public int code(int loc) throws ICodeArray.CodeTooSmallError {
        return loc;
    }
}
