package IR;

import symboltable.SymbolTable;
import symboltable.SymbolTableVariable;

import java.util.HashMap;

public class SwitchIR  implements Quadruple{
    public SymbolTableVariable switch_id;
    public String defaultLabel;
    public HashMap<Integer,String> labels;
    public SwitchIR(SymbolTableVariable switch_id, String defaultLabel, HashMap<Integer,String> labels){
        this.switch_id = switch_id;
        this.labels = labels;
        this.defaultLabel = defaultLabel;
    }
}
