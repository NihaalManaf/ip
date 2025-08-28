public class Events extends Task{

    protected static String mark = "[E]";
    String fromDate;
    String toDate;

    private final static String type = "event";


    public Events(String name, String fromDate, String toDate, boolean isComplete){
        super(name, isComplete);
        this.fromDate = fromDate;
        this.toDate = toDate;
    }

    public String getType() {return type;}

    @Override
    public String toString() {
        return mark + super.toString() + " (from: " + fromDate + " to: " + toDate + ")";
    }
}
