package ch.fhnw.cpib.compiler.parser.nts;

import ch.fhnw.cpib.compiler.error.GrammarError;
import ch.fhnw.cpib.compiler.parser.IAbstractNode;
import ch.fhnw.cpib.compiler.parser.INtsParser;
import ch.fhnw.cpib.compiler.parser.IToAbsNodeList;
import ch.fhnw.cpib.compiler.parser.Parser;
import ch.fhnw.cpib.compiler.tokens.IToken;

import java.util.List;

import static ch.fhnw.cpib.compiler.tokens.enums.AttributeTerminals.CHANGEMODE;
import static ch.fhnw.cpib.compiler.tokens.enums.AttributeTerminals.IDENT;

public class CpsStoDecl implements INtsParser, IToAbsNodeList {
    private final IToken token;
    private final INtsParser stoDecl;
    private final INtsParser repSemicolonStoDecl;
    private final String string;

    public CpsStoDecl() throws GrammarError {
        token = Parser.getCurrentToken();
        if (token.hasTerminal(IDENT, CHANGEMODE)) {
            stoDecl = new StoDecl();
            repSemicolonStoDecl = new RepSemicolonDecl();
            string = stoDecl.toString() + " " + repSemicolonStoDecl.toString();
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

    public INtsParser getRepSemicolonStoDecl() {
        return repSemicolonStoDecl;
    }

    @Override
    public List<IAbstractNode> toAbsSyn() {
        return null;
    }
}
