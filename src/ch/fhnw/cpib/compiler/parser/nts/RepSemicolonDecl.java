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
import static ch.fhnw.cpib.compiler.tokens.enums.KeywordTerminals.SEMICOLON;

public class RepSemicolonDecl implements INtsParser, IToAbsNodeList {
    private final IToken token;
    private INtsParser decl;
    private INtsParser repSemicolonDecl;
    private INtsParser epsilon;
    private final String string;

    public RepSemicolonDecl(Environment environment) throws GrammarError {
        token = Parser.getCurrentToken();
        if (token.hasTerminal(SEMICOLON)) {
            Parser.consume(SEMICOLON);
            decl = new Decl(environment);
            repSemicolonDecl = new RepSemicolonDecl(environment);
            string = "; \n " + decl.toString() + " " + repSemicolonDecl.toString();
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

    public INtsParser getDecl() {
        return decl;
    }

    public INtsParser getRepSemicolonDecl() {
        return repSemicolonDecl;
    }

    public INtsParser getEpsilon() {
        return epsilon;
    }

    @Override
    public List<IAbstractNode> toAbsSyn() {
        return null;
    }
}
