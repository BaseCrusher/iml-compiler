package ch.fhnw.cpib.compiler.error;

import ch.fhnw.cpib.compiler.Scanner;

public class LexicalError extends Throwable {
    int line;
    int col;
    Scanner.ScannerState state;

    public LexicalError(int line, int col, Scanner.ScannerState state){
        this.line = line;
        this.col = col;
        this.state = state;
    }

    @Override
    public String toString(){
        return "Lexical error at line " + line + ", column " + col + ", with state " + state +".";
    }
}
