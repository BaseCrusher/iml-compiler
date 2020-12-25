package ch.fhnw.cpib.compiler.parser.nts;

import ch.fhnw.cpib.compiler.error.GrammarError;
import ch.fhnw.cpib.compiler.parser.INtsParser;
import ch.fhnw.cpib.compiler.parser.Parser;
import ch.fhnw.cpib.compiler.tokens.IToken;

import static ch.fhnw.cpib.compiler.tokens.enums.AttributeTerminals.IDENT;
import static ch.fhnw.cpib.compiler.tokens.enums.AttributeTerminals.TYPE;
import static ch.fhnw.cpib.compiler.tokens.enums.KeywordTerminals.COLON;

public class TypedIdent implements INtsParser {
    private IToken identifier;
    private IToken type;
    private INtsParser optArrDecl;
    private final String string;

    public TypedIdent() throws GrammarError {
        identifier = Parser.getCurrentToken();
        if (identifier.hasTerminal(IDENT)) {
            Parser.consume(IDENT);
            Parser.consume(COLON);
            type = Parser.consume(TYPE);
            optArrDecl = new OptArrDecl();
            string = identifier.getTerminal().toString() + " : " + type.getTerminal().toString() + " : " + optArrDecl.toString();
        } else {
            throw new GrammarError(identifier);
        }

    }

    @Override
    public String toString() {
        return string;
    }

    public IToken getIdentifier() {
        return identifier;
    }

    public IToken getType() {
        return type;
    }
}
