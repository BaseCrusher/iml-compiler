package ch.fhnw.cpib.compiler.parser.nts;

import ch.fhnw.cpib.compiler.error.GrammarError;
import ch.fhnw.cpib.compiler.parser.INtsParser;
import ch.fhnw.cpib.compiler.parser.Parser;
import ch.fhnw.cpib.compiler.tokens.IToken;

import static ch.fhnw.cpib.compiler.tokens.enums.AttributeTerminals.LITERAL;
import static ch.fhnw.cpib.compiler.tokens.enums.KeywordTerminals.PIPE;

public class OptLit implements INtsParser {
    private IToken token;
    private IToken literal;
    private String string;

    public OptLit() throws GrammarError {
        this.token = Parser.consume(LITERAL, PIPE);
        if (token.hasTerminal(LITERAL)) {
            this.literal = Parser.consume(LITERAL);
            string = token.getTerminal().toString() + " : " + literal.getTerminal().toString();
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

    public IToken getLiteral() {
        return literal;
    }
}