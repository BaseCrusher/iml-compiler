package ch.fhnw.cpib.compiler.parser.abstracts;

import ch.fhnw.cpib.compiler.error.GrammarError;
import ch.fhnw.cpib.compiler.error.TypeCheckError;
import ch.fhnw.cpib.compiler.parser.IAbstractNode;
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

    public AbsStoDecl(ChangeModes optChangemode, TypedIdent typedIdent) {
        this.optChangemode = optChangemode;
        this.typedIdent = typedIdent;
        this.absTypedIdent = typedIdent.toAbsSyn();
    }

    @Override
    public IType check() throws TypeCheckError {
        return null;
    }

    @Override
    public int code(int loc) throws ICodeArray.CodeTooSmallError {
        codeArray.put(loc, new IInstructions.AllocBlock(1));
        return loc + 1;
    }

    public TypedIdent getTypedIdent() {
        return typedIdent;
    }
}
