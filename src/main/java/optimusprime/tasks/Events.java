package optimusprime.tasks;

import java.time.LocalDate;

import optimusprime.parser.Parser;

/**
 * Represents an event task with start and end dates.
 */
public class Events extends Task {

    protected static String mark = "[E]";
    private static final String type = "event";

    private LocalDate fromDate;
    private LocalDate toDate;

    /**
     * Constructs a new Events task.
     *
     * @param name       The name of the event
     * @param dates      Array containing start and end dates
     * @param isComplete Whether the event is completed
     */
    public Events(String name, LocalDate[] dates, boolean isComplete) {
        super(name, isComplete);
        this.fromDate = dates[0];
        this.toDate = dates[1];
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
     * Returns the start date of the event.
     *
     * @return LocalDate representing the start date
     */
    public LocalDate getFromDate() {
        return fromDate;
    }

    /**
     * Returns the end date of the event.
     *
     * @return LocalDate representing the end date
     */
    public LocalDate getToDate() {
        return toDate;
    }

    @Override
    public String toString() {
        return mark + super.toString()
                + " (from: " + Parser.prettifyDate(fromDate)
                + " to: " + Parser.prettifyDate(toDate) + ")";
    }
}
