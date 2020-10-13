package ch.fhnw.cpib.compiler.tokens.enums.modes;

public enum FlowModes implements IFlowMode {
    
    IN,
    OUT,
    INOUT;

    public static IFlowMode getByName(String name){
        FlowModes mode = null;
        try {
            mode = FlowModes.valueOf(name);
        }catch (IllegalArgumentException ignored){}
        return mode;
    }
}
