package ch.fhnw.cpib.compiler.tokens.enums.types;

public class Types {
    public static IType getByName(String name){
        IType type = null;
        type = BoolTypes.getByName(name);
        if (type != null)
            return type;
        type = Inttypes.getByName(name);
        return type;
    }

}
