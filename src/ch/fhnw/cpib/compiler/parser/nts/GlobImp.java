package ch.fhnw.cpib.compiler.parser.nts;

import ch.fhnw.cpib.compiler.error.GrammarError;
import ch.fhnw.cpib.compiler.parser.INtsParser;
import ch.fhnw.cpib.compiler.parser.Parser;
import ch.fhnw.cpib.compiler.tokens.IToken;

import static ch.fhnw.cpib.compiler.tokens.enums.AttributeTerminals.CHANGEMODE;
import static ch.fhnw.cpib.compiler.tokens.enums.AttributeTerminals.FLOWMODE;
import static ch.fhnw.cpib.compiler.tokens.enums.AttributeTerminals.IDENT;

public class GlobImp implements INtsParser {
    private final IToken token;
    private INtsParser optFlowmode;
    private INtsParser optChangemode;
    private IToken identifier;
    private final String string;

    public GlobImp() throws GrammarError {
        token = Parser.consume(IDENT, CHANGEMODE, FLOWMODE);
        optFlowmode = new OptFlowmode(token);
        optChangemode = new OptChangemode(this.token);
        identifier = Parser.consume(IDENT);
        string = token.getTerminal().toString() + " " + optFlowmode.toString() + " " + optChangemode.toString() + " " + identifier.getTerminal().toString();
    }

    @Override
    public String toString() {
        return string;
    }

    public IToken getToken() {
        return token;
    }

    public INtsParser getOptFlowmode() {
        return optFlowmode;
    }

    public INtsParser getOptChangemode() {
        return optChangemode;
    }

    public IToken getIdentifier() {
        return identifier;
    }
}
