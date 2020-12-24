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
    private final String string;

    public StoDecl() throws GrammarError {
        token = Parser.consume(IDENT, CHANGEMODE);
        optChangemode = new OptChangemode(this.token);
        typedIdent = new TypedIdent(this.token);
        string = token.getTerminal().toString() + " " + optChangemode.toString() + " " + typedIdent.toString();
    }

    @Override
    public String toString() {
        return string;
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
