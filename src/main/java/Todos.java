public class Todos extends Task{

    public Todos(String name) {
        super(name);
    }

    final static String mark = "[T]";

    @Override
    public String toString() {
        return mark + super.toString();
    }
}
