package ch.fhnw.cpib.compiler.parser;

import ch.fhnw.cpib.compiler.error.CodeGenError;
import ch.fhnw.cpib.compiler.error.TypeCheckError;
import ch.fhnw.cpib.compiler.tokens.enums.types.IType;
import ch.fhnw.cpib.compiler.vm.ICodeArray;

public interface IAbstractNode {

    IType check() throws TypeCheckError;

    int code(int loc) throws ICodeArray.CodeTooSmallError, CodeGenError;

}
