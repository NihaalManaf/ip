package optimusprime.tasks;

import optimusprime.exceptions.InvalidArgumentException;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Represents a task with a description and completion status.
 */
public class Task {

    private boolean isComplete = false;
    private final String description;

    /**
     * Constructs a new Task with the given name and completion status.
     *
     * @param name       The description of the task
     * @param isComplete Whether the task is completed
     */
    public Task(String name, boolean isComplete) {
        this.description = name;
        this.isComplete = isComplete;
    }

    public void markComplete() {
        isComplete = true;
    }

    public void markUncompleted() {
        isComplete = false;
    }

    public static boolean getStatus(Task t) {
        return t.isComplete;
    }

    public static String getName(Task t) {
        return t.description;
    }

    public String getType() {
        return "Default";
    }

    public static LocalDate[] getMetadata(Task t) {
        if (Objects.equals(t.getType(), "todo")) {
            return null;
        } else if (Objects.equals(t.getType(), "deadline")) {
            Deadlines task = (Deadlines) t;
            return task.getDeadline();
        } else if (Objects.equals(t.getType(), "event")) {
            Events task = (Events) t;
            LocalDate startDate = task.getFromDate();
            LocalDate endDate = task.getToDate();
            return new LocalDate[] { startDate, endDate };
        } else {
            return null;
        }
    }

    /**
     * Creates a new task of the specified type.
     *
     * @param type        The type of task to create
     * @param isComplete  Whether the task is completed
     * @param description The description of the task
     * @param metadata    Additional metadata for the task
     * @return A new Task object
     */
    public static Task createTask(String type, boolean isComplete, String description, LocalDate[] metadata) {
        Task task;
        if (Objects.equals(type, "todo")) {
            task = new Todos(description, isComplete);
        } else if (Objects.equals(type, "deadline")) {
            task = new Deadlines(description, metadata, isComplete);
        } else if (Objects.equals(type, "event")) {
            task = new Events(description, metadata, isComplete);
        } else {
            return null;
        }
        return task;
    }

    @Override
    public String toString() {
        String box = "[ ]";
        if (isComplete) {
            box = "[X]";
        }
        return box + " " + description;
    }

}
