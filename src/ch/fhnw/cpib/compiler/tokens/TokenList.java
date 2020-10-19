package ch.fhnw.cpib.compiler.tokens;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import ch.fhnw.cpib.compiler.tokens.enums.KeywordTerminals;

public class TokenList implements ITokenList {

    private final List<IToken> list = new LinkedList<IToken>();
    private Iterator<IToken> listIterator;

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
        return listIterator.next();
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
        return list.stream().anyMatch(token -> token.getTerminal().equals(KeywordTerminals.SENTINEL));
    }
}
