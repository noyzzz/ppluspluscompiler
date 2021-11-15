package syntaxtree;

public class Cond_Stmt {
    public Expr ifExpr;
    public Block ifBlock;
    public Block elseBlock;
    public Block defaultCaseBlock;
    public Identifier switch_id;
    public Switch_Case_Cases switch_case_cases;
    public enum Cond_Stmt_enum{
        IF, IFELSE, DEFAULT_SWITCH, SWITCH
    }
    public Cond_Stmt_enum cond_stmt_enum;
    public Cond_Stmt(Cond_Stmt_enum cond_stmt_enum, Expr expr, Block block){
        this.ifExpr = expr;
        this.ifBlock = block;
        this.cond_stmt_enum = cond_stmt_enum;
    }
    public Cond_Stmt(Cond_Stmt_enum cond_stmt_enum, Expr expr, Block ifBlock, Block elseBlock){
        this(cond_stmt_enum, expr,ifBlock);
        this.elseBlock = elseBlock;
    }
    public Cond_Stmt(Cond_Stmt_enum cond_stmt_enum, Identifier switch_id, Block defaultCaseBlock){
        this.switch_id = switch_id;
        this.defaultCaseBlock = defaultCaseBlock;
        this.cond_stmt_enum = cond_stmt_enum;
    }
    public Cond_Stmt(Cond_Stmt_enum cond_stmt_enum, Identifier switch_id, Switch_Case_Cases switch_case_cases, Block defaultCaseBlock){
        this.switch_id = switch_id;
        this.switch_case_cases = switch_case_cases;
        this.defaultCaseBlock = defaultCaseBlock;
        this.cond_stmt_enum = cond_stmt_enum;
    }
}
