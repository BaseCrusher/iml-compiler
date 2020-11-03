package ch.fhnw.cpib.compiler.parser.nts;

import ch.fhnw.cpib.compiler.error.GrammarError;
import ch.fhnw.cpib.compiler.parser.INtsParser;
import ch.fhnw.cpib.compiler.parser.Parser;
import ch.fhnw.cpib.compiler.tokens.IToken;

import static ch.fhnw.cpib.compiler.tokens.enums.AttributeTerminals.*;
import static ch.fhnw.cpib.compiler.tokens.enums.KeywordTerminals.*;

public class Cmd implements INtsParser {
    private IToken token;
    private IToken identifier;
    private INtsParser exprList;
    private INtsParser optGlobInits;
    private INtsParser expr1;
    private INtsParser expr2;
    private INtsParser cpsCmd;
    private INtsParser optElseCpsCmd;
    private String string;

    public Cmd() throws GrammarError {
        token = Parser.consume(SKIP, LPAREN, MONOPR, IDENT, LITERAL, IF, WHILE, CALL, DEBUGIN, DEBUGOUT);
        if (token.hasTerminal(SKIP)) {
            string = token.getTerminal().toString();
        }
        else if (token.hasTerminal(LPAREN)
                || token.hasTerminal(MONOPR)
                || token.hasTerminal(IDENT)
                || token.hasTerminal(LITERAL)) {
            expr1 = null;
            Parser.consume(BECOMES);
            expr2 = null;
            string = expr1.toString() + " BECOMES " + expr2;
        }
        else if (token.hasTerminal(IF)) {
            expr1 = null;
            Parser.consume(THEN);
            cpsCmd = null;
            optElseCpsCmd = null;
            Parser.consume(ENDIF);
            string = token.getTerminal().toString() + " " + expr1.toString() + " THEN " + cpsCmd.toString() + " " + optElseCpsCmd.toString() + " ENDIF";
        }
        else if (token.hasTerminal(WHILE)) {
            expr1 = null;
            Parser.consume(DO);
            cpsCmd = null;
            Parser.consume(ENDWHILE);
            string = token.getTerminal().toString() + " " + expr1.toString() + " DO " + cpsCmd.toString() + " ENDWHILE";
        }
        else if (token.hasTerminal(CALL)) {
            identifier = Parser.consume(IDENT);
            exprList = null;
            optGlobInits = null;
            string = token.getTerminal().toString() + " " + identifier.getTerminal().toString() + " " + exprList.toString() + " " + optGlobInits.toString();
        }
        else {
            expr1 = null;
            string = token.getTerminal().toString() + " " + expr1.toString();
        }
    }

    @Override
    public String toString() {
        return string;
    }

    public IToken getToken() {
        return token;
    }

    public IToken getIdentifier() {
        return identifier;
    }

    public INtsParser getExprList() {
        return exprList;
    }

    public INtsParser getOptGlobInits() {
        return optGlobInits;
    }

    public INtsParser getExpr1() {
        return expr1;
    }

    public INtsParser getExpr2() {
        return expr2;
    }

    public INtsParser getCpsCmd() {
        return cpsCmd;
    }

    public INtsParser getOptElseCpsCmd() {
        return optElseCpsCmd;
    }
}
