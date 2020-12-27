package ch.fhnw.cpib.compiler.parser.nts;

import java.math.BigInteger;

import ch.fhnw.cpib.compiler.error.GrammarError;
import ch.fhnw.cpib.compiler.parser.IAbstractNode;
import ch.fhnw.cpib.compiler.parser.INtsParser;
import ch.fhnw.cpib.compiler.parser.Parser;
import ch.fhnw.cpib.compiler.parser.abstracts.AbsIntLiteralExpr;
import ch.fhnw.cpib.compiler.tokens.AttributeToken;
import ch.fhnw.cpib.compiler.tokens.IToken;

import static ch.fhnw.cpib.compiler.tokens.enums.AttributeTerminals.LITERAL;
import static ch.fhnw.cpib.compiler.tokens.enums.KeywordTerminals.PIPE;

public class OptLit implements INtsParser {
    private final IToken token;
    private IToken literal;
    private final String string;
    private Epsilon epsilon;

    public OptLit() throws GrammarError {
        this.token = Parser.getCurrentToken();
        if (token.hasTerminal(LITERAL)) {
            this.literal = Parser.consume(LITERAL);
            string = token.getTerminal().toString() + " : " + literal.getTerminal().toString();
        } else if(token.hasTerminal(PIPE)) {
            epsilon = new Epsilon();
            string = epsilon.toString();
        } else {
            throw new GrammarError(token);
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

    public IAbstractNode toAbsSyn() {
        if (epsilon == null) {
            if (((AttributeToken)token).getOriginalValue() instanceof BigInteger) {
                return new AbsIntLiteralExpr(token);
            } else {
                throw new Error("Only integer allowed to specify the array size!");
            }
        }
        return null;
    }

    public Epsilon getEpsilon() {
        return epsilon;
    }
}
