package optimusprime.tasks;

public class Todos extends Task {

    public Todos(String name, boolean isComplete) {
        super(name, isComplete);
    }

    private final static String mark = "[T]";
    private final static String type = "todo";

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return mark + super.toString();
    }
}
