package ch.fhnw.cpib.compiler.parser.nts;

import ch.fhnw.cpib.compiler.error.GrammarError;
import ch.fhnw.cpib.compiler.parser.INtsParser;
import ch.fhnw.cpib.compiler.parser.Parser;
import ch.fhnw.cpib.compiler.tokens.IToken;

import static ch.fhnw.cpib.compiler.tokens.enums.AttributeTerminals.*;
import static ch.fhnw.cpib.compiler.tokens.enums.KeywordTerminals.*;

public class RepCommaGlobImp implements INtsParser {
    private IToken token;
    private INtsParser globImps;
    private INtsParser epsilon;
    private String string;

    public RepCommaGlobImp() throws GrammarError {
        token = Parser.consume(COMMA, DO, LOCAL);
        if (token.hasTerminal(COMMA)) {
            globImps = null;
            string = token.getTerminal().toString() + " " + globImps.toString();
        }
        else {
            epsilon = new Epsilon();
            string = epsilon.toString();
        }
    }

    public IToken getToken() {
        return token;
    }

    public INtsParser getGlobImps() {
        return globImps;
    }

    public INtsParser getEpsilon() {
        return epsilon;
    }
}
