package ch.fhnw.cpib.compiler.tokens;

import ch.fhnw.cpib.compiler.tokens.enums.AttributeTerminals;
import ch.fhnw.cpib.compiler.tokens.enums.ITerminal;

public class AttributeToken<T> implements IToken {
    private final T _value;
    private final ITerminal _terminal;

    public AttributeToken(AttributeTerminals terminal, T value) {
        _value = value;
        _terminal = terminal;
    }

    @Override
    public ITerminal getTerminal() {
        return _terminal;
    }

    @Override
    public String toString(){
        return "(" + _terminal.toString() + ";" + _value + ")";
    }
}
