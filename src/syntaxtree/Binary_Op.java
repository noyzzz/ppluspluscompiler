package syntaxtree;

public class Binary_Op {
    public Arithmatic arithmatic;
    public Conditional conditional;
    public Binary_Op(Conditional conditional){
        this.conditional = conditional;
    }
    public Binary_Op(Arithmatic arithmatic){
        this.arithmatic = arithmatic;
    }
}
