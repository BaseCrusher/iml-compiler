package ch.fhnw.cpib.compiler.parser.nts;

import ch.fhnw.cpib.compiler.error.GrammarError;
import ch.fhnw.cpib.compiler.parser.INtsParser;
import ch.fhnw.cpib.compiler.parser.Parser;
import ch.fhnw.cpib.compiler.tokens.IToken;
import ch.fhnw.cpib.compiler.tokens.enums.AttributeTerminals;
import sun.tools.jstat.Literal;

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

    public Factor() throws GrammarError {
        token = Parser.consume(LITERAL, IDENT, MONOPR, LPAREN);
        if (token.hasTerminal(IDENT)) {
            optInitOrExprList = null;
        } else if (token.hasTerminal(MONOPR)) {
            factor = new Factor();
        } else if (token.hasTerminal(LPAREN)) {
            expr = new Expr();
            Parser.consume(RPAREN);
        }
    }
}
