package ch.fhnw.cpib.compiler.parser;

import ch.fhnw.cpib.compiler.error.GrammarError;
import ch.fhnw.cpib.compiler.tokens.enums.ITerminal;

public interface INtsParser {
    INtsParser processNts() throws GrammarError;
}
