package ch.fhnw.cpib.compiler.tokens.terminals;

import ch.fhnw.cpib.compiler.tokens.AToken;

public class Literal extends AToken {
    private final int _value;

    public Literal(int value) {
        super(AttributeTerminals.LITERAL);
        _value = value;
    }

    public int get_value() {
        return _value;
    }

    @Override
    public String toString(){
        return "(" + super.toString() + ";" + _value + ")";
    }
}
