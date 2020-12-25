package ch.fhnw.cpib.compiler.parser.nts;

import ch.fhnw.cpib.compiler.error.GrammarError;
import ch.fhnw.cpib.compiler.parser.INtsParser;
import ch.fhnw.cpib.compiler.parser.Parser;
import ch.fhnw.cpib.compiler.tokens.IToken;

import static ch.fhnw.cpib.compiler.tokens.enums.AttributeTerminals.CHANGEMODE;
import static ch.fhnw.cpib.compiler.tokens.enums.AttributeTerminals.IDENT;
import static ch.fhnw.cpib.compiler.tokens.enums.KeywordTerminals.FUN;
import static ch.fhnw.cpib.compiler.tokens.enums.KeywordTerminals.PROC;

public class Decl implements INtsParser {
    private final IToken token;
    private INtsParser stoDecl;
    private INtsParser funDecl;
    private INtsParser procDecl;
    private final String string;

    public Decl() throws GrammarError {
        token = Parser.getCurrentToken();
        if (token.hasTerminal(IDENT, CHANGEMODE)) {
            stoDecl = new StoDecl();
            string = token.getTerminal().toString() + " " + stoDecl.toString();
        }
        else if (token.hasTerminal(FUN)) {
            funDecl = new FunDecl();
            string = token.getTerminal().toString() + " " + funDecl.toString();
        }
        else if (token.hasTerminal(PROC)) {
            procDecl = new ProcDecl();
            string = token.getTerminal().toString() + " " + procDecl.toString();
        }
        else {
            throw new GrammarError(token);
        }
    }

    @Override
    public String toString() {
        return string;
    }

    public IToken getToken() {
        return token;
    }

    public INtsParser getStoDecl() {
        return stoDecl;
    }

    public INtsParser getFunDecl() {
        return funDecl;
    }

    public INtsParser getProcDecl() {
        return procDecl;
    }
}
