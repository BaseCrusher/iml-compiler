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
        token = Parser.consume(ADDOPR, COMMA, RPAREN, DO, THEN, ENDWHILE, ENDIF, ELSE, ENDPROC, ENDFUN, ENDPROGRAM, SEMICOLON, BECOMES, BOOLOPR, RELOPR);
        if (token.hasTerminal(ADDOPR)) {
            term3 = null;
            repAddOprTerm3 = null;
            string = token.getTerminal().toString() + " " + term3.toString() + " " + repAddOprTerm3.toString();
        } else {
            epsilon = new Epsilon();
            string = token.getTerminal().toString() + " " + epsilon.toString();
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

    public void setTerm3(INtsParser term3) {
        this.term3 = term3;
    }

    public INtsParser getRepAddOprTerm3() {
        return repAddOprTerm3;
    }

    public void setRepAddOprTerm3(INtsParser repAddOprTerm3) {
        this.repAddOprTerm3 = repAddOprTerm3;
    }

    public INtsParser getEpsilon() {
        return epsilon;
    }

    public void setEpsilon(INtsParser epsilon) {
        this.epsilon = epsilon;
    }
}
