package ch.fhnw.cpib.compiler.parser.nts;

import ch.fhnw.cpib.compiler.error.GrammarError;
import ch.fhnw.cpib.compiler.parser.INtsParser;
import ch.fhnw.cpib.compiler.parser.Parser;
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
import static ch.fhnw.cpib.compiler.tokens.enums.KeywordTerminals.RPAREN;
import static ch.fhnw.cpib.compiler.tokens.enums.KeywordTerminals.SEMICOLON;
import static ch.fhnw.cpib.compiler.tokens.enums.KeywordTerminals.THEN;

public class RepAddOprTerm3 implements INtsParser {
    private final IToken token;
    private INtsParser term3;
    private INtsParser repAddOprTerm3;
    private INtsParser epsilon;
    private String string;

    public RepAddOprTerm3() throws GrammarError {
        token = Parser.getCurrentToken();
        if (token.hasTerminal(ADDOPR)) {
            Parser.consume(ADDOPR);
            term3 = new Term3();
            repAddOprTerm3 = new RepAddOprTerm3();
            string = token.getTerminal().toString() + " " + term3.toString() + " " + repAddOprTerm3.toString();
        } else if (token.hasTerminal(COMMA, RPAREN, DO, THEN, ENDWHILE, ENDIF, ELSE, ENDPROC, ENDFUN, ENDPROGRAM, SEMICOLON, BECOMES, BOOLOPR, RELOPR)) {
            epsilon = new Epsilon();
            string = token.getTerminal().toString() + " " + epsilon.toString();
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
}
