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
    private INtsParser term1;
    private INtsParser repBoolOprTerm1;

    public Expr() throws GrammarError {
        IToken token = Parser.getCurrentToken();
        if (token.hasTerminal(LPAREN, MONOPR, IDENT, LITERAL) {
            term1 = new Term1();
            repBoolOprTerm1 = new RepBoolOprTerm1();
        } 
        else {
            throw new GrammarError(token);
        }
    }

    @Override
    public String toString() {
        return term1.toString() + " " + repBoolOprTerm1.toString();
    }

    public INtsParser getTerm1() {
        return term1;
    }

    public INtsParser getRepBoolOprTerm1() {
        return repBoolOprTerm1;
    }
}
