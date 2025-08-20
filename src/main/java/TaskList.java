public class TaskList {

    String[] list = new String[100];
    int pointer = 0;

    public TaskList() {}


    public void addToList(String task){
        list[pointer] = task;
        pointer ++;
    }

    public String getTasks(TaskList S){

        if (pointer == 0){
            return "You have nothing on your list!";
        }
        String tasks = "";
        for (int i = 1; i <= pointer - 1; i ++){
            tasks += i + ". " + list[i - 1] + "\n";
        }
        tasks += (pointer) + ". " + list[pointer - 1];
        return tasks;
    }
}
