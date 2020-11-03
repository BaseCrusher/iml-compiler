package ch.fhnw.cpib.compiler.parser.nts;

import ch.fhnw.cpib.compiler.error.GrammarError;
import ch.fhnw.cpib.compiler.parser.INtsParser;
import ch.fhnw.cpib.compiler.parser.Parser;
import ch.fhnw.cpib.compiler.tokens.IToken;

import static ch.fhnw.cpib.compiler.tokens.enums.AttributeTerminals.*;
import static ch.fhnw.cpib.compiler.tokens.enums.KeywordTerminals.*;

public class OptGlobInits implements INtsParser {
    private IToken token;
    private INtsParser repCommaIdent;
    private INtsParser epsilon;
    private String string;

    public OptGlobInits() throws GrammarError {
        token = Parser.consume(INIT, ENDWHILE, ENDIF, ELSE, ENDPROC, ENDFUN, ENDPROGRAM, SEMICOLON);
        if (token.hasTerminal(INIT)) {
            Parser.consume(IDENT);
            repCommaIdent = null;
            string = token.getTerminal().toString() + " IDENT " + repCommaIdent.toString();
        }
        else {
            epsilon = new Epsilon();
            string = epsilon.toString();
        }
    }

    @Override
    public String toString() {
        return string;
    }
}
