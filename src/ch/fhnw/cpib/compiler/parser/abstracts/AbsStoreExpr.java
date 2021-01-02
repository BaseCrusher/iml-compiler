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
        if (variable.getType() == null) throw new TypeCheckError("undefined " + ident.getIdent().getValue());
        return Types.getByName(variable.getType().getValue());
    }

    @Override
    public int code(int loc) throws ICodeArray.CodeTooSmallError, CodeGenError {
        Environment env = ident.getEnvironment();
        Variable variable = env.getVariable(ident.getIdent().getValue());

        codeArray.put(loc, new IInstructions.LoadAddrRel(env.getAbsoluteAddress(ident.getIdent().getValue())));

        loc++;
        if ((variable.getMechmode() == null || variable.getMechmode().getToken().getValue().equals(COPY.name())) && !isInit && !isAssignment) {
            codeArray.put(loc, new IInstructions.Deref());
            loc++;
        }
        return loc;
    }

    public Identifier getIdent() {
        return ident;
    }
}
