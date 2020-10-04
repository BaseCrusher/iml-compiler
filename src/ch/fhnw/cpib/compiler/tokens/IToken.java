package ch.fhnw.cpib.compiler.tokens;

import ch.fhnw.cpib.compiler.tokens.enums.ITerminal;

public interface IToken {
    ITerminal getTerminal();
    String toString();
}
