package syntaxtree;

import symboltable.SymbolTableVariable;

public class Type {
    public enum TypeEnum{
        INT, BOOL, FLOAT, LONG, CHAR, DOUBLE, STRING, VOID, AUTO, ID
    }
    public String getLLVMName(){
        switch (typeEnum){

            case INT:
                return  "i32";

            case BOOL:
                return  "i8";
            case FLOAT:
                return  "double";
            case LONG:
                return  "i64";
            case CHAR:
                return  "i8";
            case DOUBLE:
                return  "double";
            case STRING:
                return "String";
            case VOID:
                return "void";
            case AUTO:
                break;
            case ID:
                return identifier.id;
        }
        return null;
    }
    boolean isArray = false;
    public TypeEnum typeEnum;
    public Identifier identifier;
    public int bracCount;
    public SymbolTableVariable result;
    public Type(TypeEnum typeEnum ){
        this.typeEnum = typeEnum;
    }
    public Type(TypeEnum typeEnum, Identifier identifier ){
        this(typeEnum);
        this.identifier = identifier;
    }

    public Type(TypeEnum typeEnum, boolean isArray, Identifier identifier, int bracCount) {
        this.typeEnum = typeEnum;
        this.identifier = identifier;
        this.bracCount = bracCount;
        this.isArray = isArray;
    }
    public String getHash(){
        if(identifier!=null){
            return new String(identifier.getId());
        }
        return typeEnum.toString();
    }
    public SymbolTableVariable generateResult(){
        return result = new SymbolTableVariable(null,this,null,null,false);
        // TODO: 18/07/04 visit type
    }
}
