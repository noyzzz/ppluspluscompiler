package IR;

import symboltable.SymbolTable;
import symboltable.SymbolTableVariable;

public class getStructElementIR implements Quadruple {
    public SymbolTableVariable struct;
    public Integer argumentNumber;
    public SymbolTableVariable result;
// %2 = getelementptr inbounds %struct.Employee, %struct.Employee* %1, i32 0, i32 argumentNumber
//result should be assigned too
    public getStructElementIR(SymbolTableVariable struct, Integer argumentNumber, SymbolTableVariable result) {
        this.struct = struct;
        this.argumentNumber = argumentNumber;
        this.result = result;
    }
}
