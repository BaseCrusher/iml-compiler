package ch.fhnw.cpib.compiler.tokens;

public class Literal extends AToken {
    private final int _value;

    public Literal(int value) {
        super(Terminal.LITERAL);
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
