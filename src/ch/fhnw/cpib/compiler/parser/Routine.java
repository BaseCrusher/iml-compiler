package ch.fhnw.cpib.compiler.parser;

import java.util.Map;

import ch.fhnw.cpib.compiler.tokens.enums.types.Types;

public class Routine {
    private String name;
    private Map<String, Variable> params;
    private Types returnType;

    public Routine(String name, Map<String, Variable> params, Types returnType) {
        this.name = name;
        this.params = params;
        this.returnType = returnType;
    }
}
