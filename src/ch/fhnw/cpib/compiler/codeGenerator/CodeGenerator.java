package ch.fhnw.cpib.compiler.codeGenerator;

import ch.fhnw.cpib.compiler.error.CodeGenError;
import ch.fhnw.cpib.compiler.parser.IAbstractTree;
import ch.fhnw.cpib.compiler.vm.CodeArray;
import ch.fhnw.cpib.compiler.vm.ICodeArray;

public class CodeGenerator {

    public static CodeArray codeArray;

    public CodeGenerator() {
        codeArray = new CodeArray(1024);
    }

    public void check(IAbstractTree abstractTree) {
        abstractTree.check();
    }

    public void code(IAbstractTree abstractTree) throws ICodeArray.CodeTooSmallError, CodeGenError {
        abstractTree.code();
    }
}
