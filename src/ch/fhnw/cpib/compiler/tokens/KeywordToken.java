package ch.fhnw.cpib.compiler.tokens;

import ch.fhnw.cpib.compiler.tokens.enums.ITerminal;
import ch.fhnw.cpib.compiler.tokens.enums.KeywordTerminals;

public class KeywordToken implements IToken {
    private final ITerminal terminal;

    public KeywordToken(KeywordTerminals t) {
        terminal = t;
    }

    @Override
    public ITerminal getTerminal() {
        return terminal;
    }

    @Override
    public String toString(){
        return terminal.toString() ;
    }
}
