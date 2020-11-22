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
        token = Parser.consume(LPAREN);
        optExprRepCommaExpr = new OptExprRepCommaExpr();
        Parser.consume(RPAREN);
    }

    @Override
    public String toString() {
        return token.getTerminal().toString() + " " + optExprRepCommaExpr.toString() + " RPAREN";
    }

    public IToken getToken() {
        return token;
    }

    public INtsParser getOptExprRepCommaExpr() {
        return optExprRepCommaExpr;
    }
}
