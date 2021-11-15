package syntaxtree;
import java.util.*;
public class Array_Index_Recursive {
    public Stack<Expr> exprStack;
    public Array_Index_Recursive(Expr expr){
        exprStack = new Stack<Expr>();
        exprStack.push(expr);
    }
    public Array_Index_Recursive(Expr expr, Array_Index_Recursive array_index_recursive){
        array_index_recursive.exprStack.push(expr);
        this.exprStack = array_index_recursive.exprStack;
    }

}
