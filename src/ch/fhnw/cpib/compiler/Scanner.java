package ch.fhnw.cpib.compiler;

import ch.fhnw.cpib.compiler.error.LexicalError;
import ch.fhnw.cpib.compiler.tokens.AttributeToken;
import ch.fhnw.cpib.compiler.tokens.ITokenList;
import ch.fhnw.cpib.compiler.tokens.KeywordToken;
import ch.fhnw.cpib.compiler.tokens.enums.dictionary.Symbols;
import ch.fhnw.cpib.compiler.tokens.TokenList;
import ch.fhnw.cpib.compiler.tokens.enums.AttributeTerminals;
import ch.fhnw.cpib.compiler.tokens.enums.KeywordTerminals;
import ch.fhnw.cpib.compiler.tokens.enums.operators.AddOperators;
import ch.fhnw.cpib.compiler.tokens.enums.operators.IRelOperator;
import ch.fhnw.cpib.compiler.tokens.enums.operators.MultOperators;
import ch.fhnw.cpib.compiler.tokens.enums.operators.RelOperators;

public class Scanner {

    public final ITokenList list = new TokenList();

    public ITokenList scan(CharSequence cs) throws LexicalError {
        if (cs.length() == 0 || cs.charAt(cs.length() - 1) == '\n') {
            throw new LexicalError();
        }

        int state = 0;
        long numAccu = 0L;
        StringBuilder currentWord = new StringBuilder();

        for (int i = 0; i < cs.length(); i++) {
            char c = cs.charAt(i);
            switch (state) {
                case 0:
                    state = initState(c, currentWord);
                    break;
                case 1:
                    state = letterState(c, currentWord);
                    break;
                case 2:
                    state = numberState(c, currentWord);
                    break;
                case 3:
                    state = symbolState(c, currentWord);
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
        list.add(new KeywordToken(KeywordTerminals.SENTINEL));

        return list;
    }

    private int initState(char c, StringBuilder currentWord) throws LexicalError {
        if (('A' <= c && c <= 'Z') || ('a' <= c && c <= 'z')) {
            // is a letter
            currentWord.append(c);
            return 1;
        } else if ('0' <= c && c <= '9') {
            // is number
            currentWord.append(c);
            return 2;
        } else if (Symbols.contains(Character.toString(c)) > 0) {
            // is symbol
            currentWord.append(c);
            return 3;
        } else if ((' ' == c) || ('\t' == c) || ('\n' == c)) {
            // is whitespace
            return 4;
        } else {
            throw new LexicalError();
        }
    }

    private int letterState(char c, StringBuilder currentWord) {
        if (('A' <= c && c <= 'Z') || ('a' <= c && c <= 'z') || ('0' <= c && c <= '9') || c == '_' || c == '\'') {
            currentWord.append(c);
            return 1;
        // TODO: all keywords that arent identifier
        } else {
            if (KeywordTerminals.contains(currentWord.toString().toUpperCase())) {
                // must be a keyword
                list.add(new KeywordToken(KeywordTerminals.valueOf(currentWord.toString())));
            } else {
                // must be an identifier
                list.add(new AttributeToken<>(AttributeTerminals.IDENT, currentWord.toString()));
            }
            currentWord.setLength(0);
            return 0;
        }
    }

    private int numberState(char c, StringBuilder currentWord) {
        if ('0' <= c && c <= '9') {
            currentWord.append(c);
            return 2;
        // TODO NATURAL INT
        } else {
            list.add(new AttributeToken<>(AttributeTerminals.LITERAL, Integer.valueOf(currentWord.toString())));
            currentWord.setLength(0);
            return 0;
        }
    }

    private int symbolState(char c, StringBuilder currentWord) throws LexicalError {
        currentWord.append(c);

        if (Symbols.contains(currentWord.toString()) > 1)
            return 3;

        if (Symbols.contains(Character.toString(c)) == 1) {
            // Checks if the currentWord is a valid symbol. if not reads another char.
            Symbols symbol = null;
            try {
                symbol = Symbols.valueOf(Character.toString(c));
            }catch (IllegalArgumentException ignored){}
            if(symbol == null)
                return 3;

            // Creates the right attribute to the symbol.
            if (RelOperators.contains(symbol)) {
                list.add(new AttributeToken<IRelOperator>(AttributeTerminals.RELOPR, RelOperators.valueOf(symbol.name())));
            } else if (AddOperators.contains(symbol)) {
                list.add(new AttributeToken<IRelOperator>(AttributeTerminals.ADDOPR, RelOperators.valueOf(symbol.name())));
            } else if (MultOperators.contains(symbol)) {
                list.add(new AttributeToken<IRelOperator>(AttributeTerminals.MULOPR, RelOperators.valueOf(symbol.name())));
            }
            return 0;
        }
        
        throw new LexicalError();
    }
}
