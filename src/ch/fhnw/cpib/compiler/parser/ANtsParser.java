package ch.fhnw.cpib.compiler.parser;

import ch.fhnw.cpib.compiler.error.GrammarError;

public abstract class ANtsParser {
    protected abstract ANtsParser processNts() throws GrammarError;

    public ANtsParser() throws GrammarError {
        processNts();
    }
}
