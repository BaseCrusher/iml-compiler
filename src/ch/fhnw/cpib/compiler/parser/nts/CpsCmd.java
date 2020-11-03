package ch.fhnw.cpib.compiler.parser.nts;

import ch.fhnw.cpib.compiler.error.GrammarError;
import ch.fhnw.cpib.compiler.parser.INtsParser;
import ch.fhnw.cpib.compiler.parser.Parser;
import ch.fhnw.cpib.compiler.tokens.IToken;

import static ch.fhnw.cpib.compiler.tokens.enums.AttributeTerminals.*;
import static ch.fhnw.cpib.compiler.tokens.enums.KeywordTerminals.*;

public class CpsCmd implements INtsParser {
    private IToken token;
    private INtsParser cmd;
    private INtsParser repSemicolonCmd;
    private String string;

    public CpsCmd() throws GrammarError {
        token = Parser.consume(DEBUGOUT, DEBUGIN, CALL, WHILE, IF, LPAREN, MONOPR, IDENT, LITERAL, SKIP);
        cmd = null;
        repSemicolonCmd = null;
        string = cmd.toString() + repSemicolonCmd.toString();
    }
}
