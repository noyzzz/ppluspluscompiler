package visitor;

/**
 * Created by Arash on 18/07/02.
 */
public class Label {
    static int labelNum = 0;
    int nextNumber;

    public Label() {
        nextNumber = labelNum++;
    }
}
