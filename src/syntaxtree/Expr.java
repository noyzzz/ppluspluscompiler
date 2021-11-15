package syntaxtree;

import symboltable.SymbolTable;
import symboltable.SymbolTableVariable;

public class Expr {
    public Expr(ExprType sizeof, Type t) {
        this.exprType = sizeof;
        this.type = t;
    }

    public enum ExprType{
        BINARY_EXPR, METHOD_CALL, VARIABLE, CONST_VAL, EXPR, UMINUS, TILDE, SIZEOF
    }
    public ExprType exprType;
    public Expr binaryExprLeft;
    public Type type;
    public Binary_Op binary_op;
    public Expr binaryExprRight;
    public Expr expr;
    public Method_Call method_call;
    public Const_Val const_val;
    public Variable variable;
    public SymbolTableVariable result;
    public Expr(ExprType exprType, Expr binaryExprLeft, Binary_Op binary_op, Expr binaryExprRight){
        this.exprType = exprType;
        this.binaryExprLeft = binaryExprLeft;
        this.binaryExprRight = binaryExprRight;
        this.binary_op = binary_op;
    }
    public Expr(ExprType exprType, Expr expr){
        this.exprType = exprType;
        this.expr = expr;
    }
    public Expr(ExprType exprType, Method_Call method_call){
        this.exprType = exprType;
        this.method_call = method_call;
    }
    public Expr(ExprType exprType, Variable variable){
        this.exprType = exprType;
        this.variable = variable;
    }
    public Expr(ExprType exprType, Const_Val const_val){
        this.exprType = exprType;
        this.const_val = const_val;
    }


    public SymbolTableVariable generateResult(){
        if (result != null)
            return result;
        Type type = null;
        switch (exprType) {
            case BINARY_EXPR: {
                if (binary_op.conditional == null)
                    type = binaryExprLeft.result.type;
                else {
                    type = new Type(Type.TypeEnum.BOOL);
                }
                break;
            }
            case METHOD_CALL: {
                type = method_call.result.type;
                break;
            }
            case VARIABLE: {
                type = variable.result.type;
                break;
            }
            case CONST_VAL:
                type = const_val.result.type;
                break;
            case EXPR:
                type = expr.result.type;
                break;
            case UMINUS:
                type = variable.result.type;
                break;
            case TILDE:
                type = variable.result.type;
                break;
            case SIZEOF:
                type = new Type(Type.TypeEnum.INT);
                break;
        }
          result = new SymbolTableVariable(new Identifier(new NameGenerator().toString()), type, null, null, false);
        if(exprType.equals(ExprType.CONST_VAL)){
            result.value = const_val.result.value;
        }
        return result;
    }


}
