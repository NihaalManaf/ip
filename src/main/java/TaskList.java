public class TaskList {

    String[] list = new String[100];
    int pointer = 0;

    public TaskList() {}


    public void addToList(String task){
        list[pointer] = task;
        pointer ++;
    }

    public void printTasks(TaskList S){
        for (int i = 1; i <= pointer; i ++){
            System.out.println(i + ". " + list[i - 1]);
        }
    }
}
