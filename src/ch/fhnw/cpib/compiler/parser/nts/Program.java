package ch.fhnw.cpib.compiler.parser.nts;

import java.util.HashMap;

import ch.fhnw.cpib.compiler.error.GrammarError;
import ch.fhnw.cpib.compiler.parser.*;
import ch.fhnw.cpib.compiler.parser.abstracts.AbsProgram;
import ch.fhnw.cpib.compiler.tokens.IToken;
import ch.fhnw.cpib.compiler.tokens.enums.AttributeTerminals;
import ch.fhnw.cpib.compiler.tokens.enums.KeywordTerminals;

import static ch.fhnw.cpib.compiler.tokens.enums.KeywordTerminals.PROGRAM;

public class Program implements INtsParser, IToAbsNode {
    private Identifier identifier;
    private INtsParser programParamList;
    private INtsParser optGlobalCpsDecl;
    private IToken _do;
    private INtsParser cpsCmd;
    private IToken endProgram;
    private Environment globalEnv;

    public Program() throws GrammarError {
        Parser.consume(PROGRAM);
        globalEnv = new Environment(null, 0);
        IToken identifier = Parser.consume(AttributeTerminals.IDENT);
        this.identifier = new Identifier(identifier, globalEnv);
        programParamList = new ProgParamList(globalEnv);
        optGlobalCpsDecl = new OptGlobalCpsDecl(globalEnv);
        _do = Parser.consume(KeywordTerminals.DO);
        cpsCmd = new CpsCmd(globalEnv);
        endProgram = Parser.consume(KeywordTerminals.ENDPROGRAM);
    }

    @Override
    public String toString() {
        return "PROGRAM " + identifier + " " + programParamList.toString() + "\n" + optGlobalCpsDecl.toString() + "\nDO\n" + cpsCmd.toString() + "\nENDPROGRAM";
    }

    @Override
    public IAbstractNode toAbsSyn() {
        return new AbsProgram(identifier.ident.getValue(), ((IToAbsNodeList)programParamList).toAbsSyn(), ((IToAbsNodeList)optGlobalCpsDecl).toAbsSyn(), ((IToAbsNodeList)cpsCmd).toAbsSyn());
    }

    public IToken getIdentifier() {
        return identifier.ident;
    }

    public INtsParser getProgramParamList() {
        return programParamList;
    }

    public INtsParser getOptGlobalCpsDecl() {
        return optGlobalCpsDecl;
    }

    public IToken get_do() {
        return _do;
    }

    public INtsParser getCpsCmd() {
        return cpsCmd;
    }

    public IToken getEndProgram() {
        return endProgram;
    }
}
