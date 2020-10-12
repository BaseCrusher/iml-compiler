package ch.fhnw.cpib.compiler.tokens.enums;

import ch.fhnw.cpib.compiler.tokens.enums.modes.ChangeModes;

public class Types implements IType {

    public static final String BOOL = "BOOL";
    public static final Inttypes[] INTTYPE =  Inttypes.values();

    String type;

    private Types(String type){
        this.type = type;
    }

    @Override
    public String toString(){
        return type;
    }

    public static IType getByName(String name){
        Types type = null;
        if (name.equals(BOOL))
            type = new Types(name);
        IInttype iType = Inttypes.getByName(name);
        if(iType != null)
            type = new Types(iType.toString());
        return type;
    }

}
