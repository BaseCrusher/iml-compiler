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
import static ch.fhnw.cpib.compiler.tokens.enums.modes.ChangeModes.CONST;
import static ch.fhnw.cpib.compiler.tokens.enums.modes.MechModes.COPY;

public class AbsStoreExpr implements IAbstractNode {

    private final Identifier ident;
    private final boolean isInit;

    public AbsStoreExpr(Identifier ident, boolean isInit) {
        this.ident = ident;
        this.isInit = isInit;
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
        Variable variable = ident.getEnvironment().getVariable(ident.getIdent().getValue());
        // global scope
        if (env.getParent() == null) {
            codeArray.put(loc, new IInstructions.LoadImInt(env.getAbsoluteAddress(ident.getIdent().getValue())));
        } else {
            codeArray.put(loc, new IInstructions.LoadAddrRel(env.getAbsoluteAddress(ident.getIdent().getValue())));
        }
        loc++;
        if (variable.getMechmode() != null && variable.getMechmode().getToken().getValue().equals(COPY.name())) {
            codeArray.put(loc, new IInstructions.Deref());
            loc++;
        }
        if (variable.getChangeMode() != null && variable.getChangeMode().getToken().getValue().equals(CONST.name())) {
            codeArray.put(loc, new IInstructions.Deref());
            loc++;
        }
        return loc;
    }

    public Identifier getIdent() {
        return ident;
    }
}
