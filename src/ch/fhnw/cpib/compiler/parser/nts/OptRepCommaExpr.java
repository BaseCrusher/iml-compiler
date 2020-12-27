package ch.fhnw.cpib.compiler.parser.nts;

import ch.fhnw.cpib.compiler.error.GrammarError;
import ch.fhnw.cpib.compiler.parser.IAbstractNode;
import ch.fhnw.cpib.compiler.parser.INtsParser;
import ch.fhnw.cpib.compiler.parser.IToAbsNodeList;
import ch.fhnw.cpib.compiler.parser.Parser;
import ch.fhnw.cpib.compiler.tokens.IToken;

import java.util.List;

import static ch.fhnw.cpib.compiler.tokens.enums.KeywordTerminals.COMMA;
import static ch.fhnw.cpib.compiler.tokens.enums.KeywordTerminals.RPAREN;

public class OptRepCommaExpr implements INtsParser, IToAbsNodeList {
    private final IToken token;
    private INtsParser expr;
    private INtsParser optRepCommaExpr;
    private INtsParser epsilon;
    private final String string;

    public OptRepCommaExpr() throws GrammarError {
        token = Parser.getCurrentToken();
        if (token.hasTerminal(COMMA)) {
            Parser.consume(COMMA);
            expr = new Expr();
            optRepCommaExpr = new OptRepCommaExpr();
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

    @Override
    public List<IAbstractNode> toAbsSyn() {
        return null;
    }
}
