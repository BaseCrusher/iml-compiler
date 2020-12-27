package ch.fhnw.cpib.compiler.parser.abstracts;

import ch.fhnw.cpib.compiler.parser.IAbstractNode;
import ch.fhnw.cpib.compiler.tokens.enums.types.IType;

import ch.fhnw.cpib.compiler.vm.ICodeArray;
import ch.fhnw.cpib.compiler.vm.IInstructions.Stop;

import java.util.List;

import static ch.fhnw.cpib.compiler.codeGenerator.CodeGenerator.codeArray;
import static ch.fhnw.cpib.compiler.tokens.enums.types.VoidType.VOID;

public class AbsProgram implements IAbstractNode {


    private String ident;
    private final List<IAbstractNode> progParamList;
    private final List<IAbstractNode> optGlobalCpsDecl;
    private final List<IAbstractNode> cmds;

    public AbsProgram(String ident, List<IAbstractNode> progParamList, List<IAbstractNode> optGlobalCpsDecl, List<IAbstractNode> cmds) {
        this.ident = ident;
        this.progParamList = progParamList;
        this.optGlobalCpsDecl = optGlobalCpsDecl;
        this.cmds = cmds;
    }

    @Override
    public IType check() {
        progParamList.forEach(param -> check());
        return VOID;
    }

    @Override
    public int code(int loc) throws ICodeArray.CodeTooSmallError {
        int loc1 = loc;
        for (IAbstractNode param : progParamList) {
            loc1 = param.code(loc1);
        }
        for (IAbstractNode decl : optGlobalCpsDecl) {
            loc1 = decl.code(loc1);
        }
        for (IAbstractNode cmd : cmds) {
            loc1 = cmd.code(loc1);
        }
        codeArray.put(loc1, new Stop());
        return loc1;
    }
}
