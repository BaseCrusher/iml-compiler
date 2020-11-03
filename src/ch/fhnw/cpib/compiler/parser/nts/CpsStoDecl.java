package ch.fhnw.cpib.compiler.parser.nts;

import ch.fhnw.cpib.compiler.error.GrammarError;
import ch.fhnw.cpib.compiler.parser.INtsParser;
import ch.fhnw.cpib.compiler.parser.Parser;
import ch.fhnw.cpib.compiler.tokens.IToken;

import static ch.fhnw.cpib.compiler.tokens.enums.AttributeTerminals.*;
import static ch.fhnw.cpib.compiler.tokens.enums.KeywordTerminals.*;

public class CpsStoDecl implements INtsParser {
    private IToken token;
    private INtsParser stoDecl;
    private INtsParser repSemicolonStoDecl;
    private String string;

    public CpsStoDecl() throws GrammarError {
        token = Parser.consume(IDENT, CHANGEMODE);
        stoDecl = null;
        repSemicolonStoDecl = null;
        string = token.getTerminal().toString() + " " + stoDecl.toString() + " " + repSemicolonStoDecl.toString();
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

    public INtsParser getRepSemicolonStoDecl() {
        return repSemicolonStoDecl;
    }
}
