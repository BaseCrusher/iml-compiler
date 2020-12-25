package ch.fhnw.cpib.compiler.parser.nts;

import ch.fhnw.cpib.compiler.error.GrammarError;
import ch.fhnw.cpib.compiler.parser.INtsParser;
import ch.fhnw.cpib.compiler.parser.Parser;
import ch.fhnw.cpib.compiler.tokens.IToken;

import static ch.fhnw.cpib.compiler.tokens.enums.AttributeTerminals.CHANGEMODE;
import static ch.fhnw.cpib.compiler.tokens.enums.AttributeTerminals.IDENT;


public class StoDecl implements INtsParser {
    private final IToken token;
    private INtsParser optChangemode;
    private INtsParser typedIdent;

    public StoDecl() throws GrammarError {
        token = Parser.getCurrentToken();
        if (token.hasTerminal(IDENT, CHANGEMODE)) {
            optChangemode = new OptChangemode();
            typedIdent = new TypedIdent();
        } else {
            throw new GrammarError(token);
        }

    }

    @Override
    public String toString() {
        return optChangemode.toString() + " " + typedIdent.toString();
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
