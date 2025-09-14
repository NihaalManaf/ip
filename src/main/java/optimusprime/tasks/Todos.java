package optimusprime.tasks;

/**
 * Represents a todo task.
 */
public class Todos extends Task {

    private static final String mark = "[T]";
    private static final String type = "todo";

    public Todos(String name, boolean isComplete) {
        super(name, isComplete);
    }

    /**
     * Returns the name of the type of class
     *
     * @return a String of the type of task
     */
    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return mark + super.toString();
    }
}
