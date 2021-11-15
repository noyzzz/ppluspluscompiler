package syntaxtree;

import symboltable.Scope;
import symboltable.SymbolTable;
import symboltable.SymbolTableVariable;

public class Identifier {
    public String id;
    public Identifier(String id){
        this.id = id;
    }
    @Override
    public String toString(){
        return id;
    }

    public SymbolTableVariable getResult(Scope scope) {
        return scope.lookupVariable(id);
    }
    //identifier doesn't have generateResult because it has been declared before
    // we should just lookup the variable in the given scope

    public String getId() {
        return id;
    }
}
