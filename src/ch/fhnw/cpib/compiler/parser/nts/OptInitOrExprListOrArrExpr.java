package ch.fhnw.cpib.compiler.parser.nts;

import ch.fhnw.cpib.compiler.error.GrammarError;
import ch.fhnw.cpib.compiler.parser.Environment;
import ch.fhnw.cpib.compiler.parser.IAbstractNode;
import ch.fhnw.cpib.compiler.parser.INtsParser;
import ch.fhnw.cpib.compiler.parser.Parser;
import ch.fhnw.cpib.compiler.parser.abstracts.AbsArrExpr;
import ch.fhnw.cpib.compiler.parser.abstracts.AbsFunCallExpr;
import ch.fhnw.cpib.compiler.parser.abstracts.AbsStoreExpr;
import ch.fhnw.cpib.compiler.parser.abstracts.RoutineCall;
import ch.fhnw.cpib.compiler.tokens.IToken;

import static ch.fhnw.cpib.compiler.tokens.enums.AttributeTerminals.*;
import static ch.fhnw.cpib.compiler.tokens.enums.KeywordTerminals.BECOMES;
import static ch.fhnw.cpib.compiler.tokens.enums.KeywordTerminals.COMMA;
import static ch.fhnw.cpib.compiler.tokens.enums.KeywordTerminals.DO;
import static ch.fhnw.cpib.compiler.tokens.enums.KeywordTerminals.ELSE;
import static ch.fhnw.cpib.compiler.tokens.enums.KeywordTerminals.ENDFUN;
import static ch.fhnw.cpib.compiler.tokens.enums.KeywordTerminals.ENDIF;
import static ch.fhnw.cpib.compiler.tokens.enums.KeywordTerminals.ENDPROC;
import static ch.fhnw.cpib.compiler.tokens.enums.KeywordTerminals.ENDPROGRAM;
import static ch.fhnw.cpib.compiler.tokens.enums.KeywordTerminals.ENDWHILE;
import static ch.fhnw.cpib.compiler.tokens.enums.KeywordTerminals.INIT;
import static ch.fhnw.cpib.compiler.tokens.enums.KeywordTerminals.LBRACK;
import static ch.fhnw.cpib.compiler.tokens.enums.KeywordTerminals.LPAREN;
import static ch.fhnw.cpib.compiler.tokens.enums.KeywordTerminals.RBRACK;
import static ch.fhnw.cpib.compiler.tokens.enums.KeywordTerminals.RPAREN;
import static ch.fhnw.cpib.compiler.tokens.enums.KeywordTerminals.SEMICOLON;
import static ch.fhnw.cpib.compiler.tokens.enums.KeywordTerminals.THEN;

public class OptInitOrExprListOrArrExpr implements INtsParser {
    private final IToken token;
    private IToken finalToken;
    private ExprList exprList;
    private Expr expr;
    private OptInit optInit;
    private Epsilon epsilon;
    private final String string;

    public OptInitOrExprListOrArrExpr(Environment environment) throws GrammarError {
        token = Parser.getCurrentToken();
        if (token.hasTerminal(INIT)) {
            Parser.consume(INIT);
            string = token.getTerminal().toString();
        }
        else if (token.hasTerminal(LPAREN)) {
            exprList = new ExprList(environment);
            string = exprList.toString();
        }
        else if (token.hasTerminal(LBRACK)) {
            Parser.consume(LBRACK);
            expr = new Expr(environment, false);
            Parser.consume(RBRACK);
            optInit = new OptInit();
            finalToken = Parser.getCurrentToken();
            string = "(" + expr.toString()  + ")";
        }
        else if (token.hasTerminal(COMMA, RBRACK, RPAREN, DO, THEN, ENDWHILE, ENDIF, ELSE, ENDPROC, ENDFUN, ENDPROGRAM, SEMICOLON, BECOMES, BOOLOPR, RELOPR, ADDOPR, MULTOPR, DIVOPR)) {
            epsilon = new Epsilon();
            string = epsilon.toString();
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

    public INtsParser getExprList() {
        return exprList;
    }

    public INtsParser getEpsilon() {
        return epsilon;
    }

    public IAbstractNode toAbsSyn(Identifier identifier, boolean isAssignment) {
        if (this.token.hasTerminal(INIT)) {
            return new AbsStoreExpr(identifier, true, isAssignment);
        }
        else if (this.token.hasTerminal(LPAREN)) {
            return new AbsFunCallExpr(new RoutineCall(identifier, exprList.toAbsSyn()));
        }
        else if (this.token.hasTerminal(LBRACK)) {
            if (optInit.getEpsilon() == null) {
                if (finalToken.hasTerminal(BECOMES)) {
                    return new AbsArrExpr(identifier, expr.toAbsSyn(), true);
                }
                return new AbsArrExpr(identifier, expr.toAbsSyn());
            } else if (optInit.getToken().hasTerminal(BECOMES)) {
                return new AbsArrExpr(identifier, expr.toAbsSyn(), true);
            }
            return new AbsArrExpr(identifier, expr.toAbsSyn(), optInit.toAbsSyn(), isAssignment);
        }
        return new AbsStoreExpr(identifier, false, isAssignment);
    }
}
