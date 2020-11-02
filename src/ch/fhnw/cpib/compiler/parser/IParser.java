package ch.fhnw.cpib.compiler.parser;

import ch.fhnw.cpib.compiler.error.GrammarError;

public interface IParser {
    IConcreteTree parse() throws GrammarError;
}
