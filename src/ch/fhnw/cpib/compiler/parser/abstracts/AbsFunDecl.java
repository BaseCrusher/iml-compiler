package ch.fhnw.cpib.compiler.parser.abstracts;

import java.util.List;

import ch.fhnw.cpib.compiler.error.GrammarError;
import ch.fhnw.cpib.compiler.parser.Environment;
import ch.fhnw.cpib.compiler.parser.IAbstractNode;
import ch.fhnw.cpib.compiler.parser.Variable;
import ch.fhnw.cpib.compiler.parser.nts.Identifier;
import ch.fhnw.cpib.compiler.tokens.enums.types.IType;
import ch.fhnw.cpib.compiler.vm.ICodeArray;
import ch.fhnw.cpib.compiler.vm.IInstructions;

import static ch.fhnw.cpib.compiler.codeGenerator.CodeGenerator.codeArray;
import static ch.fhnw.cpib.compiler.tokens.enums.types.VoidType.VOID;

public class AbsFunDecl implements IAbstractNode {
    private final Identifier identifier;
    private final List<IAbstractNode> paramList;
    private final IAbstractNode absStoDecl;
    private final List<IAbstractNode> optGlobalGlobImps;
    private final List<IAbstractNode> optLocalCpsStoDecl;
    private final List<IAbstractNode> cpsCmd;

    public AbsFunDecl(Identifier identifier, List<IAbstractNode> paramList, IAbstractNode absStoDecl, List<IAbstractNode> optGlobalGlobImps, List<IAbstractNode> optLocalCpsStoDecl, List<IAbstractNode> cpsCmd) {
        this.identifier = identifier;
        this.paramList = paramList;
        this.absStoDecl = absStoDecl;
        this.optGlobalGlobImps = optGlobalGlobImps;
        this.optLocalCpsStoDecl = optLocalCpsStoDecl;
        this.cpsCmd = cpsCmd;
    }

    @Override
    public IType check() throws GrammarError {
        cpsCmd.forEach(c -> {
            try {
                check();
            } catch (GrammarError grammarError) {
                grammarError.printStackTrace();
            }
        });
        return VOID;
    }

    @Override
    public int code(int loc) throws ICodeArray.CodeTooSmallError {
        int nextLoc = loc + 1;
        loc = absStoDecl.code(loc);
        var relLoc = 0;
        if (paramList != null) {
            for (IAbstractNode param : paramList) {
                loc = param.code(loc);
            }
            relLoc = paramList.size();
        }
        int globImpsSize = 0;
        if (optGlobalGlobImps != null) {
            for (IAbstractNode glob : optGlobalGlobImps) {
                loc = glob.code(loc);
            }
            globImpsSize = optGlobalGlobImps.size();
        }

        int optLocalCpsStoDeclsSize = 0;
        if (optLocalCpsStoDecl != null) {
            for (IAbstractNode local : optLocalCpsStoDecl) {
                loc = local.code(loc);
            }
            optLocalCpsStoDeclsSize = optLocalCpsStoDecl.size();
        }
        for (IAbstractNode cmd : cpsCmd) {
            loc = cmd.code(loc);
        }

        Environment environment = identifier.getEnvironment();
        int size = environment.getVars().size() - 4 - globImpsSize - optLocalCpsStoDeclsSize;

        Variable variable = environment.getVariable(((AbsStoDecl)absStoDecl).getTypedIdent().getIdentifier().getValue());
        codeArray.put(loc, new IInstructions.LoadAddrRel(variable.getRelAddress()));
        loc++;
        codeArray.put(loc, new IInstructions.Deref());
        loc++;

        codeArray.put(loc, new IInstructions.LoadAddrRel(relLoc + 1));
        loc++;
        codeArray.put(loc, new IInstructions.Return(size));
        loc++;
        codeArray.put(loc, new IInstructions.UncondJump(nextLoc));
        loc++;
        return loc;
    }
}
