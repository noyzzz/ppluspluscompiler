package syntaxtree;

public class Var_Dcl {
    public boolean isConst;
    public Type type;
    public Variable variable;
    public Expr expr;
    public Var_Dcl(Variable variable, Expr expr){
        this.variable = variable;
        this.expr = expr;
    }
    public Var_Dcl(Variable variable){
        this(variable, null);
    }
}
