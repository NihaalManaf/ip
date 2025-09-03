package optimusprime.tasks;

import optimusprime.parser.Parser;
import java.time.LocalDate;

public class Events extends Task {

    protected static String mark = "[E]";
    LocalDate fromDate;
    LocalDate toDate;

    private final static String type = "event";


    public Events(String name, LocalDate[] dates, boolean isComplete){
        super(name, isComplete);
        this.fromDate = dates[0];
        this.toDate = dates[1];
    }

    /**
     * Returns the name of the type of class
     * @return a String of the type of task
     */
    public String getType() {return type;}

    @Override
    public String toString() {
        return mark + super.toString()
                + " (from: " + Parser.prettifyDate(fromDate)
                + " to: " + Parser.prettifyDate(toDate) + ")";
    }
}
