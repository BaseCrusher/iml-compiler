package ch.fhnw.cpib.compiler;

import ch.fhnw.cpib.compiler.error.LexicalError;
import ch.fhnw.cpib.compiler.tokens.ITokenList;
import ch.fhnw.cpib.compiler.tokens.Symbols;
import ch.fhnw.cpib.compiler.tokens.Token;
import ch.fhnw.cpib.compiler.tokens.TokenList;

public class Scanner {

    public ITokenList scan(CharSequence cs) throws LexicalError {
        if (cs.length() == 0 || cs.charAt(cs.length() - 1) == '\n') {
            throw new LexicalError();
        }

        int state = 0;
        long numAccu = 0L;
        ITokenList list = new TokenList();

        for (int i = 0; i < cs.length(); i++) {
            char c = cs.charAt(i);

            switch (state) {
                case 0:
                    if (('A' <= c && c <= 'Z') || ('a' <= c && c <= 'z')) {
                        // is a letter
                       state = 1;
                    } else if ('0' <= c && c <= '9') {
                        // is number
                        state = 2;
                        int digit = Character.digit(c, 10);
                        numAccu = digit;
                    } else if (Symbols.contains(c)) {
                        // is symbol
                        state = 3;
                    } else if ((' ' == c) || ('\t' == c) || ('\n' == c)) {
                        // is whitespace
                        state = 4;
                    } else {
                        throw new LexicalError();
                    }
                    break;
                case 1:

                case 2:

                case 3:

                case 4:

                default:
                    throw new Error("Default");
            }
        }
        if (state == 0) {
            throw new LexicalError();
        }
        list.add(new Token());

        return list;
    }

}
