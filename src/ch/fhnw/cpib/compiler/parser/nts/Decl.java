package ch.fhnw.cpib.compiler.parser.nts;

import ch.fhnw.cpib.compiler.error.GrammarError;
import ch.fhnw.cpib.compiler.parser.Environment;
import ch.fhnw.cpib.compiler.parser.IAbstractNode;
import ch.fhnw.cpib.compiler.parser.INtsParser;
import ch.fhnw.cpib.compiler.parser.Parser;
import ch.fhnw.cpib.compiler.tokens.IToken;

import static ch.fhnw.cpib.compiler.tokens.enums.AttributeTerminals.CHANGEMODE;
import static ch.fhnw.cpib.compiler.tokens.enums.AttributeTerminals.IDENT;
import static ch.fhnw.cpib.compiler.tokens.enums.KeywordTerminals.FUN;
import static ch.fhnw.cpib.compiler.tokens.enums.KeywordTerminals.PROC;

public class Decl implements INtsParser {
    private final IToken token;
    private StoDecl stoDecl;
    private FunDecl funDecl;
    private ProcDecl procDecl;
    private final String string;

    public Decl(Environment environment) throws GrammarError {
        token = Parser.getCurrentToken();
        if (token.hasTerminal(IDENT, CHANGEMODE)) {
            stoDecl = new StoDecl(environment, false);
            string = stoDecl.toString();
        }
        else if (token.hasTerminal(FUN)) {
            funDecl = new FunDecl(environment);
            string = funDecl.toString();
        }
        else if (token.hasTerminal(PROC)) {
            procDecl = new ProcDecl(environment);
            string = procDecl.toString();
        }
        else {
            throw new GrammarError(token);
        }
    }

    @Override
    public String toString() {
        return string;
    }

    public IAbstractNode toAbsSyn() {
        if (token.hasTerminal(IDENT, CHANGEMODE)) {
            return stoDecl.toAbsSyn();
        }
        else if (token.hasTerminal(FUN)) {
            return funDecl.toAbsSyn();
        }
        else {
            return procDecl.toAbsSyn();
        }
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
