package ch.fhnw.cpib.compiler.parser;

import java.util.Map;

import ch.fhnw.cpib.compiler.tokens.enums.types.IType;

public class Routine {
    private final String name;
    private final Map<String, Variable> params;
    private final IType returnType;

    public Routine(String name, Map<String, Variable> params, IType returnType) {
        this.name = name;
        this.params = params;
        this.returnType = returnType;
    }

    public String getName() {
        return name;
    }

    public Map<String, Variable> getParams() {
        return params;
    }

    public IType getReturnType() {
        return returnType;
    }
}
