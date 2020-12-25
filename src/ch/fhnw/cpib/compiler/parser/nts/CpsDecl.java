package ch.fhnw.cpib.compiler.parser.nts;

import ch.fhnw.cpib.compiler.error.GrammarError;
import ch.fhnw.cpib.compiler.parser.INtsParser;
import ch.fhnw.cpib.compiler.parser.Parser;
import ch.fhnw.cpib.compiler.tokens.IToken;

import static ch.fhnw.cpib.compiler.tokens.enums.AttributeTerminals.CHANGEMODE;
import static ch.fhnw.cpib.compiler.tokens.enums.AttributeTerminals.IDENT;
import static ch.fhnw.cpib.compiler.tokens.enums.KeywordTerminals.FUN;
import static ch.fhnw.cpib.compiler.tokens.enums.KeywordTerminals.PROC;

public class CpsDecl implements INtsParser {
    private final IToken token;
    private final INtsParser decl;
    private final INtsParser repSemicolonDecl;
    private final String string;

    public CpsDecl() throws GrammarError {
        token = Parser.getCurrentToken();
        if (token.hasTerminal(PROC, FUN, IDENT, CHANGEMODE)) {
            decl = new Decl();
            repSemicolonDecl = new RepSemicolonDecl();
            string = token.getTerminal().toString() + " " + decl.toString() + " " + repSemicolonDecl.toString();
        } else {
            throw new GrammarError(token);
        }

    }

    @Override
    public String toString() {
        return string;
    }

    public INtsParser getDecl() {
        return decl;
    }

    public INtsParser getRepSemicolonDecl() {
        return repSemicolonDecl;
    }

    public IToken getToken() {
        return token;
    }
}
