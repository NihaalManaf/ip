import java.util.Objects;

public class TaskList {

    Task[] list = new Task[100];
    int pointer = 0;

    public TaskList() {}


    public void addToList(Task task){
        list[pointer] = task;
        pointer ++;
    }

    public String createTask(String taskName, String metadata) throws MissingArugmentException{


        Task task;
        String name = "";
        if (!metadata.contains("/")){
            name = metadata;
        } else {
            name = metadata.substring(0, metadata.indexOf("/")).trim();
        }


        if (Objects.equals(taskName, "todo")) {
            task = new Todos(name);

            if(metadata.isEmpty()) {
                throw new MissingArugmentException(
                        "Human... You must do something...\nTell me what you want to do after the todo command...");
            }

        } else if(Objects.equals(taskName, "deadline")) {
            String subString = "/by";

            if (! metadata.contains(subString)) {
                throw new MissingDeadlineArgumentException(
                        "The autobots normally enter their deadline proceeding a '/by' command..." );
            }
            String finalDate = metadata
                    .substring(metadata.indexOf(subString))
                    .replaceAll(subString, "")
                    .trim();
            task = new Deadlines(name, finalDate);

        } else if(Objects.equals(taskName, "event")) {
            String firstSubString = "/from";
            String secondSubString = "/to";
            if (!metadata.contains(firstSubString) || !metadata.contains(secondSubString)) {
                throw new MissingEventArgumentException(
                        "The autobots normally enter their event proceeding a '/from' and '/to' command..." );
            }
            String startDate = metadata.substring(
                    metadata.indexOf(firstSubString),
                    metadata.indexOf(secondSubString))
                    .replaceAll(firstSubString, "")
                    .trim();

            String endDate = metadata.substring(metadata.indexOf(secondSubString))
                    .replaceAll(secondSubString, "")
                    .trim();
            task = new Events(name, startDate, endDate);
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
