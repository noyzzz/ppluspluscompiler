package syntaxtree;

public class Conditional {
    public enum ConditionalType{
        ISEQUAL, NOTEQUAL, GEQ, LEQ, LESS, GREATER, AND, OR, NOT
    }
    public ConditionalType conditionalType;
    public Conditional(ConditionalType conditionalType){
        this.conditionalType = conditionalType;
    }
}
