package IR;

import syntaxtree.Func_Dcl;

public class GenerateMethodSignature implements Quadruple {
    public Func_Dcl func_dcl;
    public GenerateMethodSignature(Func_Dcl f) {
        this.func_dcl = f;
    }
}
