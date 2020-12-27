package ch.fhnw.cpib.compiler.parser.nts;

import ch.fhnw.cpib.compiler.error.GrammarError;
import ch.fhnw.cpib.compiler.parser.Environment;
import ch.fhnw.cpib.compiler.parser.IAbstractNode;
import ch.fhnw.cpib.compiler.parser.INtsParser;
import ch.fhnw.cpib.compiler.parser.IToAbsNodeList;
import ch.fhnw.cpib.compiler.parser.Parser;
import ch.fhnw.cpib.compiler.tokens.IToken;

import java.util.List;

import static ch.fhnw.cpib.compiler.tokens.enums.KeywordTerminals.DO;
import static ch.fhnw.cpib.compiler.tokens.enums.KeywordTerminals.LOCAL;

public class OptLocalCpsStoDecl implements INtsParser, IToAbsNodeList {
    private final IToken token;
    private INtsParser cpsStoDecl;
    private INtsParser epsilon;
    private final String string;

    public OptLocalCpsStoDecl(Environment localEnv) throws GrammarError {
        token = Parser.getCurrentToken();
        if(token.hasTerminal(LOCAL)) {
            Parser.consume(LOCAL);
            cpsStoDecl = new CpsStoDecl();
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

    @Override
    public List<IAbstractNode> toAbsSyn() {
        return null;
    }
}
