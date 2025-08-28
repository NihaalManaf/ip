import java.util.Objects;

public class Task {

    boolean isComplete = false;
    private String name;

    public Task(String name, boolean isComplete) {
        this.name = name;
        this.isComplete = isComplete;
    }

    public void markComplete(){
        isComplete = true;
    }

    public void markUncompleted() {
        isComplete = false;
    }

    public static boolean getStatus(Task t) { return t.isComplete; }

    public static String getName(Task t) { return t.name; }

    public String getType() {
        return "Default";
    }

    public static String[] getMetaData(Task t) {
        if (Objects.equals(t.getType(), "todo")) {
            return null;
        } else if(Objects.equals(t.getType(), "deadline")) {
            Deadlines task = (Deadlines) t;
            String deadline = task.deadline;
            return new String[] {deadline};
        } else if(Objects.equals(t.getType(), "event")) {
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
