package ch.fhnw.cpib.compiler.parser.abstracts;

import java.util.List;

import ch.fhnw.cpib.compiler.error.GrammarError;
import ch.fhnw.cpib.compiler.parser.IAbstractNode;
import ch.fhnw.cpib.compiler.parser.nts.Identifier;
import ch.fhnw.cpib.compiler.tokens.enums.types.IType;
import ch.fhnw.cpib.compiler.vm.ICodeArray;
import ch.fhnw.cpib.compiler.vm.IInstructions;

import static ch.fhnw.cpib.compiler.codeGenerator.CodeGenerator.codeArray;
import static ch.fhnw.cpib.compiler.tokens.enums.types.VoidType.VOID;

public class AbsProcDecl implements IAbstractNode {
    private final Identifier identifier;
    private final List<IAbstractNode> absParamList;
    private final List<IAbstractNode> absOptGlobalGlobImps;
    private final List<IAbstractNode> absOptLocalCpsStoDecl;
    private final List<IAbstractNode> absCpsCmd;

    public AbsProcDecl(Identifier identifier, List<IAbstractNode> absParamList, List<IAbstractNode> absOptGlobalGlobImps, List<IAbstractNode> absOptLocalCpsStoDecl, List<IAbstractNode> absCpsCmd) {

        this.identifier = identifier;
        this.absParamList = absParamList;
        this.absOptGlobalGlobImps = absOptGlobalGlobImps;
        this.absOptLocalCpsStoDecl = absOptLocalCpsStoDecl;
        this.absCpsCmd = absCpsCmd;
    }

    @Override
    public IType check() throws GrammarError {
        absCpsCmd.forEach(cmd -> {
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
        if (absParamList != null) {
            for (IAbstractNode param : absParamList) {
                loc = param.code(loc);
            }
        }

        int globalImpsSize = 0;
        if (absOptGlobalGlobImps != null) {
            for (IAbstractNode globalImp : absOptGlobalGlobImps) {
                loc = globalImp.code(loc);
            }
            globalImpsSize = absOptGlobalGlobImps.size();
        }
        if (absOptLocalCpsStoDecl != null) {
            for (IAbstractNode stoDecl : absOptLocalCpsStoDecl) {
                loc = stoDecl.code(loc);
            }
        }

        // TODO replace 10 with appropriate value from context
        int size = 10 - 3 - globalImpsSize;
        codeArray.put(loc, new IInstructions.Return(size));
        loc++;
        codeArray.put(loc, new IInstructions.UncondJump(nextLoc));
        loc++;
        return loc;
    }
}
