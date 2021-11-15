package IR;

import symboltable.SymbolTableVariable;

public class ArithmaticLocalIR implements Quadruple {
    public SymbolTableVariable result;
    public String operator;
    public ArithmaticLocalIR(String operator, SymbolTableVariable result) {
        this.result = result;
        this.operator = operator;
    }
}
