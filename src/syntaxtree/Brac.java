package syntaxtree;

public class Brac {
    Integer countBrackets;
    public Brac(){
        countBrackets = 0;
    }
    public Brac(Brac brac){
        this.countBrackets = brac.countBrackets +1;
    }
}
