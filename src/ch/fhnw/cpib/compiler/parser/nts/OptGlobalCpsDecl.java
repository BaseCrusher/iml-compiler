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
        token = Parser.getCurrentToken();
        if (token.hasTerminal(GLOBAL)) {
            Parser.consume(GLOBAL);
            cpsDecl = new CpsDecl();
            string = token.getTerminal().toString() + " " + cpsDecl.toString();
        }
        else if(token.hasTerminal(DO)) {
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

    public INtsParser getCpsDecl() {
        return cpsDecl;
    }

    public INtsParser getEpsilon() {
        return epsilon;
    }
}
