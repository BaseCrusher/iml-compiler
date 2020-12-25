package ch.fhnw.cpib.compiler.parser.nts;

import ch.fhnw.cpib.compiler.error.GrammarError;
import ch.fhnw.cpib.compiler.parser.IAbstractNode;
import ch.fhnw.cpib.compiler.parser.INtsParser;
import ch.fhnw.cpib.compiler.parser.Parser;
import ch.fhnw.cpib.compiler.tokens.IToken;

import static ch.fhnw.cpib.compiler.tokens.enums.KeywordTerminals.PIPE;

public class ArrayDecl implements INtsParser {
    private IToken token;
    private INtsParser optLit;
    private String string;

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

    @Override
    public IAbstractNode toAbsSyn() {
        return optLit.toAbsSyn();
    }

    public IToken getToken() {
        return token;
    }

    public INtsParser getOptLit() {
        return optLit;
    }
}
