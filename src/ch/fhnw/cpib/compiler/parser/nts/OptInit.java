package ch.fhnw.cpib.compiler.parser.nts;

import ch.fhnw.cpib.compiler.error.GrammarError;
import ch.fhnw.cpib.compiler.parser.IAbstractNode;
import ch.fhnw.cpib.compiler.parser.INtsParser;
import ch.fhnw.cpib.compiler.parser.IToAbsNode;
import ch.fhnw.cpib.compiler.parser.Parser;
import ch.fhnw.cpib.compiler.tokens.IToken;

import static ch.fhnw.cpib.compiler.tokens.enums.AttributeTerminals.ADDOPR;
import static ch.fhnw.cpib.compiler.tokens.enums.AttributeTerminals.BOOLOPR;
import static ch.fhnw.cpib.compiler.tokens.enums.AttributeTerminals.MULTOPR;
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
import static ch.fhnw.cpib.compiler.tokens.enums.KeywordTerminals.INIT;
import static ch.fhnw.cpib.compiler.tokens.enums.KeywordTerminals.RBRACK;
import static ch.fhnw.cpib.compiler.tokens.enums.KeywordTerminals.RPAREN;
import static ch.fhnw.cpib.compiler.tokens.enums.KeywordTerminals.SEMICOLON;
import static ch.fhnw.cpib.compiler.tokens.enums.KeywordTerminals.THEN;

public class OptInit implements INtsParser, IToAbsNode {
    private final IToken token;
    private String string;

    public OptInit() throws GrammarError {
        token = Parser.getCurrentToken();
        if (token.hasTerminal(INIT)) {
            Parser.consume(INIT);
            string = token.toString();
        } else if (token.hasTerminal(RELOPR, ADDOPR, COMMA, RBRACK, RPAREN, DO, THEN, ENDWHILE, ENDIF, ELSE, ENDPROC, ENDFUN, ENDPROGRAM, SEMICOLON, BECOMES, BOOLOPR, MULTOPR)) {
            Epsilon epsilon = new Epsilon();
            string = epsilon.toString();
        }
    }

    @Override
    public String toString() {
        return string;
    }

    public IToken getToken() {
        return token;
    }

    @Override
    public IAbstractNode toAbsSyn() {
        return null;
    }
}
