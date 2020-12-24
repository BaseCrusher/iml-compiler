package ch.fhnw.cpib.compiler.parser.nts;

import ch.fhnw.cpib.compiler.error.GrammarError;
import ch.fhnw.cpib.compiler.parser.INtsParser;
import ch.fhnw.cpib.compiler.parser.Parser;
import ch.fhnw.cpib.compiler.tokens.IToken;

import static ch.fhnw.cpib.compiler.tokens.enums.KeywordTerminals.PIPE;

public class ArrayDecl implements INtsParser {
    private IToken token;
    private INtsParser optLit;
    private String string;

    public ArrayDecl(IToken token) throws GrammarError {
        this.token = token;
        if (token.hasTerminal(PIPE)) {
            optLit = new OptLit();
            Parser.consume(PIPE);
            string = token.getTerminal().toString() + " : " + optLit.toString() + " : " + PIPE.toString();
        } else {
            Epsilon epsilon = new Epsilon();
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

    public INtsParser getOptLit() {
        return optLit;
    }
}
