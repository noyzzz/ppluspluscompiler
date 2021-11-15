package symboltable;

import syntaxtree.Array_Index_Recursive;
import syntaxtree.Idarray;
import syntaxtree.Identifier;
import syntaxtree.Type;

public class SymbolTableVariable {
    public Identifier name;
    public Type type;
    public Array_Index_Recursive array_index_recursive;
    public Idarray idarray;
    public boolean isConst;
    public Object value;
    public boolean isLoaded;
    public SymbolTableVariable(Identifier name, Type type, Array_Index_Recursive array_index_recursive, Idarray idarray, boolean isConst){
        this.name = name;
        this.type = type;
        this.array_index_recursive = array_index_recursive;
        this.isConst = isConst;
        this.idarray = idarray;
        isLoaded =false;

    }
    public SymbolTableVariable(boolean isLoaded, Identifier name, Type type, Array_Index_Recursive array_index_recursive, Idarray idarray, boolean isConst){
        this(name,type,array_index_recursive,idarray,isConst);
        this.isLoaded = isLoaded;


    }
}
