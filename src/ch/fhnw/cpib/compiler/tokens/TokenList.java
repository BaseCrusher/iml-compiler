package ch.fhnw.cpib.compiler.tokens;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import ch.fhnw.cpib.compiler.tokens.enums.KeywordTerminals;

public class TokenList implements ITokenList {

    private final List<IToken> list = new LinkedList<>();
    private Iterator<IToken> listIterator;
    private IToken currentToken;

    @Override
    public void add(IToken token) {
        if (token == null)
            return;
        list.add(token);
    }

    @Override
    public void reset() {
        list.clear();
    }

    @Override
    public IToken nextToken() {
        if (listIterator == null) {
            listIterator = list.iterator();
        }
        currentToken = listIterator.next();
        return currentToken;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (IToken token : list){
            sb.append(token.toString());
            sb.append(", ");
        }
        sb.setLength(sb.length()-1);
        return sb.toString();
    }

    @Override
    public boolean hasSentinel() {
        return list.get(list.size()-1).getTerminal() == KeywordTerminals.SENTINEL;
    }

    @Override
    public IToken currentToken() {
        return currentToken;
    }
}
