package ch.fhnw.cpib.compiler.parser.nts;

import ch.fhnw.cpib.compiler.error.GrammarError;
import ch.fhnw.cpib.compiler.parser.INtsParser;
import ch.fhnw.cpib.compiler.parser.Parser;
import ch.fhnw.cpib.compiler.tokens.IToken;

import static ch.fhnw.cpib.compiler.tokens.enums.AttributeTerminals.IDENT;
import static ch.fhnw.cpib.compiler.tokens.enums.AttributeTerminals.LITERAL;
import static ch.fhnw.cpib.compiler.tokens.enums.AttributeTerminals.MONOPR;
import static ch.fhnw.cpib.compiler.tokens.enums.KeywordTerminals.ARRLEN;
import static ch.fhnw.cpib.compiler.tokens.enums.KeywordTerminals.LPAREN;

public class Term2 implements INtsParser {
    private final IToken token;
    private INtsParser term3;
    private INtsParser repAddOprTerm3;

    public Term2() throws GrammarError {
        token = Parser.getCurrentToken();
        if (token.hasTerminal(ARRLEN, LPAREN, MONOPR, IDENT, LITERAL)) {
            term3 = new Term3();
            repAddOprTerm3 = new RepAddOprTerm3();
        }
        else {
            throw new GrammarError(token);
        }
    }

    @Override
    public String toString() {
        return term3.toString() + " " + repAddOprTerm3.toString();
    }

    public IToken getToken() {
        return token;
    }

    public INtsParser getTerm3() {
        return term3;
    }

    public INtsParser getRepAddOprTerm3() {
        return repAddOprTerm3;
    }
}
