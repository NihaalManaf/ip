package optimusprime.tasks;

import optimusprime.parser.Parser;
import java.time.LocalDate;

public class Deadlines extends Task {

    LocalDate[] deadline;
    protected static String mark = "[D]";
    private final static String type = "deadline";

    public Deadlines(String name, LocalDate[] date, boolean isComplete){
        super(name, isComplete);
        this.deadline = date;
    }

    public String getType() {return type;}

    public String toString() {
        return mark + super.toString() + " (by: " + Parser.prettifyDate(deadline[0]) + ")";
    }
}
