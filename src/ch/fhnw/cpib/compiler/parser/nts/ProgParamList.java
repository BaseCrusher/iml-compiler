package ch.fhnw.cpib.compiler.parser.nts;

import ch.fhnw.cpib.compiler.error.GrammarError;
import ch.fhnw.cpib.compiler.parser.IAbstractNode;
import ch.fhnw.cpib.compiler.parser.INtsParser;
import ch.fhnw.cpib.compiler.parser.Parser;
import ch.fhnw.cpib.compiler.tokens.enums.KeywordTerminals;

public class ProgParamList implements INtsParser {

    private INtsParser optProgParamRepCommaProgParam;

    public ProgParamList() throws GrammarError {
        Parser.consume(KeywordTerminals.LPAREN);
        optProgParamRepCommaProgParam = new OptProgParamRepCommaProgParam();
        Parser.consume(KeywordTerminals.RPAREN);
    }

    @Override
    public String toString() {
        return "(" + optProgParamRepCommaProgParam.toString() + ")";
    }

    @Override
    public IAbstractNode toAbsSyn() {
        return null;
    }

    public INtsParser getOptProgParamRepCommaProgParam() {
        return optProgParamRepCommaProgParam;
    }
}
