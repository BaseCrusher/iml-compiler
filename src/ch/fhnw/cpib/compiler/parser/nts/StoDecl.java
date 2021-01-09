package ch.fhnw.cpib.compiler.parser.nts;

import ch.fhnw.cpib.compiler.error.GrammarError;
import ch.fhnw.cpib.compiler.parser.Environment;
import ch.fhnw.cpib.compiler.parser.IAbstractNode;
import ch.fhnw.cpib.compiler.parser.INtsParser;
import ch.fhnw.cpib.compiler.parser.Parser;
import ch.fhnw.cpib.compiler.parser.Variable;
import ch.fhnw.cpib.compiler.parser.abstracts.AbsStoDecl;
import ch.fhnw.cpib.compiler.tokens.IToken;

import static ch.fhnw.cpib.compiler.tokens.enums.AttributeTerminals.CHANGEMODE;
import static ch.fhnw.cpib.compiler.tokens.enums.AttributeTerminals.IDENT;
import static ch.fhnw.cpib.compiler.tokens.enums.AttributeTerminals.LITERAL;


public class StoDecl implements INtsParser {
    private final IToken token;
    private final Environment environment;
    private OptChangemode optChangemode;
    private TypedIdent typedIdent;

    public StoDecl(Environment environment, boolean isReturns) throws GrammarError {
        this.environment = environment;
        token = Parser.getCurrentToken();
        if (token.hasTerminal(IDENT, CHANGEMODE)) {
            optChangemode = new OptChangemode();
            typedIdent = new TypedIdent();
            Variable variable;
            if (typedIdent.getArrDecl().getEpsilon() == null) {
                variable = new Variable(typedIdent.getIdentifier().getValue(), null, null, optChangemode, typedIdent.getToken(), environment.getStartAddress() + environment.getVars().size(), true);
            }
            else {
                variable = new Variable(typedIdent.getIdentifier().getValue(), optChangemode, typedIdent.getToken(), environment.getStartAddress() + environment.getVars().size());
            }
            variable.setIsReturns(isReturns);
            environment.putVariable(typedIdent.getIdentifier().getValue(), variable);
            OptArrDecl optArrDecl = typedIdent.getArrDecl();
            if (optArrDecl != null){
                ArrayDecl arrDecl = optArrDecl.getArrDecl();
                if (arrDecl != null) {
                    OptLit optLit = arrDecl.getOptLit();
                    if (optLit != null) {
                        IToken token = optLit.getToken();
                        if (token.hasTerminal(LITERAL)) {
                            for (int i = 1; i <= Integer.parseInt(token.getValue()); i++) {
                                Variable arrVar = new Variable(typedIdent.getIdentifier().getValue()+"["+i+"]", optChangemode, typedIdent.getToken(), environment.getStartAddress() + environment.getVars().size());
                                environment.putVariable(typedIdent.getIdentifier().getValue()+"["+i+"]", arrVar);
                            }
                        }
                    }
                }
            }
        } else {
            throw new GrammarError(token);
        }

    }

    @Override
    public String toString() {
        return optChangemode.toString() + " " + typedIdent.toString();
    }

    public IAbstractNode toAbsSyn() {
        return new AbsStoDecl(optChangemode.toAbsSyn(), typedIdent, environment);
    }

    public IToken getToken() {
        return token;
    }

    public INtsParser getOptChangemode() {
        return optChangemode;
    }

    public TypedIdent getTypedIdent() {
        return typedIdent;
    }
}
