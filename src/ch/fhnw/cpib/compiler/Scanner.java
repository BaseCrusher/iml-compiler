package ch.fhnw.cpib.compiler;

import ch.fhnw.cpib.compiler.error.LexicalError;
import ch.fhnw.cpib.compiler.tokens.ITokenList;
import ch.fhnw.cpib.compiler.tokens.Token;
import ch.fhnw.cpib.compiler.tokens.TokenList;

public class Scanner {

    public ITokenList scan(CharSequence cs) throws LexicalError {
        if (cs.length() == 0 || cs.charAt(cs.length() - 1) == '\n') {
            throw new LexicalError();
        }

        int state = 0;
        ITokenList list = new TokenList();

        for (int i = 0; i < cs.length(); i++) {
            char c = cs.charAt(i);

            switch (state) {
                case 0:
                    break;
            }
        }
        if (state == 0) {
            throw new LexicalError();
        }
        list.add(new Token());

        return list;
    }

}
