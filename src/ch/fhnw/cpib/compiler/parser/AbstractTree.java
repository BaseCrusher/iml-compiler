package ch.fhnw.cpib.compiler.parser;


import ch.fhnw.cpib.compiler.error.CodeGenError;
import ch.fhnw.cpib.compiler.error.TypeCheckError;
import ch.fhnw.cpib.compiler.vm.ICodeArray;

public class AbstractTree implements IAbstractTree {

    private final IAbstractNode absProgram;

    public AbstractTree(IConcreteTree concreteTree) {
         absProgram = concreteTree.getProgram().toAbsSyn();
    }

    @Override
    public void check() throws TypeCheckError {
        absProgram.check();
    }

    public void code() throws ICodeArray.CodeTooSmallError, CodeGenError {
        absProgram.code(0);
    }

}
