package ch.fhnw.cpib.compiler.parser.nts;

import ch.fhnw.cpib.compiler.error.GrammarError;
import ch.fhnw.cpib.compiler.parser.INtsParser;
import ch.fhnw.cpib.compiler.parser.Parser;
import ch.fhnw.cpib.compiler.tokens.IToken;

import static ch.fhnw.cpib.compiler.tokens.enums.KeywordTerminals.ELSE;
import static ch.fhnw.cpib.compiler.tokens.enums.KeywordTerminals.ENDFUN;
import static ch.fhnw.cpib.compiler.tokens.enums.KeywordTerminals.ENDIF;
import static ch.fhnw.cpib.compiler.tokens.enums.KeywordTerminals.ENDPROC;
import static ch.fhnw.cpib.compiler.tokens.enums.KeywordTerminals.ENDPROGRAM;
import static ch.fhnw.cpib.compiler.tokens.enums.KeywordTerminals.ENDWHILE;
import static ch.fhnw.cpib.compiler.tokens.enums.KeywordTerminals.SEMICOLON;

public class RepSemicolonCmd implements INtsParser {
    private final IToken token;
    private INtsParser cmd;
    private INtsParser repSemicolonCmd;
    private INtsParser epsilon;
    private final String string;

    public RepSemicolonCmd() throws GrammarError {
        token = Parser.consume(SEMICOLON, ENDWHILE, ENDIF, ELSE, ENDPROC, ENDFUN, ENDPROGRAM);
        if (token.hasTerminal(SEMICOLON)) {
            cmd = new Cmd();
            repSemicolonCmd = new RepSemicolonCmd();
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