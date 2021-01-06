package ch.fhnw.cpib.compiler.parser.abstracts;

import ch.fhnw.cpib.compiler.error.CodeGenError;
import ch.fhnw.cpib.compiler.error.TypeCheckError;
import ch.fhnw.cpib.compiler.parser.Environment;
import ch.fhnw.cpib.compiler.parser.IAbstractNode;
import ch.fhnw.cpib.compiler.parser.nts.Identifier;
import ch.fhnw.cpib.compiler.tokens.enums.types.IType;

import ch.fhnw.cpib.compiler.vm.ICodeArray;
import ch.fhnw.cpib.compiler.vm.IInstructions;
import ch.fhnw.cpib.compiler.vm.IInstructions.Stop;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static ch.fhnw.cpib.compiler.codeGenerator.CodeGenerator.codeArray;
import static ch.fhnw.cpib.compiler.tokens.enums.types.VoidType.VOID;

public class AbsProgram implements IAbstractNode {

    public static Map<String, Integer> declMap = new HashMap<>();
    public static Map<Integer, String> callMap = new HashMap<>();

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
        for (IAbstractNode decl : optGlobalCpsDecl) {
            if (decl instanceof AbsStoDecl) {
                loc = decl.code(loc);
            }
        }
        for (IAbstractNode cmd : cmds) {
            loc = cmd.code(loc);
        }
        codeArray.put(loc, new Stop());
        loc++;
        for (IAbstractNode decl : optGlobalCpsDecl) {
            if (!(decl instanceof AbsStoDecl)) {
                loc = decl.code(loc);
            }
        }

        for (Map.Entry<Integer, String> entry : callMap.entrySet()) {
            Integer location = declMap.get(entry.getValue());
            codeArray.put(entry.getKey(), new IInstructions.Call(location));
        }

        return loc;
    }
}
