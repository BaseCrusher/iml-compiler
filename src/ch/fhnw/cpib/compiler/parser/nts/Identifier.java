package ch.fhnw.cpib.compiler.parser.nts;

import ch.fhnw.cpib.compiler.parser.Environment;
import ch.fhnw.cpib.compiler.parser.INtsParser;
import ch.fhnw.cpib.compiler.tokens.IToken;

public class Identifier implements INtsParser {
    IToken ident;
    Environment environment;

    public Identifier(IToken ident, Environment environment) {
        this.ident = ident;
        this.environment = environment;
    }

}
