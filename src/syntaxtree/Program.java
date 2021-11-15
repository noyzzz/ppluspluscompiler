package syntaxtree;

public class Program {
    public Var_Dcls var_dcls;
    public Func_Extern func_extern;
    public Struct_Dec struct_dec;
    public Program program;
    public Program(Var_Dcls var_dcls,Program program){
       this.var_dcls = var_dcls;
        this.program = program;
    }
    public Program(Var_Dcls var_dcls){
        this(var_dcls,null);
    }
    public Program(Func_Extern func_extern, Program program){
        this.func_extern = func_extern;
        this.program = program;
    }
    public Program(Func_Extern func_extern){
        this(func_extern, null);
    }
    public Program(Struct_Dec struct_dec, Program program){
        this.struct_dec = struct_dec;
        this.program = program;
    }
    public Program(Struct_Dec struct_dcl){
        this(struct_dcl,null);
    }
}
