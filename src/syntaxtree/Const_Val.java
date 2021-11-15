package syntaxtree;

import symboltable.SymbolTableVariable;

public class Const_Val {
    public Object const_val;
    public SymbolTableVariable result;

    public Const_Val(Object const_val) {
        this.const_val = const_val;
    }

    public SymbolTableVariable generateResult() {
        Type type = null;
        if (const_val instanceof Integer) {
            type = new Type(Type.TypeEnum.INT);
        }
        if (const_val instanceof Double) {
            type = new Type(Type.TypeEnum.DOUBLE);
        }
        if (const_val instanceof Boolean) {
            type = new Type(Type.TypeEnum.BOOL);
        }
        if (const_val instanceof String) {
            type = new Type(Type.TypeEnum.STRING);
        }
        if (const_val instanceof Character) {
            type = new Type(Type.TypeEnum.CHAR);
        }
        if (const_val instanceof Long) {
            type = new Type(Type.TypeEnum.LONG);

        }
        result = new SymbolTableVariable(new Identifier(new NameGenerator().toString()), type, null, null, false);

        result.value = const_val;
        return result;
    }
}
