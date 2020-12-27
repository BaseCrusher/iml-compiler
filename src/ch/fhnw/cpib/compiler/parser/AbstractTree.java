package ch.fhnw.cpib.compiler.parser;


import ch.fhnw.cpib.compiler.vm.ICodeArray;

public class AbstractTree implements IAbstractTree {

    private final IAbstractNode absProgram;

    public AbstractTree(IConcreteTree concreteTree) {
         absProgram = concreteTree.getProgram().toAbsSyn();
    }

    public void code() throws ICodeArray.CodeTooSmallError {
        absProgram.code(0);
    }

}
