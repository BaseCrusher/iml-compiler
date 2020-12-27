package ch.fhnw.cpib.compiler.parser;

import ch.fhnw.cpib.compiler.error.GrammarError;
import ch.fhnw.cpib.compiler.tokens.enums.types.IType;
import ch.fhnw.cpib.compiler.vm.ICodeArray;

public interface IAbstractNode {

    IType check() throws GrammarError;

    int code(int loc) throws ICodeArray.CodeTooSmallError;

}
