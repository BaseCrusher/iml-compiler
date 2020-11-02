package ch.fhnw.cpib.compiler.parser.nts;

import ch.fhnw.cpib.compiler.error.GrammarError;
import ch.fhnw.cpib.compiler.parser.INtsParser;
import ch.fhnw.cpib.compiler.parser.Parser;
import ch.fhnw.cpib.compiler.tokens.IToken;
import ch.fhnw.cpib.compiler.tokens.enums.KeywordTerminals;


import static ch.fhnw.cpib.compiler.tokens.enums.AttributeTerminals.*;
import static ch.fhnw.cpib.compiler.tokens.enums.KeywordTerminals.*;

public class OptProgParamRepCommaProgParam implements INtsParser {
    private IToken identifier;
    private INtsParser progParam;
    private INtsParser repCommaProgParam;


    public OptProgParamRepCommaProgParam() throws GrammarError {
        IToken token = Parser.consume(IDENT, CHANGEMODE, FLOWMODE, RPAREN);
        if (token.getTerminal().)
        identifier = Parser.consume(IDENT);
        progParam = null;
        repCommaProgParam = null;
    }
}
