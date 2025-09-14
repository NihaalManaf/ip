package optimusprime.tasks;

import java.time.LocalDate;

import optimusprime.parser.Parser;

/**
 * Represents a deadline task with a due date.
 */
public class Deadlines extends Task {

    protected static String mark = "[D]";
    private static final String type = "deadline";

    private LocalDate[] deadline;

    /**
     * Constructs a new Deadlines task.
     *
     * @param name       The name of the deadline
     * @param date       Array containing the deadline date
     * @param isComplete Whether the deadline is completed
     */
    public Deadlines(String name, LocalDate[] date, boolean isComplete) {
        super(name, isComplete);
        this.deadline = date;
    }

    /**
     * Returns the name of the type of class
     *
     * @return a String of the type of task
     */
    public String getType() {
        return type;
    }

    /**
     * Returns the deadline date array.
     *
     * @return LocalDate array containing the deadline
     */
    public LocalDate[] getDeadline() {
        return deadline;
    }

    public String toString() {
        return mark + super.toString() + " (by: " + Parser.prettifyDate(deadline[0]) + ")";
    }
}
