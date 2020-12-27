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

public class Term2 implements INtsParser {
    private final IToken token;
    private Term3 term3;
    private RepAddOprTerm3 repAddOprTerm3;

    public Term2(Environment environment) throws GrammarError {
        token = Parser.getCurrentToken();
        if (token.hasTerminal(ARRLEN, LPAREN, MONOPR, IDENT, LITERAL)) {
            term3 = new Term3(environment);
            repAddOprTerm3 = new RepAddOprTerm3(environment);
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

    public IAbstractNode toAbsSyn() {
        return repAddOprTerm3.toAbsSyn(term3.toAbsSyn());
    }

    public Identifier getIdentifier() {
        return term3.getIdentifier();
    }
}
