package ch.fhnw.cpib.compiler.parser.nts;

import ch.fhnw.cpib.compiler.error.GrammarError;
import ch.fhnw.cpib.compiler.parser.INtsParser;
import ch.fhnw.cpib.compiler.parser.Parser;
import ch.fhnw.cpib.compiler.tokens.IToken;
import ch.fhnw.cpib.compiler.tokens.enums.AttributeTerminals;

import static ch.fhnw.cpib.compiler.tokens.enums.KeywordTerminals.COMMA;
import static ch.fhnw.cpib.compiler.tokens.enums.KeywordTerminals.ELSE;
import static ch.fhnw.cpib.compiler.tokens.enums.KeywordTerminals.ENDFUN;
import static ch.fhnw.cpib.compiler.tokens.enums.KeywordTerminals.ENDIF;
import static ch.fhnw.cpib.compiler.tokens.enums.KeywordTerminals.ENDPROC;
import static ch.fhnw.cpib.compiler.tokens.enums.KeywordTerminals.ENDPROGRAM;
import static ch.fhnw.cpib.compiler.tokens.enums.KeywordTerminals.ENDWHILE;
import static ch.fhnw.cpib.compiler.tokens.enums.KeywordTerminals.SEMICOLON;

public class RepCommaIdent implements INtsParser {
    private final IToken token;
    private IToken identifier;
    private INtsParser repCommaIdent;
    private INtsParser epsilon;
    private String string;

    public RepCommaIdent() throws GrammarError {
        token = Parser.consume(COMMA, ENDWHILE, ENDIF, ELSE, ENDPROC, ENDFUN, ENDPROGRAM, SEMICOLON);
        if (token.hasTerminal(COMMA)) {
            identifier = Parser.consume(AttributeTerminals.IDENT);
            repCommaIdent = new RepCommaIdent();
            string = token.getTerminal().toString() + " " + identifier + " " + repCommaIdent.toString();
        } else if (token.hasTerminal(ENDWHILE) ||
                token.hasTerminal(ENDIF) ||
                token.hasTerminal(ELSE) ||
                token.hasTerminal(ENDPROC) ||
                token.hasTerminal(ENDFUN) ||
                token.hasTerminal(ENDPROGRAM) ||
                token.hasTerminal(SEMICOLON)) {
            epsilon = new Epsilon();
            string = token.getTerminal().toString() + epsilon.toString();
        }
    }

    @Override
    public String toString() {
        return string;
    }

    public IToken getIdentifier() {
        return identifier;
    }

    public void setIdentifier(IToken identifier) {
        this.identifier = identifier;
    }

    public INtsParser getRepCommaIdent() {
        return repCommaIdent;
    }

    public void setRepCommaIdent(INtsParser repCommaIdent) {
        this.repCommaIdent = repCommaIdent;
    }

    public INtsParser getEpsilon() {
        return epsilon;
    }

    public void setEpsilon(INtsParser epsilon) {
        this.epsilon = epsilon;
    }
}
