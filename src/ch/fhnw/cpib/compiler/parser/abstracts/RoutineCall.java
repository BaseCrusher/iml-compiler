package ch.fhnw.cpib.compiler.parser.abstracts;

import java.util.List;

import ch.fhnw.cpib.compiler.error.GrammarError;
import ch.fhnw.cpib.compiler.parser.IAbstractNode;
import ch.fhnw.cpib.compiler.parser.Routine;
import ch.fhnw.cpib.compiler.parser.nts.Identifier;
import ch.fhnw.cpib.compiler.tokens.enums.types.IType;

public class RoutineCall implements IAbstractNode {

    private final Identifier identifier;
    private final List<IAbstractNode> exprList;

    public RoutineCall(Identifier identifier, List<IAbstractNode> exprList) {
        this.identifier = identifier;
        this.exprList = exprList;
    }

    @Override
    public IType check() throws GrammarError {
        exprList.forEach(param -> {
            try {
                check();
            } catch (GrammarError grammarError) {
                grammarError.printStackTrace();
            }
        });
        Routine routine = identifier.getEnvironment().getRoutine(identifier.getIdent().getValue());
        if (routine == null) throw new GrammarError("Routine " + identifier.getIdent().getValue() + " was not declared");
        return routine.getReturnType();
    }

    @Override
    public int code(int loc) {
        return loc;
    }
}
