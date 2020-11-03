package ch.fhnw.cpib.compiler.parser.nts;

import ch.fhnw.cpib.compiler.error.GrammarError;
import ch.fhnw.cpib.compiler.parser.INtsParser;
import ch.fhnw.cpib.compiler.parser.Parser;
import ch.fhnw.cpib.compiler.tokens.IToken;

import static ch.fhnw.cpib.compiler.tokens.enums.AttributeTerminals.*;
import static ch.fhnw.cpib.compiler.tokens.enums.KeywordTerminals.*;

public class ProcDecl implements INtsParser {
    private IToken token;
    private IToken identifier;
    private INtsParser paramList;
    private INtsParser optGlobalGlobImps;
    private INtsParser optLocalCpsStoDecl;
    private INtsParser cpsCmd;
    private String string;

    public ProcDecl() throws GrammarError {
        token = Parser.consume(PROC);
        identifier = Parser.consume(IDENT);
        paramList = null;
        optGlobalGlobImps = null;
        optLocalCpsStoDecl = null;
        Parser.consume(DO);
        cpsCmd = null;
        Parser.consume(ENDPROC);
        string = token.getTerminal().toString() + " " + identifier.getTerminal().toString() + " " + paramList.toString() + " " + optGlobalGlobImps.toString() +
                " " + optLocalCpsStoDecl.toString() + " DO " + cpsCmd.toString() + " ENDPROC";
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
