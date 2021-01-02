package ch.fhnw.cpib.compiler.parser.abstracts;

import ch.fhnw.cpib.compiler.error.CodeGenError;
import ch.fhnw.cpib.compiler.error.GrammarError;
import ch.fhnw.cpib.compiler.error.TypeCheckError;
import ch.fhnw.cpib.compiler.parser.Environment;
import ch.fhnw.cpib.compiler.parser.IAbstractNode;
import ch.fhnw.cpib.compiler.parser.nts.OptArrDecl;
import ch.fhnw.cpib.compiler.parser.nts.TypedIdent;
import ch.fhnw.cpib.compiler.tokens.enums.modes.ChangeModes;
import ch.fhnw.cpib.compiler.tokens.enums.types.IType;
import ch.fhnw.cpib.compiler.vm.ICodeArray;
import ch.fhnw.cpib.compiler.vm.IInstructions;

import static ch.fhnw.cpib.compiler.codeGenerator.CodeGenerator.codeArray;

public class AbsStoDecl implements IAbstractNode {


    private final ChangeModes optChangemode;
    private final TypedIdent typedIdent;
    private final IAbstractNode absTypedIdent;
    private int blockSize = 0;
    private final OptArrDecl arrDecl;
    private final Environment environment;

    public AbsStoDecl(ChangeModes optChangemode, TypedIdent typedIdent, Environment environment) {
        this.optChangemode = optChangemode;
        this.typedIdent = typedIdent;
        this.absTypedIdent = typedIdent.toAbsSyn();
        arrDecl = typedIdent.getArrDecl();
        this.environment = environment;
    }

    @Override
    public IType check() throws TypeCheckError {
        if (isArrDecl()) {
            String value = arrDecl.getArrDecl().getOptLit().getToken().getValue();
            // + 1 for array size
            blockSize += Integer.parseInt(value) + 1;
            return absTypedIdent.check();
        }
        blockSize++;
        return absTypedIdent.check();
    }

    @Override
    public int code(int loc) throws ICodeArray.CodeTooSmallError, CodeGenError {
        if (isArrDecl()) {
            String value = arrDecl.getArrDecl().getOptLit().getToken().getValue();
            codeArray.put(loc, new IInstructions.LoadAddrRel(environment.getRelAddress(typedIdent.getIdentifier().getValue())));
            loc++;
            codeArray.put(loc, new IInstructions.LoadImInt(Integer.parseInt(value)));
            loc++;
            codeArray.put(loc, new IInstructions.Store());
            loc++;
        }
        return loc;
    }

    public TypedIdent getTypedIdent() {
        return typedIdent;
    }

    private boolean isArrDecl() {
        if (arrDecl.getEpsilon() == null) {
            return arrDecl.getArrDecl().getOptLit() != null;
        }
        return false;
    }
}
