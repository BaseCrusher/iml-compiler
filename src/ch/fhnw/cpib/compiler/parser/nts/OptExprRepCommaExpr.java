package ch.fhnw.cpib.compiler.parser.nts;

import ch.fhnw.cpib.compiler.error.GrammarError;
import ch.fhnw.cpib.compiler.parser.INtsParser;
import ch.fhnw.cpib.compiler.parser.Parser;
import ch.fhnw.cpib.compiler.tokens.IToken;

import static ch.fhnw.cpib.compiler.tokens.enums.AttributeTerminals.IDENT;
import static ch.fhnw.cpib.compiler.tokens.enums.AttributeTerminals.LITERAL;
import static ch.fhnw.cpib.compiler.tokens.enums.AttributeTerminals.MONOPR;
import static ch.fhnw.cpib.compiler.tokens.enums.KeywordTerminals.LPAREN;
import static ch.fhnw.cpib.compiler.tokens.enums.KeywordTerminals.RPAREN;

public class OptExprRepCommaExpr implements INtsParser {
    private final IToken token;
    private INtsParser expr;
    private INtsParser repCommaExpr;
    private INtsParser epsilon;
    private String string;

    public OptExprRepCommaExpr() throws GrammarError {
        token = Parser.consume(LPAREN, MONOPR, IDENT, LITERAL, RPAREN);
        if (token.hasTerminal(RPAREN)) {
            epsilon = new Epsilon();
            string = token.getTerminal().toString() + " " + epsilon.toString();
        } else {
            expr = new Expr();
            repCommaExpr = null;
            string = expr.toString() + " " + repCommaExpr.toString();
        }
    }

    @Override
    public String toString() {
        return string;
    }

    public IToken getToken() {
        return token;
    }

    public INtsParser getExpr() {
        return expr;
    }

    public INtsParser getRepCommaExpr() {
        return repCommaExpr;
    }

    public INtsParser getEpsilon() {
        return epsilon;
    }
}
