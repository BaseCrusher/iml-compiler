package ch.fhnw.cpib.compiler.tokens;

public interface ITokenList {
    void add(IToken token);

    void reset();

    IToken nextToken();

    String toString();

    boolean hasSentinel();
}
