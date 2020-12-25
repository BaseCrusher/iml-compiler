package ch.fhnw.cpib.compiler.parser.nts;

import ch.fhnw.cpib.compiler.error.GrammarError;
import ch.fhnw.cpib.compiler.parser.IAbstractNode;
import ch.fhnw.cpib.compiler.parser.INtsParser;
import ch.fhnw.cpib.compiler.parser.Parser;
import ch.fhnw.cpib.compiler.parser.abstracts.AbsProgram;
import ch.fhnw.cpib.compiler.tokens.AttributeToken;
import ch.fhnw.cpib.compiler.tokens.IToken;
import ch.fhnw.cpib.compiler.tokens.enums.AttributeTerminals;
import ch.fhnw.cpib.compiler.tokens.enums.KeywordTerminals;

import static ch.fhnw.cpib.compiler.tokens.enums.KeywordTerminals.PROGRAM;

public class Program implements INtsParser {
    private IToken identifier;
    private INtsParser programParamList;
    private INtsParser optGlobalCpsDecl;
    private IToken _do;
    private INtsParser cpsCmd;
    private IToken endProgram;

    public Program() throws GrammarError {
        Parser.consume(PROGRAM);
        identifier = Parser.consume(AttributeTerminals.IDENT);
        programParamList = new ProgParamList();
        optGlobalCpsDecl = new OptGlobalCpsDecl();
        _do = Parser.consume(KeywordTerminals.DO);
        cpsCmd = new CpsCmd();
        endProgram = Parser.consume(KeywordTerminals.ENDPROGRAM);
    }

    @Override
    public String toString() {
        return "PROGRAM " + identifier + " " + programParamList.toString() + "\n" + optGlobalCpsDecl.toString() + "\nDO\n" + cpsCmd.toString() + "\nENDPROGRAM";
    }

    @Override
    public IAbstractNode toAbsSyn() {
        return new AbsProgram(identifier.getValue(), programParamList.toAbsSyn(), optGlobalCpsDecl.toAbsSyn(), cpsCmd.toAbsSyn());
    }

    public IToken getIdentifier() {
        return identifier;
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
