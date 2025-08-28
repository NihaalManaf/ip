import java.util.Objects;

public class Task {

    boolean completed = false;
    private String name;

    public Task(String name) {
        this.name = name;
    }

    public void markComplete(){
        completed = true;
    }

    public void markUncompleted() {
        completed = false;
    }

    public static boolean getStatus(Task t) { return t.completed; }

    public static String getName(Task t) { return t.name; }

    public String getType() {
        return "Default";
    }

    public static String[] getMetaData(Task t) {
        if (Objects.equals(t.getType(), "Todo")) {
            return null;
        } else if(Objects.equals(t.getType(), "Deadline")) {
            Deadlines task = (Deadlines) t;
            String deadline = task.deadline;
            return new String[] {deadline};
        } else if(Objects.equals(t.getType(), "Event")) {
            Events task = (Events) t;
            String startDate = task.fromDate;
            String endDate = task.toDate;
            return new String[] {startDate, endDate};
        }else {
            return null;
        }
    }

    @Override
    public String toString(){
        String box = "[ ]";
        if (completed) {
            box = "[X]";
        }

        return box + " " + name;
    }

}
