package symboltable;

import syntaxtree.*;

import java.util.Hashtable;
import java.util.LinkedHashMap;

public class MethodSymbolTable extends BlockSymbolTable {
    Identifier identifier;
    public LinkedHashMap<String, SymbolTableVariable> args;
    public Type returnType;

    public MethodSymbolTable(SymbolTable parent, Identifier identifier, Type type, Arguments arguments) {
        super(parent);
        this.identifier = identifier;
        this.returnType = type;
        this.args = decodeArgs(arguments);

    }

    @Override
    public Scope enterScope(String name) {
        return blocks.get(name);
    }

    @Override
    public SymbolTableVariable lookupVariable(String name) {

        SymbolTableVariable symbolTableVariable= this.args.get(name);
        if(symbolTableVariable ==null)
            symbolTableVariable = this.vars.get(name);
        if (symbolTableVariable == null)
            return super.lookupVariable(name);
        return symbolTableVariable;
    }


    @Override
    public Scope exitScope() {
        return root;
    }

    @Override
    public void print(int indentLevel) {

    }

    LinkedHashMap<String, SymbolTableVariable> decodeArgs(Arguments arguments) {
        LinkedHashMap<String, SymbolTableVariable> args = new LinkedHashMap<>();
        while (arguments != null) {
            SymbolTableVariable symbolTableVariable =
                    new SymbolTableVariable(true,new Identifier(new NameGenerator().toString()), arguments.type, null, arguments.idarray, false);
            args.put(arguments.identifier.toString(), symbolTableVariable);
            arguments = arguments.arguments;
        }
        return args;
    }
}
