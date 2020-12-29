package ch.fhnw.cpib.compiler.parser.nts;

import ch.fhnw.cpib.compiler.error.GrammarError;
import ch.fhnw.cpib.compiler.parser.IAbstractNode;
import ch.fhnw.cpib.compiler.parser.INtsParser;
import ch.fhnw.cpib.compiler.parser.Parser;
import ch.fhnw.cpib.compiler.parser.abstracts.AbsTypedIdent;
import ch.fhnw.cpib.compiler.tokens.IToken;
import ch.fhnw.cpib.compiler.tokens.enums.types.IType;
import ch.fhnw.cpib.compiler.tokens.enums.types.Types;

import static ch.fhnw.cpib.compiler.tokens.enums.AttributeTerminals.IDENT;
import static ch.fhnw.cpib.compiler.tokens.enums.AttributeTerminals.TYPE;
import static ch.fhnw.cpib.compiler.tokens.enums.KeywordTerminals.COLON;

public class TypedIdent implements INtsParser {

    private final IToken identifier;
    private final IToken type;
    private final OptArrDecl optArrDecl;
    private final String string;

    public TypedIdent() throws GrammarError {
        identifier = Parser.getCurrentToken();
        if (identifier.hasTerminal(IDENT)) {
            Parser.consume(IDENT);
            Parser.consume(COLON);
            type = Parser.consume(TYPE);
            optArrDecl = new OptArrDecl();
            string = identifier.toString() + " : " + type.toString() + " " + optArrDecl.toString();
        } else {
            throw new GrammarError(identifier);
        }

    }

    public IAbstractNode toAbsSyn() {
        if (optArrDecl.getEpsilon() != null) {
            return new AbsTypedIdent(identifier.getValue(), type.getValue(), null);
        }
        return new AbsTypedIdent(identifier.getValue(), type.getValue(), optArrDecl.toAbsSyn(type));
    }

    @Override
    public String toString() {
        return string;
    }

    public IToken getIdentifier() {
        return identifier;
    }

    public IToken getToken() {
        return type;
    }

    public IType getType() {
        return Types.getByName(type.getValue());
    }
}
