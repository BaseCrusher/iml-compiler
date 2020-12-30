package ch.fhnw.cpib.compiler.parser.nts;

import java.util.List;
import java.util.Map;

import ch.fhnw.cpib.compiler.error.DuplicateDeclaratoinError;
import ch.fhnw.cpib.compiler.error.GrammarError;
import ch.fhnw.cpib.compiler.parser.Environment;
import ch.fhnw.cpib.compiler.parser.IAbstractNode;
import ch.fhnw.cpib.compiler.parser.INtsParser;
import ch.fhnw.cpib.compiler.parser.Parser;
import ch.fhnw.cpib.compiler.parser.Routine;
import ch.fhnw.cpib.compiler.parser.Variable;
import ch.fhnw.cpib.compiler.parser.abstracts.AbsFunDecl;
import ch.fhnw.cpib.compiler.parser.abstracts.AbsParam;
import ch.fhnw.cpib.compiler.tokens.IToken;

import static ch.fhnw.cpib.compiler.tokens.enums.AttributeTerminals.IDENT;
import static ch.fhnw.cpib.compiler.tokens.enums.KeywordTerminals.DO;
import static ch.fhnw.cpib.compiler.tokens.enums.KeywordTerminals.ENDFUN;
import static ch.fhnw.cpib.compiler.tokens.enums.KeywordTerminals.FUN;
import static ch.fhnw.cpib.compiler.tokens.enums.KeywordTerminals.RETURNS;

public class FunDecl implements INtsParser {
    private final IToken token;
    private final Identifier identifier;
    private final ParamList paramList;
    private final StoDecl stoDecl;
    private final OptGlobalGlobImps optGlobalGlobImps;
    private final OptLocalCpsStoDecl optLocalCpsStoDecl;
    private final CpsCmd cpsCmd;
    private final String string;
    private final Environment localEnv;

    public FunDecl(Environment globalEnv) throws GrammarError {
        token = Parser.getCurrentToken();
        if (token.hasTerminal(FUN)) {
            Parser.consume(FUN);
            IToken identifier = Parser.consume(IDENT);
            localEnv = new Environment(globalEnv, globalEnv.getStartAddress() + globalEnv.getVars().size());
            paramList = new ParamList(localEnv);
            Parser.consume(RETURNS);
            stoDecl = new StoDecl(localEnv);
            optGlobalGlobImps = new OptGlobalGlobImps();
            optLocalCpsStoDecl = new OptLocalCpsStoDecl(localEnv);
            try {
                globalEnv.putRoutine(identifier.getValue(), new Routine(identifier.getValue(), localEnv.getVars(), stoDecl.getTypedIdent().getType()));
            } catch (DuplicateDeclaratoinError duplicateDeclaratoinError) {
                duplicateDeclaratoinError.printStackTrace();
            }
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

    public IAbstractNode toAbsSyn() {
        return new AbsFunDecl(identifier, paramList.toAbsSyn(), stoDecl.toAbsSyn(), optGlobalGlobImps.toAbsSyn(), optLocalCpsStoDecl.toAbsSyn(), cpsCmd.toAbsSyn(), localEnv);
    }
}
