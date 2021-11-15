package syntaxtree;

public class Assignment {
    public Variable variable;
    public Expr expr;
    public Assignment(Variable variable, Expr expr){
        this.variable = variable;
        this.expr = expr;
    }
}
