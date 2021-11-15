package symboltable;

import syntaxtree.*;

import java.util.Hashtable;

public abstract class Scope
{
	Hashtable<String,SymbolTableVariable> vars;
	public abstract Scope enterScope(String name);
	public abstract SymbolTableVariable lookupVariable(String name);
	public abstract MethodSymbolTable lookupMethod(Identifier identifier, Type type, Arguments arguments);
	public abstract Scope exitScope();
	public abstract void print(int indentLevel);
	public abstract MethodSymbolTable lookupMethod(String hash);
	public void addVariable(Var_Dcl var_dcl){
		SymbolTableVariable symbolTableVariable =
				new SymbolTableVariable(new Identifier(new NameGenerator().toString()), var_dcl.type, var_dcl.variable.array_index_recursive, null, var_dcl.isConst);
		vars.put(var_dcl.variable.identifier.toString(), symbolTableVariable);
	}

}
