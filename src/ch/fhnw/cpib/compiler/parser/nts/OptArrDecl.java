package ch.fhnw.cpib.compiler.parser.nts;

import ch.fhnw.cpib.compiler.error.GrammarError;
import ch.fhnw.cpib.compiler.parser.INtsParser;
import ch.fhnw.cpib.compiler.parser.Parser;
import ch.fhnw.cpib.compiler.tokens.IToken;

import static ch.fhnw.cpib.compiler.tokens.enums.KeywordTerminals.COMMA;
import static ch.fhnw.cpib.compiler.tokens.enums.KeywordTerminals.DO;
import static ch.fhnw.cpib.compiler.tokens.enums.KeywordTerminals.GLOBAL;
import static ch.fhnw.cpib.compiler.tokens.enums.KeywordTerminals.LOCAL;
import static ch.fhnw.cpib.compiler.tokens.enums.KeywordTerminals.PIPE;
import static ch.fhnw.cpib.compiler.tokens.enums.KeywordTerminals.RPAREN;
import static ch.fhnw.cpib.compiler.tokens.enums.KeywordTerminals.SEMICOLON;

public class OptArrDecl implements INtsParser {
    private IToken token;
    private INtsParser arrayDecl;
    private String string;

    public OptArrDecl() throws GrammarError {
        this.token = Parser.consume(PIPE, LOCAL, GLOBAL, DO, SEMICOLON, RPAREN, COMMA);
        if (token.hasTerminal(PIPE)) {
            arrayDecl = new ArrayDecl(token);
            string = token.getTerminal().toString() + " : " + arrayDecl.toString();
        } else {
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

    public INtsParser getArrayDecl() {
        return arrayDecl;
    }
}