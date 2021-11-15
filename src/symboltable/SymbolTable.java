package symboltable;

import syntaxtree.*;

import java.util.Hashtable;

public class SymbolTable extends Scope {
    Hashtable<String,RecordSymbolTable> records;
    Hashtable<String, MethodSymbolTable> methods;
    Hashtable<String, BlockSymbolTable> blocks;
//    Hashtable<String,ExternSymbolTable> externs;
public SymbolTable(){
    vars = new Hashtable<>();
    BlockSymbolTable.root = this;
    RecordSymbolTable.root = this;
        records = new Hashtable<String, RecordSymbolTable>();
        methods = new Hashtable<String, MethodSymbolTable>();
        blocks = new Hashtable<>();
    }
    @Override
    public Scope enterScope(String name) {
        /*RecordSymbolTable recordSymbolTable =
                records.get(name);
        if(recordSymbolTable!=null)
            return recordSymbolTable;*/
        MethodSymbolTable methodSymbolTable =
                methods.get(name);
        if(methodSymbolTable!=null)
            return methodSymbolTable;
//        ExternSymbolTable externSymbolTable =
//                externs.get(name);
//        return externSymbolTable;
        return null;

    }

    @Override
    public SymbolTableVariable lookupVariable(String name) {
        return vars.get(name);
    }

    @Override
    public MethodSymbolTable lookupMethod(Identifier identifier, Type type, Arguments arguments) {
        StringBuilder name = new StringBuilder(identifier.getId());
        name.append(arguments.getHash());
        return methods.get(name.toString());
    }


    @Override
    public Scope exitScope() {
        //nothing exits this
        return null;
    }

    @Override
    public void print(int indentLevel) {

    }

    @Override
    public MethodSymbolTable lookupMethod(String hash) {
        return methods.get(hash);
    }

    /*
    Type type;
    Identifier identifier;
    Arguments arguments;
    Block block;
     */
    public void addMethod(Type type, Identifier identifier, Arguments arguments){
        StringBuilder name = new StringBuilder(identifier.getId());
        if(arguments!=null)
            name.append(arguments.getHash());// this name is identified for each method
        methods.put(name.toString(),
                new MethodSymbolTable(this,new Identifier(name.toString()), type, arguments));
    }
    public Hashtable<String,MethodSymbolTable> getMethods(){return this.methods;}
    public void addRecord(Identifier identifier, Var_Dcls var_dcls){

        this.records.put(identifier.toString(), new RecordSymbolTable(identifier,var_dcls));
    }
    public RecordSymbolTable getRecordSymbolTable(String name){
        return records.get(name);

    }
    public void addBlock(String identifier){
        this.blocks.put(identifier, new BlockSymbolTable(this));
    }

}
