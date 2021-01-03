package ch.fhnw.cpib.compiler.parser.abstracts;

import ch.fhnw.cpib.compiler.error.CodeGenError;
import ch.fhnw.cpib.compiler.error.TypeCheckError;
import ch.fhnw.cpib.compiler.parser.IAbstractNode;
import ch.fhnw.cpib.compiler.tokens.enums.types.IType;
import ch.fhnw.cpib.compiler.vm.ICodeArray;
import ch.fhnw.cpib.compiler.vm.IInstructions;

import java.util.List;

import static ch.fhnw.cpib.compiler.codeGenerator.CodeGenerator.codeArray;
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
    public int code(int loc) throws ICodeArray.CodeTooSmallError, CodeGenError {
        int condLoc = absExpr.code(loc);
        loc = condLoc + 1;
        for (IAbstractNode cmd : thenCommand) {
            loc = cmd.code(loc);
        }
        loc++;
        int thenLoc = loc;
        for (IAbstractNode cmd : elseCommand) {
            loc = cmd.code(loc);
        }
        codeArray.put(condLoc, new IInstructions.CondJump(thenLoc));
        codeArray.put(thenLoc-1, new IInstructions.UncondJump(loc));
        return loc;
    }
}
