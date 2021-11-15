package visitor;

import syntaxtree.*;

/**
 * Created by Arash on 18/06/30.
 */
public interface Visitor {
    public void visit(Program n);

    public void visit(Arguments a);

    public void visit(Arithmatic a);

    public void visit(Array_Index_Recursive a);

    public void visit(Assignment a);

    public void visit(Binary_Op b);

    public void visit(Block b);

    public void visit(Brac b);

    public void visit(Cond_Stmt c);

    public void visit(Conditional c);

    public void visit(Const_Val c);

    public void visit(Expr e);

    public void visit(Extern_Dcl e);

    public void visit(Func_Dcl f);

    public void visit(Func_Extern f);

    public void visit(Idarray i);

    public void visit(Identifier i);

    public void visit(Loop_Stmt l);

    public void visit(Method_Call m);

    public void visit(Parameters p);

    public void visit(Statement s);

    public void visit(Struct_Dec s);

    public void visit(Switch_Case_Case s);

    public void visit(Switch_Case_Cases s);

    public void visit(Type t);

    public void visit(Var_Dcl v);

    public void visit(Var_Dcls v);

    public void visit(Variable v);


}