public class Deadlines extends Task{

    String deadline;
    final static String mark = "[D]";

    public Deadlines(String name, String date){
        super(name);
        this.deadline = date;
    }

    public String toString() {
        return mark + super.toString() + " (by: " + deadline + ")";
    }
}
