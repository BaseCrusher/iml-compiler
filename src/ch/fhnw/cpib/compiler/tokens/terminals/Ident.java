package ch.fhnw.cpib.compiler.tokens.terminals;

import ch.fhnw.cpib.compiler.tokens.AToken;

public class Ident extends AToken {
    private final String _ident;

    public Ident(String ident) {
        super(AttributeTerminals.IDENT);
        _ident = ident;
    }
}
