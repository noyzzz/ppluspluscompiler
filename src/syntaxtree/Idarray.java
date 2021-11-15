package syntaxtree;

public class Idarray {
    public Integer countBrackets;
    public Idarray(){
        countBrackets = 0;
    }
    public Idarray(Brac brac){
        countBrackets = brac.countBrackets;
    }
}
