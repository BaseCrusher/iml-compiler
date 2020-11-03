package ch.fhnw.cpib.compiler.parser.nts;

import ch.fhnw.cpib.compiler.error.GrammarError;
import ch.fhnw.cpib.compiler.parser.INtsParser;
import ch.fhnw.cpib.compiler.parser.Parser;
import ch.fhnw.cpib.compiler.tokens.IToken;

import static ch.fhnw.cpib.compiler.tokens.enums.AttributeTerminals.*;
import static ch.fhnw.cpib.compiler.tokens.enums.KeywordTerminals.*;

public class RepSemicolonCmd implements INtsParser {
    private IToken token;
    private INtsParser cmd;
    private INtsParser repSemicolonCmd;
    private INtsParser epsilon;
    private String string;

    public RepSemicolonCmd() throws GrammarError {
        token = Parser.consume(SEMICOLON, ENDWHILE, ENDIF, ELSE, ENDPROC, ENDFUN, ENDPROGRAM);
        if (token.hasTerminal(SEMICOLON)) {
            cmd = null;
            repSemicolonCmd = null;
            string = token.getTerminal().toString() + " " + cmd.toString() + " " + repSemicolonCmd.toString();
        }
        else {
            epsilon = new Epsilon();
            string = epsilon.toString();
        }
    }

    @Override
    public String toString() {
        return string;
    }

    public IToken getToken() {
        return token;
    }

    public INtsParser getCmd() {
        return cmd;
    }

    public INtsParser getRepSemicolonCmd() {
        return repSemicolonCmd;
    }

    public INtsParser getEpsilon() {
        return epsilon;
    }
}
