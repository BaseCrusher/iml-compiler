package ch.fhnw.cpib.compiler.parser.nts;

import java.util.HashMap;

import ch.fhnw.cpib.compiler.error.GrammarError;
import ch.fhnw.cpib.compiler.parser.Environment;
import ch.fhnw.cpib.compiler.parser.IAbstractNode;
import ch.fhnw.cpib.compiler.parser.INtsParser;
import ch.fhnw.cpib.compiler.parser.IToAbsNode;
import ch.fhnw.cpib.compiler.parser.Parser;
import ch.fhnw.cpib.compiler.tokens.IToken;

import static ch.fhnw.cpib.compiler.tokens.enums.AttributeTerminals.IDENT;
import static ch.fhnw.cpib.compiler.tokens.enums.KeywordTerminals.DO;
import static ch.fhnw.cpib.compiler.tokens.enums.KeywordTerminals.ENDFUN;
import static ch.fhnw.cpib.compiler.tokens.enums.KeywordTerminals.FUN;
import static ch.fhnw.cpib.compiler.tokens.enums.KeywordTerminals.RETURNS;

public class FunDecl implements INtsParser, IToAbsNode {
    private final IToken token;
    private final Identifier identifier;
    private final INtsParser paramList;
    private final INtsParser stoDecl;
    private final INtsParser optGlobalGlobImps;
    private final INtsParser optLocalCpsStoDecl;
    private final INtsParser cpsCmd;
    private final String string;

    public FunDecl(Environment globalEnv) throws GrammarError {
        token = Parser.getCurrentToken();
        if (token.hasTerminal(FUN)) {
            Parser.consume(FUN);
            IToken identifier = Parser.consume(IDENT);
            Environment localEnv = new Environment(globalEnv, globalEnv.getStartAddress() + globalEnv.getVars().size());
            paramList = new ParamList(localEnv);
            Parser.consume(RETURNS);
            stoDecl = new StoDecl(localEnv);
            optGlobalGlobImps = new OptGlobalGlobImps();
            optLocalCpsStoDecl = new OptLocalCpsStoDecl(localEnv);
            Parser.consume(DO);
            cpsCmd = new CpsCmd(localEnv);
            Parser.consume(ENDFUN);
            this.identifier = new Identifier(identifier, localEnv);
            string = token.getTerminal().toString() + " " + identifier.toString() + " " + paramList.toString() + " RETURNS " + stoDecl.toString() +
                    " " + optGlobalGlobImps.toString() + " " + optLocalCpsStoDecl.toString() + " DO " + cpsCmd.toString() + " ENDFUN";
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
        return identifier.ident;
    }

    public INtsParser getParamList() {
        return paramList;
    }

    public INtsParser getStoDecl() {
        return stoDecl;
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

    @Override
    public IAbstractNode toAbsSyn() {
        return null;
    }
}
