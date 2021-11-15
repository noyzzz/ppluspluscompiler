package IR;

import symboltable.SymbolTable;
import symboltable.SymbolTableVariable;

public class RetIR implements Quadruple{ // produces ret instruction in llvm
    public SymbolTableVariable toReturn;
    public RetIR(SymbolTableVariable toReturn){
        this.toReturn = toReturn;
    }
}
