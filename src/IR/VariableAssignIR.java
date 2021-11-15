package IR;

import symboltable.Scope;
import symboltable.SymbolTableVariable;
import syntaxtree.Variable;

// result is a always a user defined variable
public class VariableAssignIR implements Quadruple {
    public Variable resultVariable;//result s global
    public SymbolTableVariable exprSymbol;//store this shit to the @result

    public Scope currentScope;
    public VariableAssignIR(SymbolTableVariable exprSymbol, Variable resultVariable,Scope currentScope) {
        this.resultVariable = resultVariable;
        this.exprSymbol = exprSymbol;
        this.currentScope = currentScope;

    }
}
