package ch.fhnw.cpib.compiler.tokens;

import ch.fhnw.cpib.compiler.tokens.enums.ITerminal;

public interface IToken {
    ITerminal getTerminal();
    String toString();
    int getLine();
    int getColumn();
    boolean hasTerminal(ITerminal... terminals);
}
