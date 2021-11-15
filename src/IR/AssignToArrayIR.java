package IR;

import symboltable.SymbolTable;
import symboltable.SymbolTableVariable;
import syntaxtree.Expr;

import java.util.Stack;

public class AssignToArrayIR implements Quadruple{// assigns an array to a variable
    public String operator;
    public SymbolTableVariable arrayId;//id global
    public Stack<Expr> indexes;// each has a result which is SymbolTableVariable
    public SymbolTableVariable result;
    public AssignToArrayIR(String operator, SymbolTableVariable arrayId, Stack<Expr> indexes, SymbolTableVariable result){
        this.operator = operator;
        this.arrayId = arrayId;
        this.indexes = indexes;
        this.result = result;
    }
}
