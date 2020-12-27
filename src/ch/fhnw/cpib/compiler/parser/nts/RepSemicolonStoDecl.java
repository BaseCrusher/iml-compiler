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
import static ch.fhnw.cpib.compiler.tokens.enums.KeywordTerminals.SEMICOLON;

public class RepSemicolonStoDecl implements INtsParser {
    private final IToken token;
    private StoDecl stoDecl;
    private RepSemicolonStoDecl repSemicolonStoDecl;
    private Epsilon epsilon;
    private final String string;

    public RepSemicolonStoDecl(Environment environment) throws GrammarError {
        token = Parser.getCurrentToken();
        if (token.hasTerminal(SEMICOLON)) {
            Parser.consume(SEMICOLON);
            stoDecl = new StoDecl(environment);
            repSemicolonStoDecl = new RepSemicolonStoDecl(environment); // TODO is this needed??
            string = "; \n " + stoDecl.toString();
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

    public INtsParser getStoDecl() {
        return stoDecl;
    }

    public INtsParser getEpsilon() {
        return epsilon;
    }

    public List<IAbstractNode> toAbsSyn() {
        if (epsilon == null) {
            List<IAbstractNode> stoDeclList = new ArrayList<>();
            stoDeclList.add(stoDecl.toAbsSyn());
            stoDeclList.addAll(repSemicolonStoDecl.toAbsSyn());
            return stoDeclList;
        }
        return new ArrayList<>();
    }
}
