package IR;

import symboltable.Scope;
import symboltable.SymbolTable;
import symboltable.SymbolTableVariable;
import syntaxtree.Variable;

public class AssignFromRecord implements Quadruple {
    public Variable variable;
    public SymbolTableVariable symbolTableVariable;
    public Scope currentScope;
    public AssignFromRecord(Variable variable, SymbolTableVariable symbolTableVariable,Scope currentScope) {

        this.variable = variable;
        this.symbolTableVariable = symbolTableVariable;
        this.currentScope = currentScope;
    }
}
