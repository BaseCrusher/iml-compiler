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
import static ch.fhnw.cpib.compiler.tokens.enums.AttributeTerminals.MECHMODE;

public class Param implements INtsParser, IToAbsNode {
    private final IToken token;
    private final INtsParser optFlowmode;
    private final INtsParser optMechmode;
    private final INtsParser optChangemode;
    private final INtsParser typedIdent;
    private final String string;

    public Param() throws GrammarError {
        token = Parser.getCurrentToken();
        if (token.hasTerminal(IDENT, CHANGEMODE, MECHMODE, FLOWMODE)) {
            optFlowmode = new OptFlowmode();
            optMechmode = new OptMechmode();
            optChangemode = new OptChangemode();
            typedIdent = new TypedIdent();

            string = optFlowmode.toString() + " " + optMechmode.toString() + " " + optChangemode.toString() + " " + typedIdent.toString();
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

    public INtsParser getOptMechmode() {
        return optMechmode;
    }

    public INtsParser getOptChangemode() {
        return optChangemode;
    }

    public INtsParser getTypedIdent() {
        return typedIdent;
    }

    @Override
    public IAbstractNode toAbsSyn() {
        return null;
    }
}
