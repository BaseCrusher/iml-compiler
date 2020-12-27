package ch.fhnw.cpib.compiler.tokens;

import ch.fhnw.cpib.compiler.tokens.enums.ITerminal;

public interface IToken {
    ITerminal getTerminal();
    String toString();
    int getLine();
    int getColumn();
    String getValue();
    boolean hasTerminal(ITerminal... terminals);
}
