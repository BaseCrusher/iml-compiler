package ch.fhnw.cpib.compiler.parser.nts;

import ch.fhnw.cpib.compiler.error.GrammarError;
import ch.fhnw.cpib.compiler.parser.Environment;
import ch.fhnw.cpib.compiler.parser.IAbstractNode;
import ch.fhnw.cpib.compiler.parser.INtsParser;
import ch.fhnw.cpib.compiler.parser.Parser;
import ch.fhnw.cpib.compiler.tokens.IToken;

import static ch.fhnw.cpib.compiler.tokens.enums.AttributeTerminals.IDENT;
import static ch.fhnw.cpib.compiler.tokens.enums.AttributeTerminals.LITERAL;
import static ch.fhnw.cpib.compiler.tokens.enums.AttributeTerminals.MONOPR;
import static ch.fhnw.cpib.compiler.tokens.enums.KeywordTerminals.ARRLEN;
import static ch.fhnw.cpib.compiler.tokens.enums.KeywordTerminals.LPAREN;

public class Term1 implements INtsParser {
    private final IToken token;
    private final Term2 term2;
    private final OptRelOprTerm2 optRelOprTerm2;
    private boolean isAssignment = false;

    public Term1(Environment environment) throws GrammarError {
        token = Parser.getCurrentToken();
        if (token.hasTerminal(ARRLEN, LPAREN, MONOPR, IDENT, LITERAL)) {
            term2 = new Term2(environment);
            optRelOprTerm2 = new OptRelOprTerm2(environment);
        }
        else {
            throw new GrammarError(token);
        }
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

    public IAbstractNode toAbsSyn(boolean isAssignment) {
        return optRelOprTerm2.toAbsSyn(term2.toAbsSyn(isAssignment));
    }

    public Identifier getIdentifier() {
        return term2.getIdentifier();
    }
}
