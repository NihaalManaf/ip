public class TaskList {

    Task[] list = new Task[100];
    int pointer = 0;

    public TaskList() {}


    public void addToList(Task task){
        list[pointer] = task;
        pointer ++;
    }

    public Task markComplete(int i){
        Task task = list[i - 1];
        task.markComplete();
        return task;
    }

    public Task markUncomplete(int i){
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
