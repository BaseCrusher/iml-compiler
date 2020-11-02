package ch.fhnw.cpib.compiler.parser;

import ch.fhnw.cpib.compiler.parser.nts.Program;

public class ConcreteTree implements IConcreteTree {
    private Program program;

    public Program getProgram() {
        return program;
    }

    public void setProgram(Program program) {
        this.program = program;
    }

    @Override
    public String toString() {
        return "ConcreteTree: \n" + program.toString();
    }
}
