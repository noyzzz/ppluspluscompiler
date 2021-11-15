package syntaxtree;

import symboltable.SymbolTable;
import symboltable.SymbolTableVariable;

public class Method_Call {
    public Identifier identifier;
    public Parameters parameters;
    public SymbolTableVariable result;
    public Method_Call(Identifier identifier, Parameters parameters){
        this.identifier = identifier;
        this.parameters = parameters;
    }
    public Method_Call(Identifier identifier){
        this.identifier = identifier;
    }

    public SymbolTableVariable generateResult(Type type) {
        if (result != null)
            return result;
        result = new SymbolTableVariable(new Identifier(new NameGenerator().toString()), type, null, null, false);
        return result;

    }
    public String getHash(){
        StringBuilder ret = new StringBuilder();
        ret.append(identifier);
        if(parameters!=null)
            ret.append(parameters.getHash());
        return ret.toString();
     /*   ret.append(type.getHash());
        ret.append(idarray.countBrackets);
        if(arguments!=null){
            ret.append(arguments.getHash());
        }*/
    }
}
