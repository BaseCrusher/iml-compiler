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
    private IToken identifier;
    private INtsParser repCommaIdent;

    public RepCommaIdent() throws GrammarError {
        IToken token = Parser.consume(COMMA, ENDWHILE, ENDIF, ELSE, ENDPROC, ENDFUN, ENDPROGRAM, SEMICOLON);
        if (token.hasTerminal(COMMA)) {
            identifier = Parser.consume(AttributeTerminals.IDENT);
            repCommaIdent = new RepCommaIdent();
        } else if (token.hasTerminal(ENDWHILE) ||
                token.hasTerminal(ENDIF) ||
                token.hasTerminal(ELSE) ||
                token.hasTerminal(ENDPROC) ||
                token.hasTerminal(ENDFUN) ||
                token.hasTerminal(ENDPROGRAM) ||
                token.hasTerminal(SEMICOLON)) {
        }


    }
}
