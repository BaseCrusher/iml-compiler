package ch.fhnw.cpib.compiler.parser.nts;

import ch.fhnw.cpib.compiler.error.GrammarError;
import ch.fhnw.cpib.compiler.parser.INtsParser;
import ch.fhnw.cpib.compiler.parser.Parser;
import ch.fhnw.cpib.compiler.tokens.IToken;

import static ch.fhnw.cpib.compiler.tokens.enums.KeywordTerminals.LPAREN;
import static ch.fhnw.cpib.compiler.tokens.enums.KeywordTerminals.RPAREN;

public class ExprList implements INtsParser {
    private final IToken token;
    private INtsParser optExprRepCommaExpr;

    public ExprList() throws GrammarError {
        token = Parser.getCurrentToken();
        if (token.hasTerminal(LPAREN)) {
            Parser.consume(LPAREN);
            optExprRepCommaExpr = new OptExprRepCommaExpr();
            Parser.consume(RPAREN);
        }
        else {
            throw new GrammarError(token);
        }
    }

    @Override
    public String toString() {
        return "(" + optExprRepCommaExpr.toString() + ")";
    }

    public IToken getToken() {
        return token;
    }

    public INtsParser getOptExprRepCommaExpr() {
        return optExprRepCommaExpr;
    }
}
