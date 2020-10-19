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
    public enum ScannerState {
        UnknownState,
        InitState,
        LetterState,
        NumberState,
        SymbolState,
        CommentState,
    }

    public final ITokenList list = new TokenList();
    private ScannerState state = ScannerState.UnknownState;
    private final StringBuilder currentRead = new StringBuilder();
    private boolean isWordFinished = false;
    private int column = 1;
    private int line = 1;

    public ITokenList scan(CharSequence cs) throws LexicalError {
        if (cs.length() == 0) {
            throw new LexicalError(line, column, state);
        }
        if (cs.charAt(cs.length() - 1) != '\n') {
            throw new LexicalError(-1, column, state);
        }
        char currentChar = ' ';
        state = ScannerState.InitState;
        for (int i = 0; i < cs.length(); i++) {
            currentChar = cs.charAt(i);
            column++;
            switch (state) {
                case InitState:
                    processInitState(currentChar);
                    break;
                case LetterState:
                    processLetterState(currentChar);
                    break;
                case NumberState:
                    processNumberState(currentChar);
                    break;
                case SymbolState:
                    processSymbolState(currentChar);
                    break;
                case CommentState:
                    processCommentState(currentChar);
                    break;
                default:
                    break;
            }
            // Sets line and char counter correctly
            if (currentChar == '\n'){
                column = 1;
                line++;
            }
            // checks if the word is finished and last char was not a \n. if true i counter needs to go 1 char back.
            if(isWordFinished && currentChar != '\n') {
                i--;
                column--;
            }
            isWordFinished = false;
        }

        list.add(new KeywordToken(KeywordTerminals.SENTINEL));
        return list;
    }

    private void processInitState(char currentChar) throws LexicalError {
        if (('A' <= currentChar && currentChar <= 'Z') || ('a' <= currentChar && currentChar <= 'z')) {
            // is a letter
            state = ScannerState.LetterState;
            currentRead.append(currentChar);
            return;
        }
        if ('0' <= currentChar && currentChar <= '9') {
            // is number
            state = ScannerState.NumberState;
            currentRead.append(currentChar);
            return;
        }
        if (Symbols.contains(Character.toString(currentChar)) > 0) {
            // is symbol or comment (finer decision in symbol state)
            state = ScannerState.SymbolState;
            processSymbolState(currentChar);
            return;
        }
        if ((' ' == currentChar) || ('\t' == currentChar) || ('\n' == currentChar)) {
            // is whitespace
            state = ScannerState.InitState;
            return;
        }
        throw new LexicalError(line, column, state);
    }

    private void processLetterState(char currentChar) {
        if (('A' <= currentChar && currentChar <= 'Z') ||
            ('a' <= currentChar && currentChar <= 'z') ||
            ('0' <= currentChar && currentChar <= '9') ||
             currentChar == '_' || currentChar == '\'' ) {
            currentRead.append(currentChar);
            return;
        }

        String name = currentRead.toString();
        currentRead.setLength(0);
        isWordFinished = true;
        state = ScannerState.InitState;

        // Check if it is a mode
        IChangeMode cMode = ChangeModes.getByName(name);
        if (cMode != null){
            list.add(new AttributeToken<>(AttributeTerminals.CHANGEMODE, cMode));
            return;
        }
        IFlowMode fMode = FlowModes.getByName(name);
        if (fMode != null){
            list.add(new AttributeToken<>(AttributeTerminals.FLOWMODE, fMode));
            return;
        }
        IMechMode mMode = MechModes.getByName(name);
        if (mMode != null){
            list.add(new AttributeToken<>(AttributeTerminals.MENCHMODE, mMode));
            return;
        }

        // Check if it is a type
        IType type = Types.getByName(name);
        if (type != null){
            list.add(new AttributeToken<>(AttributeTerminals.TYPE, type));
            return;
        }

        // check if it is a mult operator
        IMultOperator multOperator = MultOperators.getByName(name);
        if (multOperator != null){
            list.add(new AttributeToken<>(AttributeTerminals.MULOPR, multOperator));
            return;
        }

        // check if it is "true"
        if (name.toLowerCase().equals("true")) {
            list.add(new AttributeToken<>(AttributeTerminals.LITERAL, true));
            return;
        }

        // check if it is "false"
        if (name.toLowerCase().equals("false")) {
            list.add(new AttributeToken<>(AttributeTerminals.LITERAL, false));
            return;
        }

        // Check if it is a keyword
        KeywordTerminals terminal = KeywordTerminals.getByName(name);
        if (terminal != null){
            list.add(new KeywordToken(terminal));
            return;
        }

        // Must be an identifier
        list.add(new AttributeToken<>(AttributeTerminals.IDENT, name));
    }

    private void processNumberState(char currentChar) {
        if ('0' <= currentChar && currentChar <= '9') {
            currentRead.append(currentRead);
            return;
            // TODO NATURAL INT
        }

        String s = currentRead.toString();
        list.add(new AttributeToken<>(AttributeTerminals.LITERAL, s));
        state = ScannerState.InitState;
        currentRead.setLength(0);
        isWordFinished = true;
    }

    private void processSymbolState(char currentChar) throws LexicalError {
        currentRead.append(currentChar);
        String currentString = currentRead.toString();

        int numOfSymbols = Symbols.contains(currentString);
        // Checks if there are more possible symbols than one. if true, another chars needs to be read.
        if (numOfSymbols > 1) {
            state = ScannerState.SymbolState;
            return;
        }

        if (numOfSymbols == 0) {
            currentString = currentString.substring(0, currentString.length() - 1);
            numOfSymbols = 1;
            isWordFinished = true;
        }

        if (numOfSymbols == 1) {
            // Checks if the currentString is a valid symbol. if not reads another char.
            Symbols symbol = Symbols.getByName(currentString);
            if(symbol == null) {
                state = ScannerState.SymbolState;
                return;
            }

            currentRead.setLength(0);
            if(symbol == Symbols.COMMENT){
                state = ScannerState.CommentState;
                return;
            }

            // Creates the right attribute to the symbol.
            state = ScannerState.InitState;
            if (RelOperators.contains(symbol)) {
                list.add(new AttributeToken<IRelOperator>(AttributeTerminals.RELOPR, RelOperators.valueOf(symbol.name())));
                return;
            }
            if (AddOperators.contains(symbol)) {
                list.add(new AttributeToken<IAddOperator>(AttributeTerminals.ADDOPR, AddOperators.valueOf(symbol.name())));
                return;
            }
            IMultOperator multOperator = MultOperators.contains(symbol);
            if (multOperator != null) {
                list.add(new AttributeToken<>(AttributeTerminals.MULOPR, multOperator));
                return;
            }
            KeywordTerminals keywordTerminal = KeywordTerminals.getByName(symbol.name());
            if (keywordTerminal != null) {
                list.add(new KeywordToken(keywordTerminal));
                return;
            }
        }


        throw new LexicalError(line, column, state);
    }

    private void processCommentState(char currentChar) {
        if (currentChar != '\n'){
            state = ScannerState.CommentState;
            return;
        }
        state = ScannerState.InitState;
    }
}
