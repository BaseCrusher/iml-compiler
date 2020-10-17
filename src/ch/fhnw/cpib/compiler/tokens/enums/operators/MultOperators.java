package ch.fhnw.cpib.compiler.tokens.enums.operators;

import ch.fhnw.cpib.compiler.tokens.enums.dictionary.Symbols;

public enum MultOperators implements IMultOperator {
    TIMES,
    DIV_E,
    MOD_E,
    DIV_F,
    MOD_F,
    DIV_T,
    MOD_T;

    public static MultOperators getByName(String name){
        MultOperators operator = null;
        switch(name){
            case "divE":
                operator = MultOperators.DIV_E;
            case "modE":
                operator = MultOperators.MOD_E;
            case "divF":
                operator = MultOperators.DIV_F;
            case "modF":
                operator = MultOperators.MOD_F;
            case "divT":
                operator = MultOperators.DIV_T;
            case "modT":
                operator = MultOperators.MOD_T;
        }
        return operator;
    }

    public static MultOperators contains(Symbols symbol){
        if (symbol == Symbols.TIMES)
            return MultOperators.TIMES;
        return null;
    }
    
}
