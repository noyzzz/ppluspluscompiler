/**
 * Created by Arash on 18/07/04.
 */
public class TempVarNameGenerator {
    static int num;
    int nextNum;

    public TempVarNameGenerator() {
        this.nextNum = num++;
    }

    @Override
    public String toString() {
        return "t" + nextNum;
    }
}
