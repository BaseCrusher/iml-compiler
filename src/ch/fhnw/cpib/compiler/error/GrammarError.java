package ch.fhnw.cpib.compiler.error;

public class GrammarError extends Throwable {

    public GrammarError(String errorMessage) {
        super(errorMessage);
    }
}
