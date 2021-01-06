package ch.fhnw.cpib.compiler.parser;

import ch.fhnw.cpib.compiler.error.CodeGenError;
import ch.fhnw.cpib.compiler.error.TypeCheckError;
import ch.fhnw.cpib.compiler.vm.ICodeArray;

public interface IAbstractTree {

    void check() throws TypeCheckError;

    void code() throws ICodeArray.CodeTooSmallError, CodeGenError;

}
