public class Events extends Task{

    final static String mark = "[E]";
    String fromDate;
    String toDate;

    public Events(String name, String fromDate, String toDate){
        super(name);
        this.fromDate = fromDate;
        this.toDate = toDate;
    }

    @Override
    public String toString() {
        return mark + super.toString() + " (from: " + fromDate + " to: " + toDate + ")";
    }
}
