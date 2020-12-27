package ch.fhnw.cpib.compiler.parser.abstracts;

import ch.fhnw.cpib.compiler.parser.IAbstractNode;

import java.util.List;

public class AbsProgram implements IAbstractNode {

    private final String value;
    private final IAbstractNode absProgramParamList;
    private final IAbstractNode absOptGlobalCpsDecl;
    private final IAbstractNode absCpsCmd;

    public AbsProgram(String value, List<IAbstractNode> absProgramParamList, List<IAbstractNode> absOptGlobalCpsDecl, List<IAbstractNode> absCpsCmd) {
        this.value = value;
        this.absProgramParamList = absProgramParamList;
        this.absOptGlobalCpsDecl = absOptGlobalCpsDecl;
        this.absCpsCmd = absCpsCmd;
    }
    
}
