package ch.fhnw.cpib.compiler.parser.nts;

import ch.fhnw.cpib.compiler.error.GrammarError;
import ch.fhnw.cpib.compiler.parser.INtsParser;
import ch.fhnw.cpib.compiler.parser.Parser;
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
import static ch.fhnw.cpib.compiler.tokens.enums.KeywordTerminals.RPAREN;
import static ch.fhnw.cpib.compiler.tokens.enums.KeywordTerminals.SEMICOLON;
import static ch.fhnw.cpib.compiler.tokens.enums.KeywordTerminals.THEN;

public class RepBoolOprTerm1 implements INtsParser {
    private final IToken token;
    private INtsParser term1;
    private INtsParser repBoolOprTerm1;
    private INtsParser epsilon;
    private final String string;

    public RepBoolOprTerm1() throws GrammarError {
        token = Parser.getCurrentToken();
        if (token.hasTerminal(BOOLOPR)) {
            Parser.consume(BOOLOPR);
            term1 = new Term1();
            repBoolOprTerm1 = new RepBoolOprTerm1();
            string = token.toString() + " " + term1.toString() + " " + repBoolOprTerm1.toString();
        }
        else if (token.hasTerminal(COMMA, RPAREN, DO, THEN, ENDWHILE, ENDIF, ENDIF, ELSE, ENDPROC, ENDFUN, ENDPROGRAM, SEMICOLON, BECOMES)) {
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
}
