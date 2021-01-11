package ch.fhnw.cpib.compiler.parser.nts;

import ch.fhnw.cpib.compiler.error.GrammarError;
import ch.fhnw.cpib.compiler.parser.IAbstractNode;
import ch.fhnw.cpib.compiler.parser.INtsParser;
import ch.fhnw.cpib.compiler.parser.Parser;
import ch.fhnw.cpib.compiler.tokens.IToken;

import static ch.fhnw.cpib.compiler.tokens.enums.KeywordTerminals.PIPE;

public class ArrayDecl implements INtsParser {
    private final IToken token;
    private final OptLit optLit;
    private final String string;

    public ArrayDecl() throws GrammarError {
        this.token = Parser.getCurrentToken();
        if (token.hasTerminal(PIPE)) {
            Parser.consume(PIPE);
            optLit = new OptLit();
            Parser.consume(PIPE);
            string = "|" + optLit.toString() + "|";
        } else {
            throw new GrammarError(token);
        }
    }

    @Override
    public String toString() {
        return string;
    }

    public IAbstractNode toAbsSyn() {
        return optLit.toAbsSyn();
    }

    public IToken getToken() {
        return token;
    }

    public OptLit getOptLit() {
        return optLit;
    }
}
