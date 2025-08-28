public class Deadlines extends Task{

    String deadline;
    protected static String mark = "[D]";
    private final static String type = "Deadline";

    public Deadlines(String name, String date){
        super(name);
        this.deadline = date;
    }

    public String getType() {return type;}

    public String toString() {
        return mark + super.toString() + " (by: " + deadline + ")";
    }
}
