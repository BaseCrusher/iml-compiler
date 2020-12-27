package ch.fhnw.cpib.compiler.parser.nts;

import ch.fhnw.cpib.compiler.error.GrammarError;
import ch.fhnw.cpib.compiler.parser.*;
import ch.fhnw.cpib.compiler.parser.abstracts.*;
import ch.fhnw.cpib.compiler.tokens.IToken;

import static ch.fhnw.cpib.compiler.tokens.enums.AttributeTerminals.IDENT;
import static ch.fhnw.cpib.compiler.tokens.enums.AttributeTerminals.LITERAL;
import static ch.fhnw.cpib.compiler.tokens.enums.AttributeTerminals.MONOPR;
import static ch.fhnw.cpib.compiler.tokens.enums.KeywordTerminals.ARRLEN;
import static ch.fhnw.cpib.compiler.tokens.enums.KeywordTerminals.BECOMES;
import static ch.fhnw.cpib.compiler.tokens.enums.KeywordTerminals.CALL;
import static ch.fhnw.cpib.compiler.tokens.enums.KeywordTerminals.DEBUGIN;
import static ch.fhnw.cpib.compiler.tokens.enums.KeywordTerminals.DEBUGOUT;
import static ch.fhnw.cpib.compiler.tokens.enums.KeywordTerminals.DO;
import static ch.fhnw.cpib.compiler.tokens.enums.KeywordTerminals.ENDIF;
import static ch.fhnw.cpib.compiler.tokens.enums.KeywordTerminals.ENDWHILE;
import static ch.fhnw.cpib.compiler.tokens.enums.KeywordTerminals.IF;
import static ch.fhnw.cpib.compiler.tokens.enums.KeywordTerminals.LPAREN;
import static ch.fhnw.cpib.compiler.tokens.enums.KeywordTerminals.SKIP;
import static ch.fhnw.cpib.compiler.tokens.enums.KeywordTerminals.THEN;
import static ch.fhnw.cpib.compiler.tokens.enums.KeywordTerminals.WHILE;

public class Cmd implements INtsParser, IToAbsNode {
    private final IToken token;
    private IToken identifier;
    private INtsParser exprList;
    private INtsParser optGlobInits;
    private INtsParser expr1;
    private INtsParser expr2;
    private INtsParser cpsCmd;
    private INtsParser optElseCpsCmd;
    private final String string;

    public Cmd() throws GrammarError {
        token = Parser.getCurrentToken();
        if (token.hasTerminal(SKIP)) {
            Parser.consume(SKIP);
            string = token.toString();
        }
        else if (token.hasTerminal(ARRLEN, LPAREN, MONOPR, IDENT, LITERAL)) {
            expr1 = new Expr();
            Parser.consume(BECOMES);
            expr2 = new Expr();
            string = expr1.toString() + " := " + expr2;
        }
        else if (token.hasTerminal(IF)) {
            Parser.consume(IF);
            expr1 = new Expr();
            Parser.consume(THEN);
            cpsCmd = new CpsCmd();
            optElseCpsCmd = new OptElseCpsCmd();
            Parser.consume(ENDIF);
            string = "IF" + expr1.toString() + " THEN\n" + cpsCmd.toString() + "\n" + optElseCpsCmd.toString() + "\nENDIF";
        }
        else if (token.hasTerminal(WHILE)) {
            Parser.consume(WHILE);
            expr1 = new Expr();
            Parser.consume(DO);
            cpsCmd = new CpsCmd();
            Parser.consume(ENDWHILE);
            string = "WHILE " + expr1.toString() + " DO\n" + cpsCmd.toString() + "\nENDWHILE";
        }
        else if (token.hasTerminal(CALL)) {
            Parser.consume(CALL);
            identifier = Parser.consume(IDENT);
            exprList = new ExprList();
            optGlobInits = new OptGlobInits();
            string = "CALL " + identifier.toString() + exprList.toString() + "\n" + optGlobInits.toString();
        }
        else if (token.hasTerminal(DEBUGIN)) {
            Parser.consume(DEBUGIN);
            expr1 = new Expr();
            string = "DEBUGIN " + expr1.toString();
        }
        else if (token.hasTerminal(DEBUGOUT)) {
            Parser.consume(DEBUGOUT);
            expr1 = new Expr();
            string = "DEBUGOUT " + expr1.toString();
        }
        else {
            throw new GrammarError(token);
        }
    }

    @Override
    public String toString() {
        return string;
    }

    @Override
    public IAbstractNode toAbsSyn() {
        if (token.hasTerminal(SKIP)) {
            return new AbsSkipCommand();
        }
        else if (token.hasTerminal(ARRLEN, LPAREN, MONOPR, IDENT, LITERAL)) {
            return new AbsAssignmentCommand(((IToAbsNode)expr1).toAbsSyn(), ((IToAbsNode)expr2).toAbsSyn());
        }
        else if (token.hasTerminal(IF)) {
            return new AbsConditionalCommand(((IToAbsNode)expr1).toAbsSyn(), ((IToAbsNodeList)cpsCmd).toAbsSyn(), ((IToAbsNodeList)optElseCpsCmd).toAbsSyn());
        }
        else if (token.hasTerminal(WHILE)) {
            return new AbsWhileCommand(((IToAbsNode)expr1).toAbsSyn(), ((IToAbsNodeList)cpsCmd).toAbsSyn());
        }
        else if (token.hasTerminal(CALL)) {
            return new AbsCallCommand(identifier.getValue(), ((IToAbsNodeList)exprList).toAbsSyn(), ((IToAbsNodeList)optGlobInits).toAbsSyn());
        }
        else if (token.hasTerminal(DEBUGIN)) {
            return new AbsInputCommand(((IToAbsNode)expr1).toAbsSyn());
        }
        else {
            return new AbsOutputCommand(((IToAbsNode)expr1).toAbsSyn());
        }
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
