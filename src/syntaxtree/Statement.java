package syntaxtree;

public class Statement {
    public enum StatementEnum{
        ASSIGNMENT, METHOD_CALL, COND_STMT,
        LOOP_STMT, RETURN_VOID, BREAK, CONTINUE,RETURN_EXP
    }
    public StatementEnum statementType;
    public Assignment assignment;
    public Method_Call method_call;
    public Cond_Stmt cond_stmt;
    public Loop_Stmt loop_stmt;
    public Expr expr;
    public Type type;
    public Statement(StatementEnum statementType, Assignment assignment){
        this.statementType = statementType;
        this.assignment = assignment;
    }
    public Statement(StatementEnum statementType, Method_Call method_call){
        this.statementType = statementType;
        this.method_call = method_call;
    }
    public Statement(StatementEnum statementType, Cond_Stmt cond_stmt){
        this.statementType = statementType;
        this.cond_stmt = cond_stmt;
    }
    public Statement(StatementEnum statementType, Loop_Stmt loop_stmt){
        this.statementType = statementType;
        this.loop_stmt = loop_stmt;
    }
    public Statement(StatementEnum statementType){
        this.statementType = statementType;
    }
    public Statement(StatementEnum statementType, Expr expr){
        this.statementType = statementType;
        this.expr = expr;
    }
    public Statement(StatementEnum statementType, Type type){
        this.statementType = statementType;
        this.type = type;
    }

}
