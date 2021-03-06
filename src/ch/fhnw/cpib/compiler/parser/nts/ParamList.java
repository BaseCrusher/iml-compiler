package ch.fhnw.cpib.compiler.parser.nts;

import ch.fhnw.cpib.compiler.error.GrammarError;
import ch.fhnw.cpib.compiler.parser.Environment;
import ch.fhnw.cpib.compiler.parser.IAbstractNode;
import ch.fhnw.cpib.compiler.parser.INtsParser;
import ch.fhnw.cpib.compiler.parser.Parser;
import ch.fhnw.cpib.compiler.tokens.IToken;

import java.util.List;

import static ch.fhnw.cpib.compiler.tokens.enums.KeywordTerminals.LPAREN;
import static ch.fhnw.cpib.compiler.tokens.enums.KeywordTerminals.RPAREN;

public class ParamList implements INtsParser {
    private final IToken token;
    private final OptParamRepCommaParam optParamRepCommaParam;
    private final String string;

    public ParamList(Environment environment) throws GrammarError {
        token = Parser.getCurrentToken();
        if (token.hasTerminal(LPAREN)) {
            Parser.consume(LPAREN);
            optParamRepCommaParam = new OptParamRepCommaParam(environment);
            Parser.consume(RPAREN);
            string = "( " + optParamRepCommaParam.toString() + " )";
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

    public INtsParser getOptParamRepCommaParam() {
        return optParamRepCommaParam;
    }

    public List<IAbstractNode> toAbsSyn() {
        return optParamRepCommaParam.toAbsSyn();
    }
}
