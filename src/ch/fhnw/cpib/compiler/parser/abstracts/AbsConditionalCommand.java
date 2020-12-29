package ch.fhnw.cpib.compiler.parser.abstracts;

import ch.fhnw.cpib.compiler.error.GrammarError;
import ch.fhnw.cpib.compiler.error.TypeCheckError;
import ch.fhnw.cpib.compiler.parser.IAbstractNode;
import ch.fhnw.cpib.compiler.tokens.enums.types.IType;
import ch.fhnw.cpib.compiler.vm.ICodeArray;

import java.util.List;

import static ch.fhnw.cpib.compiler.tokens.enums.types.VoidType.VOID;

public class AbsConditionalCommand implements IAbstractNode {
    private final IAbstractNode absExpr;
    private final List<IAbstractNode> thenCommand;
    private final List<IAbstractNode> elseCommand;

    public AbsConditionalCommand(IAbstractNode absExpr, List<IAbstractNode> thenCommand, List<IAbstractNode> elseCommand) {
        this.absExpr = absExpr;
        this.thenCommand = thenCommand;
        this.elseCommand = elseCommand;
    }

    @Override
    public IType check() throws TypeCheckError {
        absExpr.check();
        for (IAbstractNode cmd : thenCommand) {
            cmd.check();
        }
        for (IAbstractNode cmd : elseCommand) {
            cmd.check();
        }
        return VOID;
    }

    @Override
    public int code(int loc) throws ICodeArray.CodeTooSmallError {
        return loc;
    }
}
