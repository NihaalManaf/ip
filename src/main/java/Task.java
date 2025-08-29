import java.time.LocalDate;
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

    public static Task createTask(String type, boolean isComplete, String Description, String[] MetaData) {
        Task task;
        if (Objects.equals(type, "todo")) {
           task = new Todos(Description, isComplete);
        } else if(Objects.equals(type, "deadline")){
            // Check if metadata exists and has at least one element
            if (MetaData != null && MetaData.length > 0 && MetaData[0] != null) {
                task = new Deadlines(Description, MetaData[0], isComplete);
            } else {
                // Create deadline with empty date if metadata is missing
                task = new Deadlines(Description, "", isComplete);
            }
        } else if(Objects.equals(type, "event")){
            // Check if metadata exists and has at least two elements
            if (MetaData != null && MetaData.length >= 2 && MetaData[0] != null && MetaData[1] != null) {
                task = new Events(Description, MetaData[0], MetaData[1], isComplete);
            } else {
                // Create event with empty dates if metadata is missing
                task = new Events(Description, "", "", isComplete);
            }
        } else {
            return null; // Return null for unknown task types
        }
        return task;
    }
    @Override
    public String toString(){
        String box = "[ ]";
        if (isComplete) {
            box = "[X]";
        }

        return box + " " + name;
    }

}
