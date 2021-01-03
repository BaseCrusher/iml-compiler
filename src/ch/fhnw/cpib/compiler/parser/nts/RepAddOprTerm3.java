package ch.fhnw.cpib.compiler.parser.nts;

import ch.fhnw.cpib.compiler.error.GrammarError;
import ch.fhnw.cpib.compiler.parser.Environment;
import ch.fhnw.cpib.compiler.parser.IAbstractNode;
import ch.fhnw.cpib.compiler.parser.INtsParser;
import ch.fhnw.cpib.compiler.parser.Parser;
import ch.fhnw.cpib.compiler.parser.abstracts.AbsDyadicExpr;
import ch.fhnw.cpib.compiler.tokens.IToken;

import static ch.fhnw.cpib.compiler.tokens.enums.AttributeTerminals.ADDOPR;
import static ch.fhnw.cpib.compiler.tokens.enums.AttributeTerminals.BOOLOPR;
import static ch.fhnw.cpib.compiler.tokens.enums.AttributeTerminals.RELOPR;
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

public class RepAddOprTerm3 implements INtsParser {
    private final IToken token;
    private Term3 term3;
    private String operator;
    private RepAddOprTerm3 repAddOprTerm3;
    private Epsilon epsilon;
    private String string;

    public RepAddOprTerm3(Environment environment, boolean isAssignment) throws GrammarError {
        token = Parser.getCurrentToken();
        if (token.hasTerminal(ADDOPR)) {
            operator = Parser.consume(ADDOPR).getValue();
            term3 = new Term3(environment, isAssignment);
            repAddOprTerm3 = new RepAddOprTerm3(environment, isAssignment);
            string = token.toString() + " " + term3.toString() + " " + repAddOprTerm3.toString();
        } else if (token.hasTerminal(COMMA, RBRACK, RPAREN, DO, THEN, ENDWHILE, ENDIF, ELSE, ENDPROC, ENDFUN, ENDPROGRAM, SEMICOLON, BECOMES, BOOLOPR, RELOPR)) {
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

    public INtsParser getTerm3() {
        return term3;
    }

    public INtsParser getRepAddOprTerm3() {
        return repAddOprTerm3;
    }

    public INtsParser getEpsilon() {
        return epsilon;
    }

    public IAbstractNode toAbsSyn(IAbstractNode absNode) {
        if (epsilon == null) {
            return repAddOprTerm3.toAbsSyn(new AbsDyadicExpr(operator, absNode, term3.toAbsSyn(false)));
        }
        else {
            return absNode;
        }
    }
}
