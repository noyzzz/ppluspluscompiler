package syntaxtree;

public class Arguments {
    public Type type;
    public Identifier identifier;
    public Idarray idarray;
    public Arguments arguments;

    public Arguments(Type type, Identifier identifier, Idarray idarray, Arguments arguments) {
        this.type = type;
        this.identifier = identifier;
        this.arguments = arguments;
        this.idarray = idarray;
    }

    public String getHash() {
        StringBuilder ret = new StringBuilder();
        ret.append(type.getHash());
        if (idarray.countBrackets != 0)
            ret.append(idarray.countBrackets);
        if (arguments != null) {
            ret.append(arguments.getHash());
        }
        return ret.toString();
    }

    public Arguments(Type type, Identifier identifier, Idarray idarray) {
        this(type, identifier, idarray, null);
    }
}
