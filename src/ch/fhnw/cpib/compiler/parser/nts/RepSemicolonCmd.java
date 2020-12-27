package ch.fhnw.cpib.compiler.parser.nts;

import ch.fhnw.cpib.compiler.error.GrammarError;
import ch.fhnw.cpib.compiler.parser.IAbstractNode;
import ch.fhnw.cpib.compiler.parser.INtsParser;
import ch.fhnw.cpib.compiler.parser.IToAbsNode;
import ch.fhnw.cpib.compiler.parser.IToAbsNodeList;
import ch.fhnw.cpib.compiler.parser.Parser;
import ch.fhnw.cpib.compiler.tokens.IToken;

import java.util.ArrayList;
import java.util.List;

import static ch.fhnw.cpib.compiler.tokens.enums.KeywordTerminals.ELSE;
import static ch.fhnw.cpib.compiler.tokens.enums.KeywordTerminals.ENDFUN;
import static ch.fhnw.cpib.compiler.tokens.enums.KeywordTerminals.ENDIF;
import static ch.fhnw.cpib.compiler.tokens.enums.KeywordTerminals.ENDPROC;
import static ch.fhnw.cpib.compiler.tokens.enums.KeywordTerminals.ENDPROGRAM;
import static ch.fhnw.cpib.compiler.tokens.enums.KeywordTerminals.ENDWHILE;
import static ch.fhnw.cpib.compiler.tokens.enums.KeywordTerminals.SEMICOLON;

public class RepSemicolonCmd implements INtsParser, IToAbsNodeList {
    private final IToken token;
    private INtsParser cmd;
    private INtsParser repSemicolonCmd;
    private INtsParser epsilon;
    private final String string;

    public RepSemicolonCmd() throws GrammarError {
        token = Parser.getCurrentToken();
        if (token.hasTerminal(SEMICOLON)) {
            Parser.consume(SEMICOLON);
            cmd = new Cmd();
            repSemicolonCmd = new RepSemicolonCmd();
            string = "; \n " + cmd.toString() + " " + repSemicolonCmd.toString();
        }
        else if (token.hasTerminal(ENDWHILE, ENDIF, ELSE, ENDPROC, ENDFUN, ENDPROGRAM)) {
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

    public INtsParser getCmd() {
        return cmd;
    }

    public INtsParser getRepSemicolonCmd() {
        return repSemicolonCmd;
    }

    public INtsParser getEpsilon() {
        return epsilon;
    }

    @Override
    public List<IAbstractNode> toAbsSyn() {
        if (epsilon != null) {
            return new ArrayList<>();
        }
        List<IAbstractNode> nodeList = new ArrayList<>();
        nodeList.add(((IToAbsNode)cmd).toAbsSyn());
        nodeList.addAll(((IToAbsNodeList)repSemicolonCmd).toAbsSyn());
        return nodeList;
    }
}
