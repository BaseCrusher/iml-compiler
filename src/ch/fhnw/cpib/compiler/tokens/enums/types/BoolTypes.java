package ch.fhnw.cpib.compiler.tokens.enums.types;

public enum BoolTypes implements IType {
    BOOL;

    public static IType getByName(String name){
        BoolTypes mode = null;
        try {
            mode = BoolTypes.valueOf(name);
        }catch (IllegalArgumentException ignored){}
        return mode;
    }
}
