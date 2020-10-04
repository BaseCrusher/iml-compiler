package ch.fhnw.cpib.compiler.tokens;

public interface ITokenList {
    public void add(IToken token);

    public void reset();

    public IToken nextToken();

    public String toString();
}