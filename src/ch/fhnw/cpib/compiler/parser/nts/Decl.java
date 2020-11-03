package ch.fhnw.cpib.compiler.parser.nts;

import ch.fhnw.cpib.compiler.error.GrammarError;
import ch.fhnw.cpib.compiler.parser.INtsParser;
import ch.fhnw.cpib.compiler.parser.Parser;
import ch.fhnw.cpib.compiler.tokens.IToken;

import static ch.fhnw.cpib.compiler.tokens.enums.AttributeTerminals.*;
import static ch.fhnw.cpib.compiler.tokens.enums.KeywordTerminals.*;

public class Decl implements INtsParser {
    private IToken token;
    private INtsParser stoDecl;
    private INtsParser funDecl;
    private INtsParser procDecl;
    private String string;

    public Decl() throws GrammarError {
        token = Parser.consume(IDENT, CHANGEMODE, FUN, PROC);
        if (token.hasTerminal(IDENT)
            || token.hasTerminal(CHANGEMODE)) {
            stoDecl = null;
            string = token.getTerminal().toString() + " " + stoDecl.toString();
        }
        else if (token.hasTerminal(FUN)) {
            funDecl = null;
            string = token.getTerminal().toString() + " " + funDecl.toString();
        }
        else {
            procDecl = null;
            string = token.getTerminal().toString() + " " + procDecl.toString();
        }
    }

    @Override
    public String toString() {
        return string;
    }
}
