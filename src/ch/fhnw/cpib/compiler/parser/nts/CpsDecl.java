package ch.fhnw.cpib.compiler.parser.nts;

import ch.fhnw.cpib.compiler.error.GrammarError;
import ch.fhnw.cpib.compiler.parser.INtsParser;
import ch.fhnw.cpib.compiler.parser.Parser;
import ch.fhnw.cpib.compiler.tokens.IToken;

import static ch.fhnw.cpib.compiler.tokens.enums.AttributeTerminals.*;
import static ch.fhnw.cpib.compiler.tokens.enums.KeywordTerminals.*;

public class CpsDecl implements INtsParser {
    private IToken token;
    private INtsParser decl;
    private INtsParser repSemicolonDecl;
    private String string;

    public CpsDecl() throws GrammarError {
        token = Parser.consume(PROC, FUN, IDENT, CHANGEMODE);
        decl = null;
        repSemicolonDecl = null;
        string = token.getTerminal().toString() + " " + decl.toString() + " " + repSemicolonDecl.toString();
    }

    @Override
    public String toString() {
        return string;
    }
}
