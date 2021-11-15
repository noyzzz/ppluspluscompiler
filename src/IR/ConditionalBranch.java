package IR;

import symboltable.SymbolTable;
import symboltable.SymbolTableVariable;

public class ConditionalBranch implements Quadruple {
    public SymbolTableVariable cond;
    public String ifTrue;
    public String ifFalse;
    public ConditionalBranch(SymbolTableVariable symbolTableVariable, String ifTrue, String ifFalse){
        this.cond = symbolTableVariable;
        this.ifFalse = ifFalse;
        this.ifTrue = ifTrue;
    }
}
