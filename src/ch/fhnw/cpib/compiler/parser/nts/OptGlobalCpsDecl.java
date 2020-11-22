package ch.fhnw.cpib.compiler.parser.nts;

import ch.fhnw.cpib.compiler.error.GrammarError;
import ch.fhnw.cpib.compiler.parser.INtsParser;
import ch.fhnw.cpib.compiler.parser.Parser;
import ch.fhnw.cpib.compiler.tokens.IToken;

import static ch.fhnw.cpib.compiler.tokens.enums.KeywordTerminals.DO;
import static ch.fhnw.cpib.compiler.tokens.enums.KeywordTerminals.GLOBAL;

public class OptGlobalCpsDecl implements INtsParser {
    private final IToken token;
    private INtsParser cpsDecl;
    private INtsParser epsilon;
    private final String string;

    public OptGlobalCpsDecl() throws GrammarError {
        token = Parser.consume(GLOBAL, DO);
        if (token.hasTerminal(GLOBAL)) {
            cpsDecl = new CpsDecl();
            string = token.getTerminal().toString() + " " + cpsDecl.toString();
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

    public INtsParser getCpsDecl() {
        return cpsDecl;
    }

    public INtsParser getEpsilon() {
        return epsilon;
    }
}
