package ch.fhnw.cpib.compiler.parser.abstracts;

import ch.fhnw.cpib.compiler.error.GrammarError;
import ch.fhnw.cpib.compiler.parser.IAbstractNode;
import ch.fhnw.cpib.compiler.parser.nts.TypedIdent;
import ch.fhnw.cpib.compiler.tokens.enums.modes.ChangeModes;
import ch.fhnw.cpib.compiler.tokens.enums.types.IType;
import ch.fhnw.cpib.compiler.vm.ICodeArray;

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
    public IType check() throws GrammarError {
        return null;
    }

    @Override
    public int code(int loc) throws ICodeArray.CodeTooSmallError {
        return 0;
    }

    public TypedIdent getTypedIdent() {
        return typedIdent;
    }
}
