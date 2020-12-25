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

public class Factor implements INtsParser {
    private final IToken token;
    private INtsParser optInitOrExprList;
    private INtsParser factor;
    private INtsParser expr;
    private String string;

    public Factor() throws GrammarError {
        token = Parser.getCurrentToken();
        if (token.hasTerminal(LITERAL)) {
            Parser.consume(LITERAL);
        }
        else if (token.hasTerminal(IDENT)) {
            Parser.consume(IDENT);
            optInitOrExprList = new OptInitOrExprListOrArrExpr();
            string = token.getTerminal().toString() + " " + optInitOrExprList.toString();
        } else if (token.hasTerminal(MONOPR)) {
            Parser.consume(MONOPR);
            factor = new Factor();
            string = token.getTerminal().toString() + " " + factor.toString();
        } else if (token.hasTerminal(LPAREN)) {
            Parser.consume(LPAREN);
            expr = new Expr();
            Parser.consume(RPAREN);
            string = token.getTerminal().toString() + " " + expr.toString() + " RPAREN";
        }
        else {
            throw new GrammarError(token);
        }
    }

    @Override
    public String toString() {
        if (string == null) {
            return token.getTerminal().toString();
        }
        return string;
    }

    public IToken getToken() {
        return token;
    }

    public INtsParser getOptInitOrExprList() {
        return optInitOrExprList;
    }

    public INtsParser getFactor() {
        return factor;
    }

    public INtsParser getExpr() {
        return expr;
    }
}
