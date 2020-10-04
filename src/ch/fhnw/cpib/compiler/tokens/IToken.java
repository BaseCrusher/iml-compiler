package ch.fhnw.cpib.compiler.tokens;

import ch.fhnw.cpib.compiler.tokens.terminals.AttributeTerminals;
import ch.fhnw.cpib.compiler.tokens.terminals.ITerminal;

public interface IToken {
    ITerminal getTerminal();
    public String toString();
}
