package IR;

import symboltable.SymbolTable;
import symboltable.SymbolTableVariable;

/**
 * Created by Arash on 18/06/28.
 */
public class AssignmentIR implements Quadruple {
    public String operator;
    public SymbolTableVariable argument1;
    public SymbolTableVariable argument2;
    public SymbolTableVariable result;
    public AssignmentIR(String operator, SymbolTableVariable argument1, SymbolTableVariable argument2, SymbolTableVariable result) {
        this.operator = operator;
        this.argument1 = argument1;
        this.argument2 = argument2;
        this.result = result;
    }
}

