package ch.fhnw.cpib.compiler.parser.nts;

import ch.fhnw.cpib.compiler.error.GrammarError;
import ch.fhnw.cpib.compiler.parser.Environment;
import ch.fhnw.cpib.compiler.parser.IAbstractNode;
import ch.fhnw.cpib.compiler.parser.INtsParser;
import ch.fhnw.cpib.compiler.parser.Parser;
import ch.fhnw.cpib.compiler.parser.abstracts.AbsDyadicExpr;
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
import static ch.fhnw.cpib.compiler.tokens.enums.KeywordTerminals.RBRACK;
import static ch.fhnw.cpib.compiler.tokens.enums.KeywordTerminals.RPAREN;
import static ch.fhnw.cpib.compiler.tokens.enums.KeywordTerminals.SEMICOLON;
import static ch.fhnw.cpib.compiler.tokens.enums.KeywordTerminals.THEN;

public class RepMultOprFactor implements INtsParser {
    private final IToken token;
    private Factor factor;
    private String operator;
    private RepMultOprFactor repMultOprFactor;
    private Epsilon epsilon;
    private final String string;

    public RepMultOprFactor(Environment environment, boolean isAssignment) throws GrammarError {
        token = Parser.getCurrentToken();
        if (token.hasTerminal(MULTOPR, DIVOPR)) {
            operator = Parser.consume(MULTOPR, DIVOPR).getValue();
            factor = new Factor(environment, isAssignment);
            repMultOprFactor = new RepMultOprFactor(environment, isAssignment);
            string = token.toString() + " " + factor.toString() + " " + repMultOprFactor.toString();
        } else if (token.hasTerminal(COMMA, RBRACK, RPAREN, DO, THEN, ENDWHILE, ENDIF, ELSE, ENDPROC, ENDFUN, ENDPROGRAM, SEMICOLON, BECOMES, BOOLOPR, RELOPR, ADDOPR)) {
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

    public INtsParser getFactor() {
        return factor;
    }

    public INtsParser getRepMultOprFactor() {
        return repMultOprFactor;
    }

    public INtsParser getEpsilon() {
        return epsilon;
    }

    public IAbstractNode toAbsSyn(IAbstractNode absFactor) {
        if (epsilon == null) {
            return new AbsDyadicExpr(operator, absFactor, repMultOprFactor.toAbsSyn(factor.toAbsSyn(false)));
        }
        else {
            return absFactor;
        }
    }
}
