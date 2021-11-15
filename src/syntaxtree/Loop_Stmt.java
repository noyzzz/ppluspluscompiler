package syntaxtree;

public class Loop_Stmt {
    public enum LoopType{
        FOR,REPEAT,FOREACH;
    }
    public LoopType loopType;
    public Assignment assignment;
    public Expr expr;
    public Object assignment_expr;
    public Block forBlock;
    public Block repeatBlock;
    public Expr repeatExpr;
    public Identifier foreachItem;
    public Identifier foreachSetIdentifier;
    public Block foreachBlock;
    public Loop_Stmt(LoopType loopType, Assignment a,
                     Expr expr, Object assignment_expr, Block forBlock){
        this.loopType = loopType;
        this.assignment = a;
        this.expr = expr;
        this.assignment_expr = assignment_expr;
        this.forBlock = forBlock;
    }
    public Loop_Stmt(LoopType loopType, Block repeatBlock, Expr repeatExpr){
        this.loopType = loopType;
        this.repeatBlock = repeatBlock;
        this.repeatExpr = repeatExpr;
    }
    public Loop_Stmt(LoopType loopType, Identifier foreachItem,
              Identifier foreachSetIdentifier, Block foreachBlock){
        this.loopType = loopType;
        this.foreachItem = foreachItem;
        this.foreachSetIdentifier = foreachSetIdentifier;
        this.foreachBlock = foreachBlock;
    }
}
