package ch.fhnw.cpib.compiler.tokens;

import ch.fhnw.cpib.compiler.tokens.terminals.AttributeTerminals;
import ch.fhnw.cpib.compiler.tokens.terminals.ITerminal;

public abstract class AToken implements IToken {
    private final ITerminal terminal;

    public AToken(ITerminal t) {
        terminal = t;
    }

    @Override
    public ITerminal getTerminal() {
        return terminal;
    }

    @Override
    public String toString(){
        return terminal.toString() ;
    }
}
