public class Todos extends Task{

    public Todos(String name) {
        super(name);
    }

    private final static String mark = "[T]";
    private final static String type = "Todo";

    public String getType() {return type;}

    @Override
    public String toString() {
        return mark + super.toString();
    }
}
