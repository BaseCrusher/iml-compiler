package ch.fhnw.cpib.compiler.parser;

import ch.fhnw.cpib.compiler.error.CodeGenError;
import ch.fhnw.cpib.compiler.vm.ICodeArray;

public interface IAbstractTree {

    void code() throws ICodeArray.CodeTooSmallError, CodeGenError;

}
