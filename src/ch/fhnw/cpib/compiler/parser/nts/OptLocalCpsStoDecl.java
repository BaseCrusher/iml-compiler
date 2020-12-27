package ch.fhnw.cpib.compiler.parser.nts;

import ch.fhnw.cpib.compiler.error.GrammarError;
import ch.fhnw.cpib.compiler.parser.Environment;
import ch.fhnw.cpib.compiler.parser.IAbstractNode;
import ch.fhnw.cpib.compiler.parser.INtsParser;
import ch.fhnw.cpib.compiler.parser.Parser;
import ch.fhnw.cpib.compiler.tokens.IToken;

import java.util.ArrayList;
import java.util.List;

import static ch.fhnw.cpib.compiler.tokens.enums.KeywordTerminals.DO;
import static ch.fhnw.cpib.compiler.tokens.enums.KeywordTerminals.LOCAL;

public class OptLocalCpsStoDecl implements INtsParser {
    private final IToken token;
    private CpsStoDecl cpsStoDecl;
    private Epsilon epsilon;
    private final String string;

    public OptLocalCpsStoDecl(Environment environment) throws GrammarError {
        token = Parser.getCurrentToken();
        if(token.hasTerminal(LOCAL)) {
            Parser.consume(LOCAL);
            cpsStoDecl = new CpsStoDecl(environment);
            string = token.toString() + " " + cpsStoDecl.toString();
        }
        else if (token.hasTerminal(DO)) {
            epsilon = new Epsilon();
            string = epsilon.toString();
        }
        else {
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

    public INtsParser getCpsStoDecl() {
        return cpsStoDecl;
    }

    public INtsParser getEpsilon() {
        return epsilon;
    }

    public List<IAbstractNode> toAbsSyn() {
        if (epsilon == null) {
            return cpsStoDecl.toAbsSyn();
        }
        return new ArrayList<>();
    }
}
