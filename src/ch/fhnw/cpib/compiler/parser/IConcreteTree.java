package ch.fhnw.cpib.compiler.parser;

import ch.fhnw.cpib.compiler.parser.nts.Program;

public interface IConcreteTree {
    Program getProgram();
    void setProgram(Program program);
}
