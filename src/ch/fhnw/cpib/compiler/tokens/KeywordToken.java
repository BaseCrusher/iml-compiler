package ch.fhnw.cpib.compiler.tokens;

import ch.fhnw.cpib.compiler.tokens.enums.ITerminal;
import ch.fhnw.cpib.compiler.tokens.enums.KeywordTerminals;

public class KeywordToken implements IToken {
    private final ITerminal terminal;
    private final int line;
    private final int column;

    public KeywordToken(KeywordTerminals t, int line, int column) {
        terminal = t;
        this.line = line;
        this.column = column;
    }

    @Override
    public ITerminal getTerminal() {
        return terminal;
    }

    @Override
    public String toString(){
        return terminal.toString() ;
    }

    @Override
    public int getLine() {
        return line;
    }

    @Override
    public int getColumn() {
        return column;
    }

    @Override
    public boolean hasTerminal(ITerminal terminal) {
        return this.terminal == terminal;
    }
}
