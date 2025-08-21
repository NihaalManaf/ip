import java.util.Objects;

public class TaskList {

    Task[] list = new Task[100];
    int pointer = 0;

    public TaskList() {}


    public void addToList(Task task){
        list[pointer] = task;
        pointer ++;
    }

    public String createTask(String taskName, String metadata){

        Task task;

        if (Objects.equals(taskName, "todo")) {
            task = new Todos(taskName);
        } else if(Objects.equals(taskName, "deadline")) {
            String subString = "/by";
            String finalDate = metadata.substring(metadata.indexOf(subString));
            task = new Deadlines(taskName, finalDate);
        } else if(Objects.equals(taskName, "event")) {
            String firstSubString = "/from";
            String secondSubString = "/to";
            String startDate = metadata.substring(
                    metadata.indexOf(firstSubString),
                    metadata.indexOf(secondSubString)
            );
            String endDate = metadata.substring(metadata.indexOf(secondSubString));
            task = new Events(taskName, startDate, endDate);
        } else {
            return "Error in reading task!";
        }
        list[pointer] = task;
        pointer ++;

        return "Got it. I've added this task:\n"
                + task.toString() + "\n"
                + "Now you have " + pointer +" tasks in the list.";
    }

    public Task markComplete(int i){

        if (i < 1 || i > pointer ){
            return null;
        }

        Task task = list[i - 1];
        task.markComplete();
        return task;
    }

    public Task markIncomplete(int i){

        if (i < 1 || i > pointer ){
            return null;
        }
        Task task = list[i - 1];
        task.markUncompleted();
        return task;
    }

    public String getTasks(TaskList S){

        if (pointer == 0){
            return "You have nothing on your list!";
        }
        String tasks = "";
        for (int i = 1; i <= pointer - 1; i ++){
            tasks += i + ". " + list[i - 1].toString() + "\n";
        }
        tasks += (pointer) + ". " + list[pointer - 1].toString();
        return tasks;
    }
}
