public class Events extends Task{

    protected static String mark = "[E]";
    String fromDate;
    String toDate;

    private final static String type = "Event";


    public Events(String name, String fromDate, String toDate){
        super(name);
        this.fromDate = fromDate;
        this.toDate = toDate;
    }

    public String getType() {return type;}

    @Override
    public String toString() {
        return mark + super.toString() + " (from: " + fromDate + " to: " + toDate + ")";
    }
}
