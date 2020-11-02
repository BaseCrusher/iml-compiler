package ch.fhnw.cpib.compiler.parser.nts;

import ch.fhnw.cpib.compiler.error.GrammarError;
import ch.fhnw.cpib.compiler.parser.INtsParser;
import ch.fhnw.cpib.compiler.parser.Parser;
import ch.fhnw.cpib.compiler.tokens.IToken;
import ch.fhnw.cpib.compiler.tokens.enums.AttributeTerminals;
import ch.fhnw.cpib.compiler.tokens.enums.KeywordTerminals;

public class Program implements INtsParser {
    private IToken identifier;
    private INtsParser programParamList;
    private INtsParser optGlobalCpsDecl;
    private IToken _do;
    private INtsParser cpsCmd;
    private IToken endProgram;

    @Override
    public INtsParser processNts() throws GrammarError {
        Parser.consume(KeywordTerminals.PROGRAM);
        identifier = Parser.consume(AttributeTerminals.IDENT);
        programParamList = null;
        optGlobalCpsDecl = null;
        _do = Parser.consume(KeywordTerminals.DO);
        cpsCmd = null;
        endProgram = Parser.consume(KeywordTerminals.ENDPROGRAM);

        return this;
    }

    @Override
    public String toString() {
        return "PROGRAM " + identifier + " " + programParamList.toString() + " " + optGlobalCpsDecl.toString() + "\nDO\n" + cpsCmd.toString() + "\nENDPROGRAM";
    }

    public IToken getIdentifier() {
        return identifier;
    }

    public void setIdentifier(IToken identifier) {
        this.identifier = identifier;
    }

    public INtsParser getProgramParamList() {
        return programParamList;
    }

    public void setProgramParamList(INtsParser programParamList) {
        this.programParamList = programParamList;
    }

    public INtsParser getOptGlobalCpsDecl() {
        return optGlobalCpsDecl;
    }

    public void setOptGlobalCpsDecl(INtsParser optGlobalCpsDecl) {
        this.optGlobalCpsDecl = optGlobalCpsDecl;
    }

    public IToken get_do() {
        return _do;
    }

    public void set_do(IToken _do) {
        this._do = _do;
    }

    public INtsParser getCpsCmd() {
        return cpsCmd;
    }

    public void setCpsCmd(INtsParser cpsCmd) {
        this.cpsCmd = cpsCmd;
    }

    public IToken getEndProgram() {
        return endProgram;
    }

    public void setEndProgram(IToken endProgram) {
        this.endProgram = endProgram;
    }
}
