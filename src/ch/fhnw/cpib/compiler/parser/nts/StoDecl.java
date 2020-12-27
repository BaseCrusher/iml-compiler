package ch.fhnw.cpib.compiler.parser.nts;

import ch.fhnw.cpib.compiler.error.GrammarError;
import ch.fhnw.cpib.compiler.parser.Environment;
import ch.fhnw.cpib.compiler.parser.IAbstractNode;
import ch.fhnw.cpib.compiler.parser.INtsParser;
import ch.fhnw.cpib.compiler.parser.Parser;
import ch.fhnw.cpib.compiler.parser.Variable;
import ch.fhnw.cpib.compiler.parser.abstracts.AbsStoDecl;
import ch.fhnw.cpib.compiler.tokens.IToken;

import static ch.fhnw.cpib.compiler.tokens.enums.AttributeTerminals.CHANGEMODE;
import static ch.fhnw.cpib.compiler.tokens.enums.AttributeTerminals.IDENT;


public class StoDecl implements INtsParser {
    private final IToken token;
    private OptChangemode optChangemode;
    private TypedIdent typedIdent;

    public StoDecl(Environment environment) throws GrammarError {
        token = Parser.getCurrentToken();
        if (token.hasTerminal(IDENT, CHANGEMODE)) {
            optChangemode = new OptChangemode();
            typedIdent = new TypedIdent();
            Variable variable = new Variable(typedIdent.getIdentifier().getValue(), optChangemode, typedIdent.getType(), environment.getStartAddress() + environment.getVars().size());
            environment.putVariable(typedIdent.getIdentifier().getValue(), variable);
        } else {
            throw new GrammarError(token);
        }

    }

    @Override
    public String toString() {
        return optChangemode.toString() + " " + typedIdent.toString();
    }

    public IAbstractNode toAbsSyn() {
        return new AbsStoDecl(optChangemode.toAbsSyn(), typedIdent);
    }

    public IToken getToken() {
        return token;
    }

    public INtsParser getOptChangemode() {
        return optChangemode;
    }

    public INtsParser getTypedIdent() {
        return typedIdent;
    }
}
