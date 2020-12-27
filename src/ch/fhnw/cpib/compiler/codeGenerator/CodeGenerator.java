package ch.fhnw.cpib.compiler.codeGenerator;

import ch.fhnw.cpib.compiler.parser.IAbstractTree;
import ch.fhnw.cpib.compiler.vm.CodeArray;
import ch.fhnw.cpib.compiler.vm.ICodeArray;

public class CodeGenerator {

    public static CodeArray codeArray;

    public CodeGenerator() {
        codeArray = new CodeArray(3);
    }

    public void code(IAbstractTree abstractTree) throws ICodeArray.CodeTooSmallError {
        abstractTree.code();
    }
}
