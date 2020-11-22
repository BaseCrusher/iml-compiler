package ch.fhnw.cpib.compiler.parser.nts;

import ch.fhnw.cpib.compiler.error.GrammarError;
import ch.fhnw.cpib.compiler.parser.INtsParser;
import ch.fhnw.cpib.compiler.parser.Parser;
import ch.fhnw.cpib.compiler.tokens.IToken;

import static ch.fhnw.cpib.compiler.tokens.enums.AttributeTerminals.IDENT;
import static ch.fhnw.cpib.compiler.tokens.enums.AttributeTerminals.LITERAL;
import static ch.fhnw.cpib.compiler.tokens.enums.AttributeTerminals.MONOPR;
import static ch.fhnw.cpib.compiler.tokens.enums.KeywordTerminals.LPAREN;

public class Term1 implements INtsParser {
    private final IToken token;
    private INtsParser term2;
    private INtsParser optRelOprTerm2;

    public Term1() throws GrammarError {
        token = Parser.consume(LPAREN, MONOPR, IDENT, LITERAL);
        term2 = new Term2();
        optRelOprTerm2 = new OptRelOprTerm2();
    }

    @Override
    public String toString() {
        return term2.toString() + " " + optRelOprTerm2.toString();
    }

    public IToken getToken() {
        return token;
    }

    public INtsParser getTerm2() {
        return term2;
    }

    public INtsParser getOptRelOprTerm2() {
        return optRelOprTerm2;
    }
}
