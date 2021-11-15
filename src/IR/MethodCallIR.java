package IR;

import symboltable.MethodSymbolTable;
import symboltable.SymbolTableVariable;
import syntaxtree.Expr;

import java.util.Stack;

public class MethodCallIR implements Quadruple { // calling method
    public MethodSymbolTable methodSymbolTable;
    public Stack<Expr> parameterStack;
    public String hash;
    public SymbolTableVariable resultVariable;
    public MethodCallIR(String hash, MethodSymbolTable methodSymbolTable, Stack<Expr> parameterStack, SymbolTableVariable resultVariable) {
        this.methodSymbolTable = methodSymbolTable;
        this.parameterStack = parameterStack;
        this.resultVariable = resultVariable;
        this.hash = hash;
    }
}
