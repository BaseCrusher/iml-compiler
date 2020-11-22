package ch.fhnw.cpib.compiler.parser.nts;

import ch.fhnw.cpib.compiler.error.GrammarError;
import ch.fhnw.cpib.compiler.parser.INtsParser;
import ch.fhnw.cpib.compiler.parser.Parser;
import ch.fhnw.cpib.compiler.tokens.IToken;

import static ch.fhnw.cpib.compiler.tokens.enums.KeywordTerminals.COMMA;
import static ch.fhnw.cpib.compiler.tokens.enums.KeywordTerminals.RPAREN;

public class RepCommaExpr implements INtsParser {
    private final IToken token;
    private INtsParser expr;
    private INtsParser repCommaExpr;
    private INtsParser epsilon;
    private final String string;

    public RepCommaExpr() throws GrammarError {
        token = Parser.consume(COMMA, RPAREN);
        if (token.hasTerminal(COMMA)) {
            expr = new Expr();
            repCommaExpr = new RepCommaExpr();
            string = token.getTerminal().toString() + " " + expr.toString() + " " + repCommaExpr.toString();
        } else {
            epsilon = new Epsilon();
            string = token.getTerminal().toString() + " " + epsilon.toString();
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
