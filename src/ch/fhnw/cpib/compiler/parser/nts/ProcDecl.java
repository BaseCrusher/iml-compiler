package ch.fhnw.cpib.compiler.parser.nts;

import ch.fhnw.cpib.compiler.error.GrammarError;
import ch.fhnw.cpib.compiler.parser.INtsParser;
import ch.fhnw.cpib.compiler.parser.Parser;
import ch.fhnw.cpib.compiler.tokens.IToken;

import static ch.fhnw.cpib.compiler.tokens.enums.AttributeTerminals.IDENT;
import static ch.fhnw.cpib.compiler.tokens.enums.KeywordTerminals.DO;
import static ch.fhnw.cpib.compiler.tokens.enums.KeywordTerminals.ENDPROC;
import static ch.fhnw.cpib.compiler.tokens.enums.KeywordTerminals.PROC;

public class ProcDecl implements INtsParser {
    private final IToken token;
    private final IToken identifier;
    private final INtsParser paramList;
    private final INtsParser optGlobalGlobImps;
    private final INtsParser optLocalCpsStoDecl;
    private final INtsParser cpsCmd;
    private final String string;

    public ProcDecl() throws GrammarError {
        token = Parser.getCurrentToken();
        if (token.hasTerminal(PROC)) {
            Parser.consume(PROC);
            identifier = Parser.consume(IDENT);
            paramList = new ParamList();
            optGlobalGlobImps = new OptGlobalGlobImps();
            optLocalCpsStoDecl = new OptLocalCpsStoDecl();
            Parser.consume(DO);
            cpsCmd = new CpsCmd();
            Parser.consume(ENDPROC);
            string = token.getTerminal().toString() + " " + identifier.getTerminal().toString() + " " + paramList.toString() + " " + optGlobalGlobImps.toString() +
                    " " + optLocalCpsStoDecl.toString() + " DO " + cpsCmd.toString() + " ENDPROC";
        } else {
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

    public IToken getIdentifier() {
        return identifier;
    }

    public INtsParser getParamList() {
        return paramList;
    }

    public INtsParser getOptGlobalGlobImps() {
        return optGlobalGlobImps;
    }

    public INtsParser getOptLocalCpsStoDecl() {
        return optLocalCpsStoDecl;
    }

    public INtsParser getCpsCmd() {
        return cpsCmd;
    }
}
