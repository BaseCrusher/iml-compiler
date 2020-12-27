package ch.fhnw.cpib.compiler.parser.nts;

import ch.fhnw.cpib.compiler.error.GrammarError;
import ch.fhnw.cpib.compiler.parser.IAbstractNode;
import ch.fhnw.cpib.compiler.parser.INtsParser;
import ch.fhnw.cpib.compiler.parser.IToAbsNode;
import ch.fhnw.cpib.compiler.parser.Parser;
import ch.fhnw.cpib.compiler.tokens.IToken;

import static ch.fhnw.cpib.compiler.tokens.enums.AttributeTerminals.CHANGEMODE;
import static ch.fhnw.cpib.compiler.tokens.enums.AttributeTerminals.FLOWMODE;
import static ch.fhnw.cpib.compiler.tokens.enums.AttributeTerminals.IDENT;

public class GlobImp implements INtsParser, IToAbsNode {
    private final IToken token;
    private final INtsParser optFlowmode;
    private final INtsParser optChangemode;
    private final IToken identifier;
    private final String string;

    public GlobImp() throws GrammarError {
        token = Parser.getCurrentToken();
        if (token.hasTerminal(IDENT, CHANGEMODE, FLOWMODE)) {
            optFlowmode = new OptFlowmode();
            optChangemode = new OptChangemode();
            identifier = Parser.consume(IDENT);
            string = optFlowmode.toString() + " " + optChangemode.toString() + " " + identifier.getTerminal().toString();
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

    public INtsParser getOptFlowmode() {
        return optFlowmode;
    }

    public INtsParser getOptChangemode() {
        return optChangemode;
    }

    public IToken getIdentifier() {
        return identifier;
    }

    @Override
    public IAbstractNode toAbsSyn() {
        return null;
    }
}
