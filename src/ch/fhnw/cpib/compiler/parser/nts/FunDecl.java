package ch.fhnw.cpib.compiler.parser.nts;

import ch.fhnw.cpib.compiler.error.GrammarError;
import ch.fhnw.cpib.compiler.parser.INtsParser;
import ch.fhnw.cpib.compiler.parser.Parser;
import ch.fhnw.cpib.compiler.tokens.IToken;

import static ch.fhnw.cpib.compiler.tokens.enums.AttributeTerminals.*;
import static ch.fhnw.cpib.compiler.tokens.enums.KeywordTerminals.*;

public class FunDecl implements INtsParser {
    private IToken token;
    private IToken identifier;
    private INtsParser paramList;
    private INtsParser stoDecl;
    private INtsParser optGlobalGlobImps;
    private INtsParser optLocalCpsStoDecl;
    private INtsParser cpsCmd;
    private String string;

    public FunDecl() throws GrammarError {
        token = Parser.consume(FUN);
        identifier = Parser.consume(IDENT);
        paramList = null;
        Parser.consume(RETURNS);
        stoDecl = null;
        optGlobalGlobImps = null;
        optLocalCpsStoDecl = null;
        Parser.consume(DO);
        cpsCmd = null;
        Parser.consume(ENDFUN);
        string = token.getTerminal().toString() + " " + identifier.getTerminal().toString() + " " + paramList.toString() + " RETURNS " + stoDecl.toString() +
                " " + optGlobalGlobImps.toString() + " " + optLocalCpsStoDecl.toString() + " DO " + cpsCmd.toString() + " ENDFUN";
    }

    @Override
    public String toString() {
        return string;
    }

    public IToken getToken() {
        return token;
    }

    public void setToken(IToken token) {
        this.token = token;
    }

    public IToken getIdentifier() {
        return identifier;
    }

    public void setIdentifier(IToken identifier) {
        this.identifier = identifier;
    }

    public INtsParser getParamList() {
        return paramList;
    }

    public void setParamList(INtsParser paramList) {
        this.paramList = paramList;
    }

    public INtsParser getStoDecl() {
        return stoDecl;
    }

    public void setStoDecl(INtsParser stoDecl) {
        this.stoDecl = stoDecl;
    }

    public INtsParser getOptGlobalGlobImps() {
        return optGlobalGlobImps;
    }

    public void setOptGlobalGlobImps(INtsParser optGlobalGlobImps) {
        this.optGlobalGlobImps = optGlobalGlobImps;
    }

    public INtsParser getOptLocalCpsStoDecl() {
        return optLocalCpsStoDecl;
    }

    public void setOptLocalCpsStoDecl(INtsParser optLocalCpsStoDecl) {
        this.optLocalCpsStoDecl = optLocalCpsStoDecl;
    }

    public INtsParser getCpsCmd() {
        return cpsCmd;
    }

    public void setCpsCmd(INtsParser cpsCmd) {
        this.cpsCmd = cpsCmd;
    }
}
