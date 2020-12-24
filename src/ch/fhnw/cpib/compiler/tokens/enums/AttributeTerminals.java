package ch.fhnw.cpib.compiler.tokens.enums;

public enum AttributeTerminals implements ITerminal {

    // Literal
    LITERAL,

    // Identifier
    IDENT,

    // Operators
    RELOPR,
    MONOPR,
    BOOLOPR,
    MULTOPR,
    DIVOPR,
    ADDOPR,

    // Type
    TYPE,

    // Modes
    CHANGEMODE,
    FLOWMODE,
    MECHMODE
}
