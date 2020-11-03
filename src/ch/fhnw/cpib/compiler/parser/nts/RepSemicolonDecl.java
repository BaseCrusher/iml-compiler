package ch.fhnw.cpib.compiler.parser.nts;

import ch.fhnw.cpib.compiler.error.GrammarError;
import ch.fhnw.cpib.compiler.parser.INtsParser;
import ch.fhnw.cpib.compiler.parser.Parser;
import ch.fhnw.cpib.compiler.tokens.IToken;

import static ch.fhnw.cpib.compiler.tokens.enums.AttributeTerminals.*;
import static ch.fhnw.cpib.compiler.tokens.enums.KeywordTerminals.*;

public class RepSemicolonDecl implements INtsParser {
    private IToken token;
    private INtsParser decl;
    private INtsParser repSemicolonDecl;
    private INtsParser epsilon;
    private String string;

    public RepSemicolonDecl() throws GrammarError {
        token = Parser.consume(SEMICOLON, DO);
        if (token.hasTerminal(SEMICOLON)) {
            decl = null;
            repSemicolonDecl = null;
            string = token.getTerminal().toString() + " " + decl.toString() + " " + repSemicolonDecl.toString();
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

    public INtsParser getDecl() {
        return decl;
    }

    public INtsParser getRepSemicolonDecl() {
        return repSemicolonDecl;
    }

    public INtsParser getEpsilon() {
        return epsilon;
    }
}
