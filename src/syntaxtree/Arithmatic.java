package syntaxtree;

public class Arithmatic {
    public enum ArithmaticSign {
        PLUS, MINUS, TIMES, SLASH, MOD, AMPERSAND, PIPE, CARET
    }

    public ArithmaticSign arithmaticSign;
    public Arithmatic(ArithmaticSign arithmaticSign){
        this.arithmaticSign = arithmaticSign;
    }
}
