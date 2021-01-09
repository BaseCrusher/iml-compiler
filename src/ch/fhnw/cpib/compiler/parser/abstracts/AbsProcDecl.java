package ch.fhnw.cpib.compiler.parser.abstracts;

import java.util.List;

import ch.fhnw.cpib.compiler.error.CodeGenError;
import ch.fhnw.cpib.compiler.error.GrammarError;
import ch.fhnw.cpib.compiler.error.TypeCheckError;
import ch.fhnw.cpib.compiler.parser.Environment;
import ch.fhnw.cpib.compiler.parser.IAbstractNode;
import ch.fhnw.cpib.compiler.parser.nts.Identifier;
import ch.fhnw.cpib.compiler.parser.nts.Param;
import ch.fhnw.cpib.compiler.tokens.enums.types.IType;
import ch.fhnw.cpib.compiler.vm.ICodeArray;
import ch.fhnw.cpib.compiler.vm.IInstructions;

import static ch.fhnw.cpib.compiler.codeGenerator.CodeGenerator.codeArray;
import static ch.fhnw.cpib.compiler.parser.abstracts.AbsProgram.declMap;
import static ch.fhnw.cpib.compiler.tokens.enums.modes.FlowModes.IN;
import static ch.fhnw.cpib.compiler.tokens.enums.modes.MechModes.COPY;
import static ch.fhnw.cpib.compiler.tokens.enums.types.VoidType.VOID;

public class AbsProcDecl implements IAbstractNode {
    private final Identifier identifier;
    private final List<IAbstractNode> absParamList;
    private final List<IAbstractNode> absOptGlobalGlobImps;
    private final List<IAbstractNode> absOptLocalCpsStoDecl;
    private final List<IAbstractNode> absCpsCmd;
    private final Environment localEnv;

    public AbsProcDecl(Identifier identifier, List<IAbstractNode> absParamList, List<IAbstractNode> absOptGlobalGlobImps, List<IAbstractNode> absOptLocalCpsStoDecl, List<IAbstractNode> absCpsCmd, Environment localEnv) {

        this.identifier = identifier;
        this.absParamList = absParamList;
        this.absOptGlobalGlobImps = absOptGlobalGlobImps;
        this.absOptLocalCpsStoDecl = absOptLocalCpsStoDecl;
        this.absCpsCmd = absCpsCmd;
        this.localEnv = localEnv;
    }

    @Override
    public IType check() throws TypeCheckError {
        for (IAbstractNode param : absParamList) {
            param.check();
        }
        for (IAbstractNode imp : absOptGlobalGlobImps) {
            imp.check();
        }
        for (IAbstractNode localDecl : absOptLocalCpsStoDecl) {
            localDecl.check();
        }
        for (IAbstractNode cmd : absCpsCmd) {
            cmd.check();
        }
        return VOID;
    }

    @Override
    public int code(int loc) throws ICodeArray.CodeTooSmallError, CodeGenError {
        declMap.put(identifier.getIdent().getValue(), loc);
        codeArray.put(loc, new IInstructions.AllocBlock(localEnv.getVars().size()));
        loc++;

        var paramCount = 0;
        if (absParamList != null) {
            for (IAbstractNode param : absParamList) {
                loc = param.code(loc);
                paramCount++;
            }
        }

        for (int i = 0, j = 3; i < paramCount; i++, j++) {
            codeArray.put(loc, new IInstructions.LoadAddrRel(j));
            loc++;
            codeArray.put(loc, new IInstructions.LoadAddrRel(i-paramCount));
            loc++;
            if (isFlowModeIn(i) && isMechModeCopy(i)) {
                codeArray.put(loc, new IInstructions.Deref());
                loc++;
                codeArray.put(loc, new IInstructions.LoadAddrAbs());
                loc++;
            }
            codeArray.put(loc, new IInstructions.Deref());
            loc++;
            codeArray.put(loc, new IInstructions.Store());
            loc++;
        }


        if (absOptGlobalGlobImps != null) {
            for (IAbstractNode globalImp : absOptGlobalGlobImps) {
                loc = globalImp.code(loc);
            }
        }
        if (absOptLocalCpsStoDecl != null) {
            for (IAbstractNode stoDecl : absOptLocalCpsStoDecl) {
                loc = stoDecl.code(loc);
            }
        }

        for (IAbstractNode cmd : absCpsCmd) {
            loc = cmd.code(loc);
        }

        codeArray.put(loc, new IInstructions.Return(paramCount));
        loc++;
        return loc;
    }

    private boolean isFlowModeIn(int i) {
        return ((AbsParam)absParamList.get(i)).getOptFlowmode().getToken().getValue().equals(IN.name());
    }

    private boolean isMechModeCopy(int i) {
        return ((AbsParam)absParamList.get(i)).getOptMechmode().getToken().getValue().equals(COPY.name());
    }
}
