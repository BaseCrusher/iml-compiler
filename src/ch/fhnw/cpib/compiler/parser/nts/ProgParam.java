package ch.fhnw.cpib.compiler.parser.nts;

import ch.fhnw.cpib.compiler.error.GrammarError;
import ch.fhnw.cpib.compiler.parser.Environment;
import ch.fhnw.cpib.compiler.parser.IAbstractNode;
import ch.fhnw.cpib.compiler.parser.INtsParser;
import ch.fhnw.cpib.compiler.parser.Parser;
import ch.fhnw.cpib.compiler.parser.Variable;
import ch.fhnw.cpib.compiler.parser.abstracts.AbsProgParam;
import ch.fhnw.cpib.compiler.tokens.IToken;

import static ch.fhnw.cpib.compiler.tokens.enums.AttributeTerminals.CHANGEMODE;
import static ch.fhnw.cpib.compiler.tokens.enums.AttributeTerminals.FLOWMODE;
import static ch.fhnw.cpib.compiler.tokens.enums.AttributeTerminals.IDENT;

public class ProgParam implements INtsParser {
    private final IToken token;
    private final OptFlowmode optFlowmode;
    private final OptChangemode optChangemode;
    private final TypedIdent typedIdent;
    private final String string;

    public ProgParam(Environment globalEnv) throws GrammarError {
        token = Parser.getCurrentToken();
        if (token.hasTerminal(IDENT, CHANGEMODE, FLOWMODE)) {
            optFlowmode = new OptFlowmode();
            optChangemode = new OptChangemode();
            typedIdent = new TypedIdent();
            Variable param = new Variable(typedIdent.getIdentifier().getValue(), optFlowmode, optChangemode, typedIdent.getToken(), globalEnv.getStartAddress() + globalEnv.getVars().size());
            globalEnv.putVariable(typedIdent.getIdentifier().getValue(), param);
            string = optFlowmode.toString() + " : " + optChangemode.toString() + " : " + typedIdent.toString();
        }
        else {
            throw new GrammarError(token);
        }
    }

    public String toString() {
        return string;
    }

    public IToken getToken() {
        return token;
    }

    public INtsParser getOptFlowmode() {
        return optFlowmode;
    }

    public INtsParser getOptChangemode() {
        return optChangemode;
    }

    public INtsParser getTypedIdent() {
        return typedIdent;
    }

    public IAbstractNode toAbsSyn() {
        return new AbsProgParam(optFlowmode, optChangemode, typedIdent.toAbsSyn());
    }
}
