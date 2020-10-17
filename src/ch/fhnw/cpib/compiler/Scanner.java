package ch.fhnw.cpib.compiler;

import ch.fhnw.cpib.compiler.error.LexicalError;
import ch.fhnw.cpib.compiler.tokens.AttributeToken;
import ch.fhnw.cpib.compiler.tokens.ITokenList;
import ch.fhnw.cpib.compiler.tokens.KeywordToken;
import ch.fhnw.cpib.compiler.tokens.enums.*;
import ch.fhnw.cpib.compiler.tokens.enums.dictionary.Symbols;
import ch.fhnw.cpib.compiler.tokens.TokenList;
import ch.fhnw.cpib.compiler.tokens.enums.modes.*;
import ch.fhnw.cpib.compiler.tokens.enums.operators.*;
import ch.fhnw.cpib.compiler.tokens.enums.types.IType;
import ch.fhnw.cpib.compiler.tokens.enums.types.Types;

public class Scanner {

    public final ITokenList list = new TokenList();

    int line = 0;
    int col = 0;

    public ITokenList scan(CharSequence cs) throws LexicalError {
        if (cs.length() == 0) {
            throw new LexicalError(0, 0, -1);
        }
        if (cs.charAt(cs.length() - 1) != '\n') {
            throw new LexicalError(-1, cs.charAt(cs.length() - 1), -1);
        }

        int state = 0;
        StringBuilder currentWord = new StringBuilder();

        for (int i = 0; i < cs.length(); i++) {
            char c = cs.charAt(i);
            col++;
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
                case 5:
                    state = commentState(c, currentWord);
                    break;
                default:
                    throw new Error("Default");
            }
        }
        if (state == 0) {
            throw new LexicalError(line, col, 0);
        }
        list.add(new KeywordToken(KeywordTerminals.SENTINEL));

        return list;
    }



    private int initState(char c, StringBuilder currentWord) throws LexicalError {
        if (('A' <= c && c <= 'Z') || ('a' <= c && c <= 'z')) {
            // is a letter
            currentWord.append(c);
            return 1;
        }
        if ('0' <= c && c <= '9') {
            // is number
            currentWord.append(c);
            return 2;
        }
        if (Symbols.contains(Character.toString(c)) > 0) {
            // is symbol
            currentWord.append(c);
            return 3;
        }
        if ((' ' == c) || ('\t' == c) || ('\n' == c)) {
            // is whitespace
            if ('\n' == c) {
                line++;
                col = 0;
            }
            return 4;
        }
        throw new LexicalError(line, col, 0);
    }

    private int letterState(char c, StringBuilder currentWord) {
        if (('A' <= c && c <= 'Z') || ('a' <= c && c <= 'z') || ('0' <= c && c <= '9') || c == '_' || c == '\'') {
            currentWord.append(c);
            return 1;
        }

        String name = currentWord.toString();
        currentWord.setLength(0);

        // Check if it is a mode
        IChangeMode cMode = ChangeModes.getByName(name);
        if (cMode != null){
            list.add(new AttributeToken<>(AttributeTerminals.CHANGEMODE, cMode));
            return 0;
        }
        IFlowMode fMode = FlowModes.getByName(name);
        if (fMode != null){
            list.add(new AttributeToken<>(AttributeTerminals.FLOWMODE, fMode));
            return 0;
        }
        IMechMode mMode = MechModes.getByName(name);
        if (mMode != null){
            list.add(new AttributeToken<>(AttributeTerminals.MENCHMODE, mMode));
            return 0;
        }

        // Check if it is a type
        IType type = Types.getByName(name);
        if (type != null){
            list.add(new AttributeToken<>(AttributeTerminals.TYPE, type));
            return 0;
        }

        IMultOperator multOperator = MultOperators.getByName(name);
        if (multOperator != null){
            list.add(new AttributeToken<>(AttributeTerminals.MULOPR, multOperator));
            return 0;
        }

        if (name.toLowerCase().equals("true")){
            list.add(new AttributeToken<>(AttributeTerminals.LITERAL, true));
            return 0;
        }

        if (name.toLowerCase().equals("false")){
            list.add(new AttributeToken<>(AttributeTerminals.LITERAL, false));
            return 0;
        }

        // Check if it is a keyword
        KeywordTerminals terminal = KeywordTerminals.getByName(name);
        if (terminal != null){
            list.add(new KeywordToken(terminal));
            return 0;
        }

        // Must be an identifier
        list.add(new AttributeToken<>(AttributeTerminals.IDENT, name));
        return 0;
    }

    private int numberState(char c, StringBuilder currentWord) {
        if ('0' <= c && c <= '9') {
            currentWord.append(c);
            return 2;
        // TODO NATURAL INT
        }

        list.add(new AttributeToken<>(AttributeTerminals.LITERAL, Integer.valueOf(currentWord.toString())));
        currentWord.setLength(0);
        return 0;
    }

    private int symbolState(char c, StringBuilder currentWord) throws LexicalError {
        currentWord.append(c);

        String currentString = currentWord.toString();
        if (Symbols.contains(currentString) > 1)
            return 3;

        if (Symbols.contains(currentString) == 1) {
            // Checks if the currentWord is a valid symbol. if not reads another char.

            Symbols symbol = Symbols.getByName(currentString);
            if(symbol == null)
                return 3;

            if(symbol == Symbols.COMMENT){
                return 5;
            }

            // Creates the right attribute to the symbol.
            if (RelOperators.contains(symbol)) {
                list.add(new AttributeToken<IRelOperator>(AttributeTerminals.RELOPR, RelOperators.valueOf(symbol.name())));
                return 0;
            }
            if (AddOperators.contains(symbol)) {
                list.add(new AttributeToken<IAddOperator>(AttributeTerminals.ADDOPR, AddOperators.valueOf(symbol.name())));
                return 0;
            }
            IMultOperator multOperator = MultOperators.contains(symbol);
            if (multOperator != null) {
                list.add(new AttributeToken<>(AttributeTerminals.MULOPR, multOperator));
                return 0;
            }
        }

        throw new LexicalError(line, col, 4);
    }

    private int commentState(char c, StringBuilder currentWord) {
        if (c != '\n'){
            return 5;
        }
        currentWord.setLength(0);
        return 0;
    }
}
