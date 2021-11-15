package IR;

import symboltable.SymbolTable;
import symboltable.SymbolTableVariable;

public class ReturnIR implements Quadruple { // this is NOT supposed to make ret instruction
    public boolean isVoid;
    public String returnLabel; // it should go to the line after this line if any exist
    public SymbolTableVariable methodReturnVariable;
    public SymbolTableVariable toBeReturnedExp;
    public ReturnIR(boolean isVoid,  SymbolTableVariable methodReturnVariable, SymbolTableVariable toBeReturnedExp) {
        this.isVoid = isVoid;
        this.methodReturnVariable = methodReturnVariable;
        this.toBeReturnedExp = toBeReturnedExp;
    }

}
