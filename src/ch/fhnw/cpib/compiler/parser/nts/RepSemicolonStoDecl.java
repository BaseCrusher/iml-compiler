package ch.fhnw.cpib.compiler.parser.nts;

import ch.fhnw.cpib.compiler.error.GrammarError;
import ch.fhnw.cpib.compiler.parser.INtsParser;
import ch.fhnw.cpib.compiler.parser.Parser;
import ch.fhnw.cpib.compiler.tokens.IToken;

import static ch.fhnw.cpib.compiler.tokens.enums.AttributeTerminals.*;
import static ch.fhnw.cpib.compiler.tokens.enums.KeywordTerminals.*;

public class RepSemicolonStoDecl implements INtsParser {
    private IToken token;
    private INtsParser stoDecl;
    private INtsParser repSemicolonStoDecl;
    private INtsParser epsilon;
    private String string;

    public RepSemicolonStoDecl() throws GrammarError {
        token = Parser.consume(SEMICOLON, DO);
        if (token.hasTerminal(SEMICOLON)) {
            stoDecl = null;
            repSemicolonStoDecl = null;
            string = token.getTerminal().toString() + " " + stoDecl.toString() + " " + repSemicolonStoDecl.toString();
        }
        else {
            epsilon = new Epsilon();
            string = epsilon.toString();
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

    public INtsParser getRepSemicolonStoDecl() {
        return repSemicolonStoDecl;
    }

    public INtsParser getEpsilon() {
        return epsilon;
    }
}
