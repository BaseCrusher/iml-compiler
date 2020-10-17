package ch.fhnw.cpib.compiler.parser;

import ch.fhnw.cpib.compiler.error.GrammarError;
import ch.fhnw.cpib.compiler.tokens.IToken;
import ch.fhnw.cpib.compiler.tokens.enums.ITerminal;

public interface IParser {

    IToken consume(ITerminal expectedTerminal) throws GrammarError;

    ParseTree parse() throws GrammarError;
}
