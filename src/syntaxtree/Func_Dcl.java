package syntaxtree;

import symboltable.SymbolTable;
import symboltable.SymbolTableVariable;

public class Func_Dcl {
    public Type type;
    public Identifier identifier;
    public Arguments arguments;
    public Block block;
    public SymbolTableVariable returnVariable;
    public Func_Dcl(Type type, Identifier identifier, Arguments arguments, Block block){
        returnVariable = new SymbolTableVariable(new Identifier(new NameGenerator().toString()),
                type, null, null, false);

        this.type = type;
        this.identifier = identifier;
        this.arguments = arguments;
        this.block = block;
    }
    public Func_Dcl(Type type, Identifier identifier){
        this(type, identifier,null,null);
    }
    public Func_Dcl(Type type, Identifier identifier, Arguments arguments){
        this(type,identifier,arguments,null);
    }
    public Func_Dcl(Type type, Identifier identifier, Block block){
        this(type,identifier,null, block);
    }
    public String getHash(){
        StringBuilder name = new StringBuilder(identifier.id);
        if(arguments!=null)
            name.append(arguments.getHash());
        return name.toString();
    }
}
