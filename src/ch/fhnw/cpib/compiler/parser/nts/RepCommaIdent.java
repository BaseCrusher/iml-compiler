package ch.fhnw.cpib.compiler.parser.nts;

import ch.fhnw.cpib.compiler.error.GrammarError;
import ch.fhnw.cpib.compiler.parser.IAbstractNode;
import ch.fhnw.cpib.compiler.parser.INtsParser;
import ch.fhnw.cpib.compiler.parser.IToAbsNodeList;
import ch.fhnw.cpib.compiler.parser.Parser;
import ch.fhnw.cpib.compiler.tokens.IToken;
import ch.fhnw.cpib.compiler.tokens.enums.AttributeTerminals;

import java.util.List;

import static ch.fhnw.cpib.compiler.tokens.enums.KeywordTerminals.COMMA;
import static ch.fhnw.cpib.compiler.tokens.enums.KeywordTerminals.ELSE;
import static ch.fhnw.cpib.compiler.tokens.enums.KeywordTerminals.ENDFUN;
import static ch.fhnw.cpib.compiler.tokens.enums.KeywordTerminals.ENDIF;
import static ch.fhnw.cpib.compiler.tokens.enums.KeywordTerminals.ENDPROC;
import static ch.fhnw.cpib.compiler.tokens.enums.KeywordTerminals.ENDPROGRAM;
import static ch.fhnw.cpib.compiler.tokens.enums.KeywordTerminals.ENDWHILE;
import static ch.fhnw.cpib.compiler.tokens.enums.KeywordTerminals.SEMICOLON;

public class RepCommaIdent implements INtsParser, IToAbsNodeList {
    private final IToken token;
    private IToken identifier;
    private INtsParser repCommaIdent;
    private INtsParser epsilon;
    private final String string;

    public RepCommaIdent() throws GrammarError {
        token = Parser.getCurrentToken();
        if (token.hasTerminal(COMMA)) {
            Parser.consume(COMMA);
            identifier = Parser.consume(AttributeTerminals.IDENT);
            repCommaIdent = new RepCommaIdent();
            string = ", " + identifier.toString() + " " + repCommaIdent.toString();
        } else if (token.hasTerminal(ENDWHILE, ENDIF, ELSE, ENDPROC, ENDFUN, ENDPROGRAM, SEMICOLON)) {
            epsilon = new Epsilon();
            string = epsilon.toString();
        } else {
            throw new GrammarError(token);
        }
    }

    @Override
    public String toString() {
        return string;
    }

    public IToken getIdentifier() {
        return identifier;
    }

    public INtsParser getRepCommaIdent() {
        return repCommaIdent;
    }

    public INtsParser getEpsilon() {
        return epsilon;
    }

    @Override
    public List<IAbstractNode> toAbsSyn() {
        return null;
    }
}
