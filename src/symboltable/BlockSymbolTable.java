package symboltable;

import syntaxtree.*;

import java.util.Hashtable;

public class BlockSymbolTable extends Scope {
    Scope parent;
    public static SymbolTable root;
    Hashtable<String, BlockSymbolTable> blocks;
    public BlockSymbolTable(Scope parent){
        vars = new Hashtable<>();
        this.parent = parent;
        vars = new Hashtable<>();
        this.blocks = new Hashtable<>();
    }
    @Override
    public Scope enterScope(String name) {
        return this.blocks.get(name);
    }

    @Override
    public SymbolTableVariable lookupVariable(String name) {
        SymbolTableVariable symbolTableVariable = this.vars.get(name);
        if (symbolTableVariable == null)
            symbolTableVariable = this.parent.lookupVariable(name);
        return symbolTableVariable;
    }

    @Override
    public MethodSymbolTable lookupMethod(Identifier identifier, Type type, Arguments arguments) {
        return root.lookupMethod(identifier, type, arguments);
    }

    @Override
    public Scope exitScope() {
        return this.parent;
    }

    @Override
    public void print(int indentLevel) {

    }

    @Override
    public MethodSymbolTable lookupMethod(String hash) {
        return  root.lookupMethod(hash);
    }


    public void addBlock(String identifier){
        this.blocks.put(identifier, new BlockSymbolTable(this));
    }

}

