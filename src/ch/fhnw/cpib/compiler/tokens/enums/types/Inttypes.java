package ch.fhnw.cpib.compiler.tokens.enums.types;

public enum Inttypes implements IType {
    INT32,
    INT64,
    INT1024;

    public static IType getByName(String name){
        Inttypes mode = null;
        try {
            mode = Inttypes.valueOf(name);
        }catch (IllegalArgumentException ignored){}
        return mode;
    }
}
