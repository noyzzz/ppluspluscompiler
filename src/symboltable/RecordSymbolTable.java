package symboltable;

import syntaxtree.*;

import java.util.HashMap;
import java.util.Hashtable;

/**
 * Created by Arash on 18/07/01.
 */
public class RecordSymbolTable  {
    public Identifier identifier;
    public static SymbolTable root;
    public HashMap<String,Integer> argumentNumber;
    public HashMap<String,Type> argumentType;
    public RecordSymbolTable(Identifier identifier,Var_Dcls var_dcls) {
        this.argumentNumber = new HashMap<>();
        this.argumentType = new HashMap<>();
        this.identifier = identifier;
        int i=0;
        for(Var_Dcl var_dcl : var_dcls.var_dclList){
            argumentNumber.put(var_dcl.variable.identifier.toString(),var_dcls.var_dclList.size()-i-1);
            argumentType.put(var_dcl.variable.identifier.toString(),var_dcl.type);
                    i++;
        }
    }



}
