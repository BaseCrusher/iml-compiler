package ch.fhnw.cpib.compiler.parser.nts;

import ch.fhnw.cpib.compiler.error.GrammarError;
import ch.fhnw.cpib.compiler.parser.ANtsParser;
import ch.fhnw.cpib.compiler.parser.Parser;
import ch.fhnw.cpib.compiler.tokens.IToken;
import ch.fhnw.cpib.compiler.tokens.enums.AttributeTerminals;
import ch.fhnw.cpib.compiler.tokens.enums.KeywordTerminals;

public class Program extends ANtsParser {
    private IToken identifier;
    private ANtsParser programParamList;
    private ANtsParser optGlobalCpsDecl;
    private IToken _do;
    private ANtsParser cpsCmd;
    private IToken endProgram;

    public Program() throws GrammarError {
        super();
    }

    public ANtsParser processNts() throws GrammarError {
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

    public ANtsParser getProgramParamList() {
        return programParamList;
    }

    public void setProgramParamList(ANtsParser programParamList) {
        this.programParamList = programParamList;
    }

    public ANtsParser getOptGlobalCpsDecl() {
        return optGlobalCpsDecl;
    }

    public void setOptGlobalCpsDecl(ANtsParser optGlobalCpsDecl) {
        this.optGlobalCpsDecl = optGlobalCpsDecl;
    }

    public IToken get_do() {
        return _do;
    }

    public void set_do(IToken _do) {
        this._do = _do;
    }

    public ANtsParser getCpsCmd() {
        return cpsCmd;
    }

    public void setCpsCmd(ANtsParser cpsCmd) {
        this.cpsCmd = cpsCmd;
    }

    public IToken getEndProgram() {
        return endProgram;
    }

    public void setEndProgram(IToken endProgram) {
        this.endProgram = endProgram;
    }
}
