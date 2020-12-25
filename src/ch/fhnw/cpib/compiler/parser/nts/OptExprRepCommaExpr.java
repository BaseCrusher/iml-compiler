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
    private INtsParser optRepCommaExpr;
    private INtsParser epsilon;
    private final String string;

    public OptExprRepCommaExpr() throws GrammarError {
        token = Parser.getCurrentToken();
        if (token.hasTerminal(LPAREN, MONOPR, IDENT, LITERAL)) {
            expr = new Expr();
            optRepCommaExpr = new OptRepCommaExpr();
            string = expr.toString() + " " + optRepCommaExpr.toString();
        }
        else if (token.hasTerminal(RPAREN)) {
            epsilon = new Epsilon();
            string = epsilon.toString();
        }
        else {
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

    public INtsParser getExpr() {
        return expr;
    }

    public INtsParser getOptRepCommaExpr() {
        return optRepCommaExpr;
    }

    public INtsParser getEpsilon() {
        return epsilon;
    }
}
