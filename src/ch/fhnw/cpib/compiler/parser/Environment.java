package ch.fhnw.cpib.compiler.parser;

import java.util.HashMap;
import java.util.Map;

import ch.fhnw.cpib.compiler.error.DuplicateDeclaratoinError;
import ch.fhnw.cpib.compiler.error.GrammarError;

public class Environment {
    private Map<String, Variable> vars;
    private Map<String, Routine> routines;
    private Environment parent;
    private int startAddress;

    public Environment(Environment parent, int startAddress) {
        this.vars = new HashMap<>();
        this.routines = new HashMap<>();
        this.parent = parent;
        this.startAddress = startAddress;
    }

    public void putVariable(String ident, Variable variable) {
        if (vars.containsKey(ident)) {
            try {
                throw new DuplicateDeclaratoinError(ident + " already defined.");
            } catch (DuplicateDeclaratoinError duplicateDeclaratoinError) {
                duplicateDeclaratoinError.printStackTrace();
            }
        }
        vars.put(ident, variable);
    }

    public Variable getVariable(String varKey) {
        Variable variable = vars.get(varKey);
        if (variable == null && parent != null) {
            return parent.getVariable(varKey);
        }
        return variable;
    }

    public Map<String, Variable> getVars() {
        return vars;
    }

    public Map<String, Routine> getRoutines() {
        return routines;
    }

    public Environment getParent() {
        return parent;
    }

    public int getStartAddress() {
        return startAddress;
    }

    public int getAbsoluteAddress() {
        // global
        if (parent == null) {
            return startAddress;
        }
        return startAddress + parent.getAbsoluteAddress();
    }

    public Routine getRoutine(String routineKey) {
        Routine routine = routines.get(routineKey);
        if (routine == null && parent != null) {
            return parent.getRoutine(routineKey);
        }
        return routine;
    }
}