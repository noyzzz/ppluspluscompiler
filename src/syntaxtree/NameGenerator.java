package syntaxtree;

/**
 * Created by Arash on 18/07/02.
 */
public class NameGenerator {
    static int name = 0;
    int nextName;

    public NameGenerator() {
        this.nextName = name++;
    }

    @Override
    public String toString() {
        return "v" + nextName;
    }
}
