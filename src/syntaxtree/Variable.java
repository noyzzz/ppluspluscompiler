package syntaxtree;

import symboltable.*;

/*
* if array_index_recursive is null then identifier is not an array;
*
* */
public class Variable {
    public enum VariableType{
        IDENTIFIER,ARRAY_IDENTIFIER, TILDE_VARIABLE, MINUSMINUS_VARIABLE, PLUSPLUS_VARIABLE,
        VARIABLE_MINUSMINUS, VARIABLE_PLUSPLUS, RECORD_ITEM;
    }
    public VariableType variableType;
    public Identifier identifier;
    public Variable variable;
    public Array_Index_Recursive array_index_recursive;
    public SymbolTableVariable result;
    public Variable(VariableType variableType, Identifier identifier){
        this.variableType = variableType;
        this.identifier = identifier;
    }
    public Variable(VariableType variableType,
             Identifier identifier, Array_Index_Recursive array_index_recursive){
        this.variableType = variableType;
        this.identifier = identifier;
        this.array_index_recursive = array_index_recursive;

    }
    public Variable(VariableType variableType, Variable variable){
        this.variableType = variableType;
        this.variable = variable;
    }

    public Variable(VariableType variableType, Variable variable, Identifier identifier) {
        this.variableType = variableType;
        this.identifier = identifier;
        this.variable = variable;
    }
    public SymbolTableVariable generateResult(Scope scope){
        if (result != null)
            return result;
        Type type = null;
        switch (variableType) {
            case IDENTIFIER:
                type = identifier.getResult(scope).type;
                break;
            case ARRAY_IDENTIFIER: {
                type = new Type(identifier.getResult(scope).type.typeEnum,true, identifier, array_index_recursive.exprStack.size());
                break;
            }
            case TILDE_VARIABLE: {
                type = variable.result.type;
                break;
            }
            case MINUSMINUS_VARIABLE: {
                type = variable.result.type;
                break;
            }
            case PLUSPLUS_VARIABLE: {
                type = variable.result.type;
                break;
            }
            case VARIABLE_MINUSMINUS: {
                type = variable.result.type;
                break;
            }
            case VARIABLE_PLUSPLUS: {
                type = variable.result.type;
                break;
            }
            case RECORD_ITEM: {
                Identifier recName ;
                Variable v = variable;
                while(v.variable!=null)
                    v = v.variable;
                RecordSymbolTable recordSymbolTable = ((SymbolTable) BlockSymbolTable.root).getRecordSymbolTable(scope.lookupVariable(v.identifier.toString()).type.identifier.toString());
                type = recordSymbolTable.argumentType.get(identifier.toString());
                break;
            }
        }
        return result = new SymbolTableVariable(new Identifier(new NameGenerator().toString()), type, null, null, false);

    }
}
