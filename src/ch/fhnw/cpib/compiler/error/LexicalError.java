package ch.fhnw.cpib.compiler.error;

public class LexicalError extends Throwable {
    int line;
    int col;
    int state;

    public LexicalError(int line, int col, int state){
        this.line = line;
        this.col = col;
        this.state = state;
    }

    @Override
    public String toString(){
        return "Lexical error at line " + line + ", column " + col + ", with state " + state +".";
    }
}
