package ch.fhnw.cpib.compiler.parser.nts;

import ch.fhnw.cpib.compiler.error.GrammarError;
import ch.fhnw.cpib.compiler.parser.INtsParser;
import ch.fhnw.cpib.compiler.parser.Parser;
import ch.fhnw.cpib.compiler.tokens.IToken;

import static ch.fhnw.cpib.compiler.tokens.enums.AttributeTerminals.*;

public class ProgParam implements INtsParser {
    private IToken token;
    private INtsParser optFlowmode;
    private INtsParser optChangemode;
    private INtsParser typedIdent;

    public ProgParam() throws GrammarError {
        token = Parser.consume(IDENT, CHANGEMODE, FLOWMODE);
        optFlowmode = null;
        optChangemode = null;
        typedIdent = null;
    }

    public String toString() {
        return token.getTerminal().toString() + " " + optFlowmode.toString() + " " + optChangemode.toString() + " " + typedIdent.toString();
    }
}
