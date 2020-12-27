package ch.fhnw.cpib.compiler.parser.nts;

import ch.fhnw.cpib.compiler.error.GrammarError;
import ch.fhnw.cpib.compiler.parser.Environment;
import ch.fhnw.cpib.compiler.parser.IAbstractNode;
import ch.fhnw.cpib.compiler.parser.INtsParser;
import ch.fhnw.cpib.compiler.parser.IToAbsNodeList;
import ch.fhnw.cpib.compiler.parser.Parser;
import ch.fhnw.cpib.compiler.tokens.enums.KeywordTerminals;

import java.util.List;

public class ProgParamList implements INtsParser, IToAbsNodeList {

    private INtsParser optProgParamRepCommaProgParam;

    public ProgParamList(Environment globalEnv) throws GrammarError {
        Parser.consume(KeywordTerminals.LPAREN);
        optProgParamRepCommaProgParam = new OptProgParamRepCommaProgParam(globalEnv);
        Parser.consume(KeywordTerminals.RPAREN);
    }

    @Override
    public String toString() {
        return "(" + optProgParamRepCommaProgParam.toString() + ")";
    }

    public INtsParser getOptProgParamRepCommaProgParam() {
        return optProgParamRepCommaProgParam;
    }

    @Override
    public List<IAbstractNode> toAbsSyn() {
        return null;
    }
}
