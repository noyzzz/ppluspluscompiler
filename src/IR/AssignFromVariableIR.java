package IR;

import symboltable.SymbolTableVariable;

// result is not global but globalIdentifier is
public class AssignFromVariableIR implements Quadruple {
    public SymbolTableVariable result;
    public SymbolTableVariable globalIdentifier;
    public AssignFromVariableIR(SymbolTableVariable globalIdentifier, SymbolTableVariable result) {
        this.result = result;
        this.globalIdentifier = globalIdentifier;
    }
}
