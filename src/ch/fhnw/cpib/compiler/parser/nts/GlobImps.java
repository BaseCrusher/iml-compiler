package ch.fhnw.cpib.compiler.parser.nts;

import ch.fhnw.cpib.compiler.error.GrammarError;
import ch.fhnw.cpib.compiler.parser.INtsParser;
import ch.fhnw.cpib.compiler.parser.Parser;
import ch.fhnw.cpib.compiler.tokens.IToken;

import static ch.fhnw.cpib.compiler.tokens.enums.AttributeTerminals.CHANGEMODE;
import static ch.fhnw.cpib.compiler.tokens.enums.AttributeTerminals.FLOWMODE;
import static ch.fhnw.cpib.compiler.tokens.enums.AttributeTerminals.IDENT;

public class GlobImps implements INtsParser {
    private final IToken token;
    private INtsParser globImp;
    private INtsParser repCommaGlobImp;
    private final String string;

    public GlobImps() throws GrammarError {
        token = Parser.consume(IDENT, CHANGEMODE, FLOWMODE);
        globImp = new GlobImp();
        repCommaGlobImp = new RepCommaGlobImp();
        string = token.getTerminal().toString() + " " + globImp.toString() + " " + repCommaGlobImp.toString();
    }

    @Override
    public String toString() {
        return string;
    }

    public IToken getToken() {
        return token;
    }

    public INtsParser getGlobImp() {
        return globImp;
    }

    public INtsParser getRepCommaGlobImp() {
        return repCommaGlobImp;
    }
}
