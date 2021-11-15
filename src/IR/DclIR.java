package IR;

import symboltable.Scope;
import syntaxtree.Var_Dcl;
// in codeGeneration v.expr shouldn't be used
//@dd = common global %struct.date zeroinitializer, align 4
//variable name can be found from
//name of variable can be found from scope.lookupVariable(v.variable.identifier)
public class DclIR {
    public Var_Dcl v;
    public Scope scope;
    public DclIR(Var_Dcl v,Scope scope) {
        this.scope = scope;
        this.v = v;
       // scope.lookupVariable(v.variable.identifier.toString()).

    }
}
