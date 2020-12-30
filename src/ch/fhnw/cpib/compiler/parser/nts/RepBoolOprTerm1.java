package ch.fhnw.cpib.compiler.parser.nts;

import ch.fhnw.cpib.compiler.error.GrammarError;
import ch.fhnw.cpib.compiler.parser.Environment;
import ch.fhnw.cpib.compiler.parser.IAbstractNode;
import ch.fhnw.cpib.compiler.parser.INtsParser;
import ch.fhnw.cpib.compiler.parser.Parser;
import ch.fhnw.cpib.compiler.parser.abstracts.AbsDyadicExpr;
import ch.fhnw.cpib.compiler.tokens.IToken;

import static ch.fhnw.cpib.compiler.tokens.enums.AttributeTerminals.BOOLOPR;
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

public class RepBoolOprTerm1 implements INtsParser {
    private final IToken token;
    private Term1 term1;
    private RepBoolOprTerm1 repBoolOprTerm1;
    private Epsilon epsilon;
    private final String string;

    public RepBoolOprTerm1(Environment environment) throws GrammarError {
        token = Parser.getCurrentToken();
        if (token.hasTerminal(BOOLOPR)) {
            Parser.consume(BOOLOPR);
            term1 = new Term1(environment);
            repBoolOprTerm1 = new RepBoolOprTerm1(environment);
            string = token.toString() + " " + term1.toString() + " " + repBoolOprTerm1.toString();
        }
        else if (token.hasTerminal(COMMA, RBRACK, RPAREN, DO, THEN, ENDWHILE, ENDIF, ENDIF, ELSE, ENDPROC, ENDFUN, ENDPROGRAM, SEMICOLON, BECOMES)) {
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

    public INtsParser getTerm1() {
        return term1;
    }

    public INtsParser getRepBoolOprTerm1() {
        return repBoolOprTerm1;
    }

    public INtsParser getEpsilon() {
        return epsilon;
    }

    public IAbstractNode toAbsSyn(Term1 term1) {
        if (token.hasTerminal(BECOMES)) {
            return term1.toAbsSyn(true);
        }
        if (epsilon == null) {
            return new AbsDyadicExpr(token.getValue(), term1.toAbsSyn(false), repBoolOprTerm1.toAbsSyn(this.term1));
        }
        return term1.toAbsSyn(false);
    }
}
