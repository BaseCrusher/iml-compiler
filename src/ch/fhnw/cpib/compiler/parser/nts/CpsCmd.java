package ch.fhnw.cpib.compiler.parser.nts;

import ch.fhnw.cpib.compiler.error.GrammarError;
import ch.fhnw.cpib.compiler.parser.Environment;
import ch.fhnw.cpib.compiler.parser.IAbstractNode;
import ch.fhnw.cpib.compiler.parser.INtsParser;
import ch.fhnw.cpib.compiler.parser.Parser;
import ch.fhnw.cpib.compiler.tokens.IToken;

import java.util.ArrayList;
import java.util.List;

import static ch.fhnw.cpib.compiler.tokens.enums.AttributeTerminals.IDENT;
import static ch.fhnw.cpib.compiler.tokens.enums.AttributeTerminals.LITERAL;
import static ch.fhnw.cpib.compiler.tokens.enums.AttributeTerminals.MONOPR;
import static ch.fhnw.cpib.compiler.tokens.enums.KeywordTerminals.ARRLEN;
import static ch.fhnw.cpib.compiler.tokens.enums.KeywordTerminals.CALL;
import static ch.fhnw.cpib.compiler.tokens.enums.KeywordTerminals.DEBUGIN;
import static ch.fhnw.cpib.compiler.tokens.enums.KeywordTerminals.DEBUGOUT;
import static ch.fhnw.cpib.compiler.tokens.enums.KeywordTerminals.IF;
import static ch.fhnw.cpib.compiler.tokens.enums.KeywordTerminals.LPAREN;
import static ch.fhnw.cpib.compiler.tokens.enums.KeywordTerminals.SKIP;
import static ch.fhnw.cpib.compiler.tokens.enums.KeywordTerminals.WHILE;

public class CpsCmd implements INtsParser {
    private final IToken token;
    private final Cmd cmd;
    private final RepSemicolonCmd repSemicolonCmd;
    private final String string;

    public CpsCmd(Environment environment) throws GrammarError {
        token = Parser.getCurrentToken();
        if (token.hasTerminal(ARRLEN, DEBUGOUT, DEBUGIN, CALL, WHILE, IF, LPAREN, MONOPR, IDENT, LITERAL, SKIP)) {
            cmd = new Cmd(environment);
            repSemicolonCmd = new RepSemicolonCmd(environment);
            string = cmd.toString() + repSemicolonCmd.toString();
        }
        else {
            throw new GrammarError(token);
        }
    }

    @Override
    public String toString() {
        return string;
    }

    public INtsParser getCmd() {
        return cmd;
    }

    public INtsParser getRepSemicolonCmd() {
        return repSemicolonCmd;
    }

    public IToken getToken() {
        return token;
    }

    public List<IAbstractNode> toAbsSyn() {
        List<IAbstractNode> cpsCmdList = new ArrayList<>();
        cpsCmdList.add(cmd.toAbsSyn());
        cpsCmdList.addAll(repSemicolonCmd.toAbsSyn());
        return cpsCmdList;
    }
}
