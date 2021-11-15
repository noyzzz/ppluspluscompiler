package IR;

import syntaxtree.Identifier;
import syntaxtree.Var_Dcls;
public class StructIR {
    /*
    here we should define the struct
    %struct.date = type { i32, i32, i32 }
    %struct.Employee = type { [20 x i8], i32, float, %struct.date }
    // this is for when we have struct type argument
    */

    public Identifier identifier;
    public Var_Dcls var_dcls;
    public StructIR(Identifier identifier, Var_Dcls var_dcls) {
        this.identifier = identifier;
        this.var_dcls = var_dcls;
    }
}
