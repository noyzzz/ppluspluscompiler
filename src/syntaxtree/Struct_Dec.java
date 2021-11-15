package syntaxtree;

public class Struct_Dec {
    public Identifier identifier;
    public Var_Dcls var_dcls;
    public Struct_Dec(Identifier identifier, Var_Dcls var_dcls){
        this.identifier = identifier;
        this.var_dcls = var_dcls;
    }

}
