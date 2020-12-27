package ch.fhnw.cpib.compiler.parser.nts;

import java.lang.reflect.Type;

import ch.fhnw.cpib.compiler.error.GrammarError;
import ch.fhnw.cpib.compiler.parser.Environment;
import ch.fhnw.cpib.compiler.parser.IAbstractNode;
import ch.fhnw.cpib.compiler.parser.INtsParser;
import ch.fhnw.cpib.compiler.parser.IToAbsNode;
import ch.fhnw.cpib.compiler.parser.Parser;
import ch.fhnw.cpib.compiler.parser.Variable;
import ch.fhnw.cpib.compiler.tokens.IToken;

import static ch.fhnw.cpib.compiler.tokens.enums.AttributeTerminals.CHANGEMODE;
import static ch.fhnw.cpib.compiler.tokens.enums.AttributeTerminals.FLOWMODE;
import static ch.fhnw.cpib.compiler.tokens.enums.AttributeTerminals.IDENT;
import static ch.fhnw.cpib.compiler.tokens.enums.AttributeTerminals.MECHMODE;

public class Param implements INtsParser, IToAbsNode {
    private final IToken token;
    private final OptFlowmode optFlowmode;
    private final OptMechmode optMechmode;
    private final OptChangemode optChangemode;
    private final TypedIdent typedIdent;
    private final String string;

    public Param(Environment environment) throws GrammarError {
        token = Parser.getCurrentToken();
        if (token.hasTerminal(IDENT, CHANGEMODE, MECHMODE, FLOWMODE)) {
            optFlowmode = new OptFlowmode();
            optMechmode = new OptMechmode();
            optChangemode = new OptChangemode();
            typedIdent = new TypedIdent();
            Variable variable = new Variable(typedIdent.getIdentifier().getValue(), optFlowmode.toAbsSyn(), optMechmode.toAbsSyn(), optChangemode.toAbsSyn(), typedIdent.getType(), environment.getStartAddress() + environment.getVars().size());
            environment.putVariable(typedIdent.getIdentifier().getValue(), variable);
            string = optFlowmode.toString() + " " + optMechmode.toString() + " " + optChangemode.toString() + " " + typedIdent.toString();
        } else {
            throw new GrammarError(token);
        }

    }

    @Override
    public String toString() {
        return string;
    }

    public IToken getToken() {
        return token;
    }

    public INtsParser getOptFlowmode() {
        return optFlowmode;
    }

    public INtsParser getOptMechmode() {
        return optMechmode;
    }

    public INtsParser getOptChangemode() {
        return optChangemode;
    }

    public INtsParser getTypedIdent() {
        return typedIdent;
    }

    @Override
    public IAbstractNode toAbsSyn() {
        return null;
    }
}
