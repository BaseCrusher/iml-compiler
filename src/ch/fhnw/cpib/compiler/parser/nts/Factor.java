package ch.fhnw.cpib.compiler.parser.nts;

import java.math.BigInteger;

import ch.fhnw.cpib.compiler.error.GrammarError;
import ch.fhnw.cpib.compiler.parser.Environment;
import ch.fhnw.cpib.compiler.parser.IAbstractNode;
import ch.fhnw.cpib.compiler.parser.INtsParser;
import ch.fhnw.cpib.compiler.parser.Parser;
import ch.fhnw.cpib.compiler.parser.abstracts.AbsArrLenExpr;
import ch.fhnw.cpib.compiler.parser.abstracts.AbsBoolLiteralExpr;
import ch.fhnw.cpib.compiler.parser.abstracts.AbsIntLiteralExpr;
import ch.fhnw.cpib.compiler.parser.abstracts.AbsMonadicExpr;
import ch.fhnw.cpib.compiler.tokens.AttributeToken;
import ch.fhnw.cpib.compiler.tokens.IToken;

import static ch.fhnw.cpib.compiler.tokens.enums.AttributeTerminals.IDENT;
import static ch.fhnw.cpib.compiler.tokens.enums.AttributeTerminals.LITERAL;
import static ch.fhnw.cpib.compiler.tokens.enums.AttributeTerminals.MONOPR;
import static ch.fhnw.cpib.compiler.tokens.enums.KeywordTerminals.ARRLEN;
import static ch.fhnw.cpib.compiler.tokens.enums.KeywordTerminals.LPAREN;
import static ch.fhnw.cpib.compiler.tokens.enums.KeywordTerminals.RPAREN;

public class Factor implements INtsParser {
    private final IToken token;
    private Identifier identifier;
    private OptInitOrExprListOrArrExpr optInitOrExprListOrArrExpr;
    private Factor factor;
    private Expr expr;
    private final String string;
    private final boolean isAssignment;

    public Factor(Environment environment, boolean isAssignment) throws GrammarError {
        this.isAssignment = isAssignment;
        token = Parser.getCurrentToken();
        if (token.hasTerminal(LITERAL)) {
            Parser.consume(LITERAL);
            string = token.toString();
        }
        else if (token.hasTerminal(IDENT)) {
            Parser.consume(IDENT);
            this.identifier = new Identifier(token, environment);
            optInitOrExprListOrArrExpr = new OptInitOrExprListOrArrExpr(environment);
            string = token.toString() + " " + optInitOrExprListOrArrExpr.toString();
        } else if (token.hasTerminal(MONOPR)) {
            Parser.consume(MONOPR);
            factor = new Factor(environment, isAssignment);
            string = token.toString() + " " + factor.toString();
        } else if (token.hasTerminal(LPAREN)) {
            Parser.consume(LPAREN);
            expr = new Expr(environment, false);
            Parser.consume(RPAREN);
            string = "(" + expr.toString() + ")";
        }
        else if (token.hasTerminal(ARRLEN)) {
            Parser.consume(ARRLEN);
            Parser.consume(LPAREN);
            IToken identifier = Parser.consume(IDENT);
            this.identifier = new Identifier(identifier, environment);
            Parser.consume(RPAREN);
            string = "ARRLEN(" + identifier.toString() + ")";
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

    public IToken getIdentifier() {
        return identifier.ident;
    }

    public IToken getToken() {
        return token;
    }

    public INtsParser getOptInitOrExprListOrArrExpr() {
        return optInitOrExprListOrArrExpr;
    }

    public INtsParser getFactor() {
        return factor;
    }

    public INtsParser getExpr() {
        return expr;
    }

    public IAbstractNode toAbsSyn(boolean isAssignment) {
        if (token.hasTerminal(LITERAL)) {
            if (((AttributeToken)token).getOriginalValue() instanceof BigInteger) {
                return new AbsIntLiteralExpr(token);
            } else {
                return new AbsBoolLiteralExpr(token);
            }
        }
        else if (token.hasTerminal(IDENT)) {
            return optInitOrExprListOrArrExpr.toAbsSyn(identifier, this.isAssignment);
        } else if (token.hasTerminal(MONOPR)) {
            return new AbsMonadicExpr(token.getValue(), factor.toAbsSyn(this.isAssignment));
        } else if (token.hasTerminal(LPAREN)) {
            return expr.toAbsSyn();
        }
        else if (token.hasTerminal(ARRLEN)) {
            return new AbsArrLenExpr(identifier);
        }
        throw new Error("Factor has no corresponding terminal");
    }

    public Identifier getOriginalIdentifier() {
        return identifier;
    }

    public IToken getLiteral(){
        if (token.hasTerminal(LITERAL)) {
            return token;
        }
        return null;
    }
}
