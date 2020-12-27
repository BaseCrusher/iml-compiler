package ch.fhnw.cpib.compiler.parser.nts;

import ch.fhnw.cpib.compiler.error.GrammarError;
import ch.fhnw.cpib.compiler.parser.Environment;
import ch.fhnw.cpib.compiler.parser.IAbstractNode;
import ch.fhnw.cpib.compiler.parser.INtsParser;
import ch.fhnw.cpib.compiler.parser.Parser;
import ch.fhnw.cpib.compiler.tokens.IToken;

import java.util.ArrayList;
import java.util.List;

import static ch.fhnw.cpib.compiler.tokens.enums.KeywordTerminals.COMMA;
import static ch.fhnw.cpib.compiler.tokens.enums.KeywordTerminals.RPAREN;

public class OptRepCommaExpr implements INtsParser {
    private final IToken token;
    private Expr expr;
    private OptRepCommaExpr optRepCommaExpr;
    private Epsilon epsilon;
    private final String string;

    public OptRepCommaExpr(Environment environment) throws GrammarError {
        token = Parser.getCurrentToken();
        if (token.hasTerminal(COMMA)) {
            Parser.consume(COMMA);
            expr = new Expr(environment);
            optRepCommaExpr = new OptRepCommaExpr(environment);
            string = ", " + expr.toString() + " " + optRepCommaExpr.toString();
        } else if (token.hasTerminal(RPAREN)) {
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

    public List<IAbstractNode> toAbsSyn() {
        if (epsilon == null) {
            List<IAbstractNode> exprList = new ArrayList<>();
            exprList.add(expr.toAbsSyn());
            exprList.addAll(optRepCommaExpr.toAbsSyn());
            return exprList;
        }
        return new ArrayList<>();
    }
}
