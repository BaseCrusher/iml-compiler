package ch.fhnw.cpib.compiler.tokens;

public interface ITokenList {
    /**
     * Add a token to the list.
     *
     * @param token IToken to add
     */
    public void add(IToken token);

    /**
     * Resets the list iterator.
     */
    public void reset();

    /**
     * Returns the next Token and then moves the cursor ahead by one token.
     *
     * @return next Token
     */
    public IToken nextToken();

    /**
     * Returns a string with the Tokens.
     *
     * @return String
     */
    public String toString();
}