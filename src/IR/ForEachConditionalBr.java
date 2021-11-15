package IR;

import symboltable.Scope;
import symboltable.SymbolTableVariable;
import syntaxtree.Identifier;

public class ForEachConditionalBr implements Quadruple {
    public Identifier foreachItem;
    public Identifier foreachSetIdentifier;
    public Scope currentScope;
    public String startBlockLabel;
    public String afterLoopLabel;
    public SymbolTableVariable countLoop;
    public ForEachConditionalBr(SymbolTableVariable countLoop, Identifier foreachItem, Identifier foreachSetIdentifier, Scope currentScope, String startBlockLabel, String afterLoopLabel) {
        this.countLoop = countLoop;
        this.foreachItem = foreachItem;
        this.foreachSetIdentifier = foreachSetIdentifier;
        this.currentScope = currentScope;
        this.startBlockLabel = startBlockLabel;
        this.afterLoopLabel = afterLoopLabel;
    }
}
