import exceptions.InvalidArugmentException;
import exceptions.InvalidDeleteArgumentException;
import exceptions.MissingDeadlineArgumentException;
import exceptions.MissingEventArgumentException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;

public class TaskList {

    Task[] list = new Task[100];
    ArrayList<Task> taskList = new ArrayList<>();

    public TaskList() {}


    public void addToList(Task task){
        taskList.add(task);
    }

    public String createTask(String taskName, String metadata) throws InvalidArugmentException {

        Task task;
        String name = "";
        if (!metadata.contains("/")){
            name = metadata;
        } else {
            name = metadata.substring(0, metadata.indexOf("/")).trim();
        }

        if (Objects.equals(taskName, "todo")) {
            task = new Todos(name, false);

            if(metadata.isEmpty()) {
                throw new InvalidArugmentException(
                        "Human... You must do something...\nTell me what you want to do after the todo command...");
            }

        } else if(Objects.equals(taskName, "deadline")) {
            if (! metadata.contains("/by")) {
                throw new MissingDeadlineArgumentException(
                        "The autobots normally enter their deadline proceeding a '/by' command..." );
            }
            LocalDate[] localDate = Parser.deadlineDateParser(metadata);
            task = new Deadlines(name, localDate, false);

        } else if(Objects.equals(taskName, "event")) {
            String firstSubString = "/from";
            String secondSubString = "/to";

            LocalDate[] localDate = Parser.eventDateParser(metadata);

            if (!metadata.contains(firstSubString) || !metadata.contains(secondSubString)) {
                throw new MissingEventArgumentException(
                        "The autobots normally enter their event proceeding a '/from' and '/to' command..." );
            }

            task = new Events(name, localDate, false);
        } else {
            return "Error in reading task!";
        }
        taskList.add(task);

        return "Got it. I've added this task:\n"
                + task.toString() + "\n"
                + "Now you have " + taskList.size() +" tasks in the list.";
    }

    public Task markComplete(int i){

        if (i < 1 || i > taskList.size() ){
            return null;
        }
        Task task = taskList.get(i - 1);
        task.markComplete();
        return task;
    }

    public Task markIncomplete(int i){

        if (i < 1 || i > taskList.size() ){
            return null;
        }
        Task task = taskList.get(i - 1);
        task.markUncompleted();
        return task;
    }

    public String getTasks(TaskList S){

        if (taskList.isEmpty()){
            return "You have nothing on your list!";
        }
        String tasks = "";
        for (int i = 1; i <= taskList.size() - 1; i ++){
            tasks += i + ". " + taskList.get(i - 1).toString() + "\n";
        }
        tasks += (taskList.size()) + ". " + taskList.get(taskList.size() - 1) .toString();
        return tasks;
    }

    public String deleteTask(int i) throws InvalidDeleteArgumentException {
        i --;
        if (i < 0 || i > taskList.size() - 1) {
            throw new InvalidDeleteArgumentException(
                    "Human... You simply cannot delete nothing...\nEnter a valid task number...");
        }

        Task task = taskList.get(i);
        taskList.remove(task);
        return
                "Noted. I've removed this task:\n"
                + task.toString() + "\n"
                + "Now you have " + taskList.size() + " tasks in the list";
    }

    public ArrayList<Task> getList() { return this.taskList; }


}

