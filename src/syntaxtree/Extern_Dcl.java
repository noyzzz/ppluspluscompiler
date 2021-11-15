package syntaxtree;

public class Extern_Dcl {
    Type type;
    Identifier identifier;
    public Extern_Dcl(Type type, Identifier identifier){
        this.type = type;
        this.identifier = identifier;
    }
}
