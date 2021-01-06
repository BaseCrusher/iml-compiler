package ch.fhnw.cpib.compiler.parser.abstracts;

import java.util.List;

import ch.fhnw.cpib.compiler.error.CodeGenError;
import ch.fhnw.cpib.compiler.error.GrammarError;
import ch.fhnw.cpib.compiler.error.TypeCheckError;
import ch.fhnw.cpib.compiler.parser.Environment;
import ch.fhnw.cpib.compiler.parser.IAbstractNode;
import ch.fhnw.cpib.compiler.parser.Variable;
import ch.fhnw.cpib.compiler.parser.nts.Identifier;
import ch.fhnw.cpib.compiler.parser.nts.ParamList;
import ch.fhnw.cpib.compiler.tokens.enums.types.IType;
import ch.fhnw.cpib.compiler.vm.ICodeArray;
import ch.fhnw.cpib.compiler.vm.IInstructions;

import static ch.fhnw.cpib.compiler.codeGenerator.CodeGenerator.codeArray;
import static ch.fhnw.cpib.compiler.parser.abstracts.AbsProgram.declMap;
import static ch.fhnw.cpib.compiler.tokens.enums.modes.MechModes.COPY;
import static ch.fhnw.cpib.compiler.tokens.enums.modes.MechModes.REF;
import static ch.fhnw.cpib.compiler.tokens.enums.types.VoidType.VOID;

public class AbsFunDecl implements IAbstractNode {
    private final Identifier identifier;
    private final List<IAbstractNode> paramList;
    private final IAbstractNode absStoDecl;
    private final List<IAbstractNode> optGlobalGlobImps;
    private final List<IAbstractNode> optLocalCpsStoDecl;
    private final List<IAbstractNode> cpsCmd;
    private final Environment localEnv;

    public AbsFunDecl(Identifier identifier, List<IAbstractNode> paramList, IAbstractNode absStoDecl, List<IAbstractNode> optGlobalGlobImps, List<IAbstractNode> optLocalCpsStoDecl, List<IAbstractNode> cpsCmd, Environment localEnv) {
        this.identifier = identifier;
        this.paramList = paramList;
        this.absStoDecl = absStoDecl;
        this.optGlobalGlobImps = optGlobalGlobImps;
        this.optLocalCpsStoDecl = optLocalCpsStoDecl;
        this.cpsCmd = cpsCmd;
        this.localEnv = localEnv;
    }

    @Override
    public IType check() throws TypeCheckError {
        for (IAbstractNode params : paramList) {
            if (params != null) {
                params.check();
            }
        }
        absStoDecl.check();
        for (IAbstractNode imp : optGlobalGlobImps) {
            if (imp != null) {
                imp.check();
            }
        }
        for (IAbstractNode localImp : optLocalCpsStoDecl) {
            if (localImp != null) {
                localImp.check();
            }
        }
        for (IAbstractNode cmd : cpsCmd) {
            if (cmd != null) {
                cmd.check();
            }
        }
        return VOID;
    }

    @Override
    public int code(int loc) throws ICodeArray.CodeTooSmallError, CodeGenError {
        declMap.put(identifier.getIdent().getValue(), loc);
        codeArray.put(loc, new IInstructions.AllocBlock(localEnv.getVars().size()));
        loc++;
        loc = absStoDecl.code(loc);
        var paramCount = 0;
        for (IAbstractNode param : paramList) {
            if (param != null) {
                loc = param.code(loc);
                paramCount++;
            }
        }

        for (int i = paramCount, j = 3; i > 0; i--, j++) {
            codeArray.put(loc, new IInstructions.LoadAddrRel(j));
            loc++;
            codeArray.put(loc, new IInstructions.LoadAddrRel(-i));
            loc++;
            codeArray.put(loc, new IInstructions.Deref());
            loc++;
            codeArray.put(loc, new IInstructions.Store());
            loc++;
        }

        for (IAbstractNode cmd : cpsCmd) {
            loc = cmd.code(loc);
        }

        codeArray.put(loc, new IInstructions.LoadAddrRel(-paramCount-1));
        loc++;
        codeArray.put(loc, new IInstructions.LoadAddrRel(paramCount + 3));
        loc++;
        codeArray.put(loc, new IInstructions.Deref());
        loc++;
        codeArray.put(loc, new IInstructions.Store());
        loc++;
        codeArray.put(loc, new IInstructions.Return(paramCount));
        return loc + 1;
    }
}
