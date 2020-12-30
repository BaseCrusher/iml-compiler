package ch.fhnw.cpib.compiler.parser.nts;

import ch.fhnw.cpib.compiler.error.GrammarError;
import ch.fhnw.cpib.compiler.parser.Environment;
import ch.fhnw.cpib.compiler.parser.IAbstractNode;
import ch.fhnw.cpib.compiler.parser.INtsParser;
import ch.fhnw.cpib.compiler.parser.Parser;
import ch.fhnw.cpib.compiler.parser.abstracts.AbsProcDecl;
import ch.fhnw.cpib.compiler.tokens.IToken;

import static ch.fhnw.cpib.compiler.tokens.enums.AttributeTerminals.IDENT;
import static ch.fhnw.cpib.compiler.tokens.enums.KeywordTerminals.DO;
import static ch.fhnw.cpib.compiler.tokens.enums.KeywordTerminals.ENDPROC;
import static ch.fhnw.cpib.compiler.tokens.enums.KeywordTerminals.PROC;

public class ProcDecl implements INtsParser {
    private final IToken token;
    private final Identifier identifier;
    private final ParamList paramList;
    private final OptGlobalGlobImps optGlobalGlobImps;
    private final OptLocalCpsStoDecl optLocalCpsStoDecl;
    private final CpsCmd cpsCmd;
    private final String string;
    private final Environment localEnv;

    public ProcDecl(Environment globalEnv) throws GrammarError {
        token = Parser.getCurrentToken();
        if (token.hasTerminal(PROC)) {
            Parser.consume(PROC);
            IToken identifier = Parser.consume(IDENT);
            localEnv = new Environment(globalEnv, globalEnv.getStartAddress() + globalEnv.getVars().size());
            paramList = new ParamList(localEnv);
            optGlobalGlobImps = new OptGlobalGlobImps();
            optLocalCpsStoDecl = new OptLocalCpsStoDecl(localEnv);
            Parser.consume(DO);
            cpsCmd = new CpsCmd(localEnv);
            Parser.consume(ENDPROC);
            this.identifier = new Identifier(identifier, localEnv);
            string = token.toString() + " " + identifier.toString() + " " + paramList.toString() + "\n" + optGlobalGlobImps.toString() +
                    "\n" + optLocalCpsStoDecl.toString() + "\nDO\n" + cpsCmd.toString() + "\nENDPROC";
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

    public INtsParser getOptGlobalGlobImps() {
        return optGlobalGlobImps;
    }

    public INtsParser getOptLocalCpsStoDecl() {
        return optLocalCpsStoDecl;
    }

    public INtsParser getCpsCmd() {
        return cpsCmd;
    }

    public IAbstractNode toAbsSyn() {
        return new AbsProcDecl(identifier, paramList.toAbsSyn(), optGlobalGlobImps.toAbsSyn(), optLocalCpsStoDecl.toAbsSyn(), cpsCmd.toAbsSyn(), localEnv);
    }
}
