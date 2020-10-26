package ch.fhnw.cpib.compiler.tokens;

import ch.fhnw.cpib.compiler.tokens.enums.AttributeTerminals;
import ch.fhnw.cpib.compiler.tokens.enums.ITerminal;

public class AttributeToken<T> implements IToken {
    private final T _value;
    private final ITerminal _terminal;
    private final int line;
    private final int column;

    public AttributeToken(AttributeTerminals terminal, T value, int line, int column) {
        _value = value;
        _terminal = terminal;
        this.line = line;
        this.column = column;
    }

    @Override
    public ITerminal getTerminal() {
        return _terminal;
    }

    @Override
    public String toString(){
        String tempValue = _value.toString();
        if (_value instanceof String) {
            tempValue = "\""+_value+"\"";
        }
        return "(" + _terminal.toString() + "; " + tempValue + ")";
    }

    @Override
    public int getLine() {
        return line;
    }

    @Override
    public int getColumn() {
        return column;
    }
}
