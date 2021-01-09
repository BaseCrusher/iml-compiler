package ch.fhnw.cpib.compiler.parser.abstracts;

import ch.fhnw.cpib.compiler.error.CodeGenError;
import ch.fhnw.cpib.compiler.error.TypeCheckError;
import ch.fhnw.cpib.compiler.parser.Environment;
import ch.fhnw.cpib.compiler.parser.IAbstractNode;
import ch.fhnw.cpib.compiler.parser.Variable;
import ch.fhnw.cpib.compiler.parser.nts.Identifier;
import ch.fhnw.cpib.compiler.tokens.enums.types.IType;
import ch.fhnw.cpib.compiler.tokens.enums.types.Types;
import ch.fhnw.cpib.compiler.vm.ICodeArray;
import ch.fhnw.cpib.compiler.vm.IInstructions;

import static ch.fhnw.cpib.compiler.codeGenerator.CodeGenerator.codeArray;
import static ch.fhnw.cpib.compiler.tokens.enums.modes.FlowModes.IN;
import static ch.fhnw.cpib.compiler.tokens.enums.modes.MechModes.COPY;

public class AbsStoreExpr implements IAbstractNode {

    private final Identifier ident;
    private boolean isInit;
    private final boolean isAssignment;

    public AbsStoreExpr(Identifier ident, boolean isInit, boolean isAssignment) {
        this.ident = ident;
        this.isInit = isInit;
        this.isAssignment = isAssignment;
    }

    @Override
    public IType check() throws TypeCheckError {
        Variable variable = ident.getEnvironment().getVariable(ident.getIdent().getValue());
        if (variable == null) throw new TypeCheckError("undefined variable " + ident.getIdent().getValue());
        return Types.getByName(variable.getType().getValue());
    }

    @Override
    public int code(int loc) throws ICodeArray.CodeTooSmallError, CodeGenError {
        Environment env = ident.getEnvironment();
        Variable variable = env.getVariable(ident.getIdent().getValue());

        codeArray.put(loc, new IInstructions.LoadAddrRel(env.getAbsoluteAddress(ident.getIdent().getValue())));
        loc++;

        if (env.getParent() != null && !(isMechModeCopy(variable) && isFlowModeIn(variable)) && !variable.isReturns() && variable.isParam()) {
            codeArray.put(loc, new IInstructions.Deref());
            loc++;
            codeArray.put(loc, new IInstructions.LoadAddrAbs());
            loc++;
        }
        if (rValue(variable)) {
            codeArray.put(loc, new IInstructions.Deref());
            loc++;
        }
        return loc;
    }

    public Identifier getIdent() {
        return ident;
    }

    private boolean rValue(Variable variable) {
        return !isInit && !isAssignment && !variable.isArray();
    }

    private boolean isFlowModeIn(Variable variable) {
        if (variable.getFlowMode() != null && variable.getFlowMode().getEpsilon() != null) {
            return true;
        }
        return variable.getFlowMode() != null && variable.getFlowMode().getToken().getValue().equals(IN.name());
    }

    private boolean isMechModeCopy(Variable variable) {
        if (variable.getMechmode() != null && variable.getMechmode().getEpsilon() !=  null) {
            return true;
        }
        return variable.getMechmode() != null && variable.getMechmode().getToken().getValue().equals(COPY.name());
    }
}
