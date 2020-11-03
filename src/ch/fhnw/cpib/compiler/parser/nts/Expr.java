package ch.fhnw.cpib.compiler.parser.nts;

import ch.fhnw.cpib.compiler.error.GrammarError;
import ch.fhnw.cpib.compiler.parser.INtsParser;
import ch.fhnw.cpib.compiler.parser.Parser;
import ch.fhnw.cpib.compiler.tokens.IToken;

import static ch.fhnw.cpib.compiler.tokens.enums.AttributeTerminals.IDENT;
import static ch.fhnw.cpib.compiler.tokens.enums.AttributeTerminals.LITERAL;
import static ch.fhnw.cpib.compiler.tokens.enums.AttributeTerminals.MONOPR;
import static ch.fhnw.cpib.compiler.tokens.enums.KeywordTerminals.LPAREN;

public class Expr implements INtsParser {
    private final IToken token;
    private INtsParser term1;
    private INtsParser repBoolOprTerm1;

    public Expr() throws GrammarError {
        token = Parser.consume(LPAREN, MONOPR, IDENT, LITERAL);
        term1 = null;
        repBoolOprTerm1 = new RepBoolOprTerm1();
    }

    @Override
    public String toString() {
        return token.getTerminal().toString() + " " + term1.toString() + " " + repBoolOprTerm1.toString();
    }

    public IToken getToken() {
        return token;
    }

    public INtsParser getTerm1() {
        return term1;
    }

    public void setTerm1(INtsParser term1) {
        this.term1 = term1;
    }

    public INtsParser getRepBoolOprTerm1() {
        return repBoolOprTerm1;
    }

    public void setRepBoolOprTerm1(INtsParser repBoolOprTerm1) {
        this.repBoolOprTerm1 = repBoolOprTerm1;
    }
}
