package syntaxtree;

public class LabelGenerator {
    static int name = 0;
    int nextName;

    public LabelGenerator() {
        this.nextName = name++;
    }

    @Override
    public String toString() {
        return "l" + nextName;
    }
}
