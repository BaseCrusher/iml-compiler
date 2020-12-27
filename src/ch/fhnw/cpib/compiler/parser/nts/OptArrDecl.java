package ch.fhnw.cpib.compiler.parser.nts;

import ch.fhnw.cpib.compiler.error.GrammarError;
import ch.fhnw.cpib.compiler.parser.IAbstractNode;
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
    private ArrayDecl arrayDecl;
    private String string;
    private Epsilon epsilon;

    public OptArrDecl() throws GrammarError {
        token = Parser.getCurrentToken();
        if (token.hasTerminal(PIPE)) {
            arrayDecl = new ArrayDecl();
            string = arrayDecl.toString();
        } else if (token.hasTerminal(PIPE, LOCAL, GLOBAL, DO, SEMICOLON, RPAREN, COMMA)){
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

    public IToken getToken() {
        return token;
    }

    public INtsParser getArrayDecl() {
        return arrayDecl;
    }

    public IAbstractNode toAbsSyn(IToken type) {
        return arrayDecl.toAbsSyn();
    }

    public Epsilon getEpsilon() {
        return epsilon;
    }
}
