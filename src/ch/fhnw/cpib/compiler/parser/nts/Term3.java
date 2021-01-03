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

public class Term3 implements INtsParser {
    private final IToken token;
    private final Factor factor;
    private final RepMultOprFactor repMultOprFactor;

    public Term3(Environment environment, boolean isAssignment) throws GrammarError {
        token = Parser.getCurrentToken();
        if (token.hasTerminal(ARRLEN, LPAREN, MONOPR, IDENT, LITERAL)) {
            factor = new Factor(environment, isAssignment);
            repMultOprFactor = new RepMultOprFactor(environment, isAssignment);
        }
        else {
            throw new GrammarError(token);
        }
    }

    public IAbstractNode toAbsSyn(boolean isAssignment) {
        return repMultOprFactor.toAbsSyn(factor.toAbsSyn(isAssignment));
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

    public Identifier getIdentifier() {
        return factor.getOriginalIdentifier();
    }

    public IToken getLiteral() {
        return factor.getLiteral();
    }
}
