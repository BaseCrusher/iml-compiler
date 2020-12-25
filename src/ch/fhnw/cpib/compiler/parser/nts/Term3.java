package ch.fhnw.cpib.compiler.parser.nts;

import ch.fhnw.cpib.compiler.error.GrammarError;
import ch.fhnw.cpib.compiler.parser.INtsParser;
import ch.fhnw.cpib.compiler.parser.Parser;
import ch.fhnw.cpib.compiler.tokens.IToken;

import static ch.fhnw.cpib.compiler.tokens.enums.AttributeTerminals.IDENT;
import static ch.fhnw.cpib.compiler.tokens.enums.AttributeTerminals.LITERAL;
import static ch.fhnw.cpib.compiler.tokens.enums.AttributeTerminals.MONOPR;
import static ch.fhnw.cpib.compiler.tokens.enums.KeywordTerminals.LPAREN;

public class Term3 implements INtsParser {
    private final IToken token;
    private final INtsParser factor;
    private final INtsParser repMultOprFactor;

    public Term3() throws GrammarError {
        token = Parser.getCurrentToken();
        if (token.hasTerminal(LPAREN, MONOPR, IDENT, LITERAL)) {
            factor = new Factor();
            repMultOprFactor = new RepMultOprFactor();
        }
    }

    @Override
    public String toString() {
        return factor.toString() + " " + repMultOprFactor.toString();
    }

    public IToken getToken() {
        return token;
    }

    public INtsParser getFactor() {
        return factor;
    }

    public INtsParser getRepMultOprFactor() {
        return repMultOprFactor;
    }
}
