package ch.fhnw.cpib.compiler.parser.nts;

import ch.fhnw.cpib.compiler.error.GrammarError;
import ch.fhnw.cpib.compiler.parser.INtsParser;
import ch.fhnw.cpib.compiler.parser.Parser;
import ch.fhnw.cpib.compiler.tokens.IToken;

import static ch.fhnw.cpib.compiler.tokens.enums.KeywordTerminals.DO;
import static ch.fhnw.cpib.compiler.tokens.enums.KeywordTerminals.SEMICOLON;

public class RepSemicolonStoDecl implements INtsParser {
    private final IToken token;
    private INtsParser stoDecl;
    private INtsParser epsilon;
    private final String string;

    public RepSemicolonStoDecl() throws GrammarError {
        token = Parser.getCurrentToken();
        if (token.hasTerminal(SEMICOLON)) {
            Parser.consume(SEMICOLON);
            stoDecl = new StoDecl();
            string = "; \n " + stoDecl.toString();
        }
        else if (token.hasTerminal(DO)) {
            epsilon = new Epsilon();
            string = epsilon.toString();
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

    public INtsParser getEpsilon() {
        return epsilon;
    }
}
