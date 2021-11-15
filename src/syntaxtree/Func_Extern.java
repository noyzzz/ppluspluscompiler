package syntaxtree;

public class Func_Extern {
    public Func_Dcl func_dcl;
    public Extern_Dcl extern_dcl;
    public Func_Extern(Func_Dcl func_dcl){
        this.func_dcl = func_dcl;
    }
    public Func_Extern(Extern_Dcl extern_dcl){
        this.extern_dcl = extern_dcl;
    }

}
