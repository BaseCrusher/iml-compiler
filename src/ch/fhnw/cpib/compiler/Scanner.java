package ch.fhnw.cpib.compiler;

import ch.fhnw.cpib.compiler.error.LexicalError;
import ch.fhnw.cpib.compiler.tokens.AttributeToken;
import ch.fhnw.cpib.compiler.tokens.ITokenList;
import ch.fhnw.cpib.compiler.tokens.KeywordToken;
import ch.fhnw.cpib.compiler.tokens.Symbols;
import ch.fhnw.cpib.compiler.tokens.Token;
import ch.fhnw.cpib.compiler.tokens.TokenList;
import ch.fhnw.cpib.compiler.tokens.enums.AttributeTerminals;
import ch.fhnw.cpib.compiler.tokens.enums.KeywordTerminals;

public class Scanner {

    public ITokenList scan(CharSequence cs) throws LexicalError {
        if (cs.length() == 0 || cs.charAt(cs.length() - 1) == '\n') {
            throw new LexicalError();
        }

        int state = 0;
        long numAccu = 0L;
        StringBuilder currentWord = new StringBuilder();
        ITokenList list = new TokenList();

        for (int i = 0; i < cs.length(); i++) {
            char c = cs.charAt(i);
            switch (state) {
                case 0:
                    if (('A' <= c && c <= 'Z') || ('a' <= c && c <= 'z')) {
                        // is a letter
                        currentWord.append(c);
                       state = 1;
                    } else if ('0' <= c && c <= '9') {
                        // is number
                        state = 2;
                    } else if (Symbols.contains(c)) {
                        // is symbol
                        currentWord.append(c);
                        state = 3;
                    } else if ((' ' == c) || ('\t' == c) || ('\n' == c)) {
                        // is whitespace
                        state = 4;
                    } else {
                        throw new LexicalError();
                    }
                    break;
                case 1:
                    if (('A' <= c && c <= 'Z') || ('a' <= c && c <= 'z') || ('0' <= c && c <= '9')) {
                        currentWord.append(c);
                    } else {
                        if (KeywordTerminals.contains(currentWord.toString().toUpperCase())) {
                            // is an keyword
                            list.add(new KeywordToken(KeywordTerminals.valueOf(currentWord.toString())));
                            state = 0;
                        } else {
                            // must be an identifier
                            list.add(new AttributeToken<>(AttributeTerminals.IDENT, currentWord.toString()));
                            state = 0;
                        }
                        currentWord.setLength(0);
                    }
                    break;
                case 2:
                    if ('0' <= c && c <= '9') {
                        currentWord.append(c);
                    } else {
                        list.add(new AttributeToken<>(AttributeTerminals.LITERAL, Integer.valueOf(currentWord.toString())));
                        state = 0;
                        currentWord.setLength(0);
                    }
                    break;
                case 3:
                    if (c == '=') {
                        currentWord.append(c);
                    }
                    state = 0;
                    // TODO add symbol with operator
                    list.add(new Token());
                    currentWord.setLength(0);
                    break;
                case 4:
                    state = 0;
                    currentWord.setLength(0);
                    break;
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
