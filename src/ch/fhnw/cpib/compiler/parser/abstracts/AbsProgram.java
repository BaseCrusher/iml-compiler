package ch.fhnw.cpib.compiler.parser.abstracts;

import ch.fhnw.cpib.compiler.error.CodeGenError;
import ch.fhnw.cpib.compiler.error.TypeCheckError;
import ch.fhnw.cpib.compiler.parser.Environment;
import ch.fhnw.cpib.compiler.parser.IAbstractNode;
import ch.fhnw.cpib.compiler.tokens.enums.types.IType;

import ch.fhnw.cpib.compiler.vm.ICodeArray;
import ch.fhnw.cpib.compiler.vm.IInstructions;
import ch.fhnw.cpib.compiler.vm.IInstructions.Stop;

import java.util.List;

import static ch.fhnw.cpib.compiler.codeGenerator.CodeGenerator.codeArray;
import static ch.fhnw.cpib.compiler.tokens.enums.types.VoidType.VOID;

public class AbsProgram implements IAbstractNode {


    private String ident;
    private final List<IAbstractNode> progParamList;
    private final List<IAbstractNode> optGlobalCpsDecl;
    private final List<IAbstractNode> cmds;
    private final Environment globalEnv;

    public AbsProgram(String ident, List<IAbstractNode> progParamList, List<IAbstractNode> optGlobalCpsDecl, List<IAbstractNode> cmds, Environment globalEnv) {
        this.ident = ident;
        this.progParamList = progParamList;
        this.optGlobalCpsDecl = optGlobalCpsDecl;
        this.cmds = cmds;
        this.globalEnv = globalEnv;
    }

    @Override
    public IType check() throws TypeCheckError {
        for (IAbstractNode param : progParamList) {
            param.check();
        }
        for (IAbstractNode decl : optGlobalCpsDecl) {
            decl.check();
        }
        for (IAbstractNode cmd : cmds) {
            cmd.check();
        }
        return VOID;
    }

    @Override
    public int code(int loc) throws ICodeArray.CodeTooSmallError, CodeGenError {
        codeArray.put(loc, new IInstructions.AllocBlock(globalEnv.getVars().size()));
        loc++;
        for (IAbstractNode param : progParamList) {
            loc = param.code(loc);
        }
        for (IAbstractNode cmd : cmds) {
            loc = cmd.code(loc);
        }
        for (IAbstractNode decl : optGlobalCpsDecl) {
            loc = decl.code(loc);
        }
        codeArray.put(loc, new Stop());
        loc++;
        return loc;
    }
}
