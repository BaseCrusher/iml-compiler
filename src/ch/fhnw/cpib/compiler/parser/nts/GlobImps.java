package ch.fhnw.cpib.compiler.parser.nts;

import ch.fhnw.cpib.compiler.error.GrammarError;
import ch.fhnw.cpib.compiler.parser.INtsParser;
import ch.fhnw.cpib.compiler.parser.Parser;
import ch.fhnw.cpib.compiler.tokens.IToken;

import static ch.fhnw.cpib.compiler.tokens.enums.AttributeTerminals.*;
import static ch.fhnw.cpib.compiler.tokens.enums.KeywordTerminals.*;

public class GlobImps implements INtsParser {
    private IToken token;
    private INtsParser globImp;
    private INtsParser repCommaGlobImp;
    private String string;

    public GlobImps() throws GrammarError {
        token = Parser.consume(IDENT, CHANGEMODE, FLOWMODE);
        globImp = null;
        repCommaGlobImp = null;
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
