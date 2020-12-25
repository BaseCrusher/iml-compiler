package ch.fhnw.cpib.compiler.parser.abstracts;

import ch.fhnw.cpib.compiler.parser.IAbstractNode;

public class AbsProgram implements IAbstractNode {

    private final String value;
    private final IAbstractNode absProgramParamList;
    private final IAbstractNode absOptGlobalCpsDecl;
    private final IAbstractNode absCpsCmd;

    public AbsProgram(String value, IAbstractNode absProgramParamList, IAbstractNode absOptGlobalCpsDecl, IAbstractNode absCpsCmd) {
        this.value = value;
        this.absProgramParamList = absProgramParamList;
        this.absOptGlobalCpsDecl = absOptGlobalCpsDecl;
        this.absCpsCmd = absCpsCmd;
    }
    
}
