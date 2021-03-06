package ch.fhnw.cpib.compiler.parser.nts;

import ch.fhnw.cpib.compiler.error.GrammarError;
import ch.fhnw.cpib.compiler.parser.Environment;
import ch.fhnw.cpib.compiler.parser.IAbstractNode;
import ch.fhnw.cpib.compiler.parser.INtsParser;
import ch.fhnw.cpib.compiler.parser.Parser;
import ch.fhnw.cpib.compiler.tokens.IToken;

import java.util.ArrayList;
import java.util.List;

import static ch.fhnw.cpib.compiler.tokens.enums.AttributeTerminals.CHANGEMODE;
import static ch.fhnw.cpib.compiler.tokens.enums.AttributeTerminals.IDENT;
import static ch.fhnw.cpib.compiler.tokens.enums.KeywordTerminals.FUN;
import static ch.fhnw.cpib.compiler.tokens.enums.KeywordTerminals.PROC;

public class CpsDecl implements INtsParser {
    private final IToken token;
    private final Decl decl;
    private final RepSemicolonDecl repSemicolonDecl;
    private final String string;

    public CpsDecl(Environment globalEnv) throws GrammarError {
        token = Parser.getCurrentToken();
        if (token.hasTerminal(PROC, FUN, IDENT, CHANGEMODE)) {
            decl = new Decl(globalEnv);
            repSemicolonDecl = new RepSemicolonDecl(globalEnv);
            string = decl.toString() + " " + repSemicolonDecl.toString();
        } else {
            throw new GrammarError(token);
        }

    }

    @Override
    public String toString() {
        return string;
    }

    public INtsParser getDecl() {
        return decl;
    }

    public INtsParser getRepSemicolonDecl() {
        return repSemicolonDecl;
    }

    public IToken getToken() {
        return token;
    }

    public List<IAbstractNode> toAbsSyn() {
        List<IAbstractNode> nodeList = new ArrayList<>();
        nodeList.add(decl.toAbsSyn());
        nodeList.addAll(repSemicolonDecl.toAbsSyn());
        return nodeList;
    }
}
