package optimusprime.tasks;

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

    public static LocalDate[] getMetadata(Task t) {
        if (Objects.equals(t.getType(), "todo")) {
            return null;
        } else if(Objects.equals(t.getType(), "deadline")) {
            Deadlines task = (Deadlines) t;
            return task.deadline;
        } else if(Objects.equals(t.getType(), "event")) {
            Events task = (Events) t;
            LocalDate startDate = task.fromDate;
            LocalDate endDate = task.toDate;
            return new LocalDate[] {startDate, endDate};
        }else {
            return null;
        }
    }

    public static Task createTask(String type, boolean isComplete, String Description, LocalDate[] metadata) {
        Task task;
        if (Objects.equals(type, "todo")) {
           task = new Todos(Description, isComplete);
        } else if(Objects.equals(type, "deadline")){
            task = new Deadlines(Description, metadata, isComplete);
        } else if(Objects.equals(type, "event")){
            task = new Events(Description, metadata, isComplete);
        } else {
            return null;
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
