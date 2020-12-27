package ch.fhnw.cpib.compiler.parser.nts;

import ch.fhnw.cpib.compiler.error.GrammarError;
import ch.fhnw.cpib.compiler.parser.IAbstractNode;
import ch.fhnw.cpib.compiler.parser.INtsParser;
import ch.fhnw.cpib.compiler.parser.IToAbsNodeList;
import ch.fhnw.cpib.compiler.parser.Parser;
import ch.fhnw.cpib.compiler.tokens.IToken;

import java.util.List;

import static ch.fhnw.cpib.compiler.tokens.enums.AttributeTerminals.CHANGEMODE;
import static ch.fhnw.cpib.compiler.tokens.enums.AttributeTerminals.FLOWMODE;
import static ch.fhnw.cpib.compiler.tokens.enums.AttributeTerminals.IDENT;

public class GlobImps implements INtsParser, IToAbsNodeList {
    private final IToken token;
    private final INtsParser globImp;
    private final INtsParser repCommaGlobImp;
    private final String string;

    public GlobImps() throws GrammarError {
        token = Parser.getCurrentToken();
        if (token.hasTerminal(IDENT, CHANGEMODE, FLOWMODE)) {
            globImp = new GlobImp();
            repCommaGlobImp = new RepCommaGlobImp();
            string = globImp.toString() + " " + repCommaGlobImp.toString();
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

    public INtsParser getGlobImp() {
        return globImp;
    }

    public INtsParser getRepCommaGlobImp() {
        return repCommaGlobImp;
    }

    @Override
    public List<IAbstractNode> toAbsSyn() {
        return null;
    }
}
