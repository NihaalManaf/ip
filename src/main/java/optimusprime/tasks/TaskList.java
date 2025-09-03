package optimusprime.tasks;

import optimusprime.parser.Parser;
import optimusprime.exceptions.InvalidArgumentException;
import optimusprime.exceptions.InvalidDeleteArgumentException;
import optimusprime.exceptions.MissingDeadlineArgumentException;
import optimusprime.exceptions.MissingEventArgumentException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;

public class TaskList {

    Task[] list = new Task[100];
    ArrayList<Task> taskList = new ArrayList<>();

    public TaskList() {}

    /**
     * Adds object Task to TaskList
     * @param task Object Task
     */
    public void addToList(Task task){
        taskList.add(task);
    }

    /**
     * Returns if the Tasklist object is empty
     * @return boolean for if the TaskList is empty
     */
    public Boolean isEmpty() {
        return taskList.isEmpty();
    }

    /**
     * Creates a task based on the type of task, and the metadata. Adds the task directly to
     * task list after task instantiation
     *
     * @param taskName A String of either 'todo', 'event', 'deadline'
     * @param metadata A String consisting of decription of task along with dates and commands that preceed them
     * @return A String that is to be displayed to the user after task has been added
     * @throws InvalidArgumentException
     */
    public String createTask(String taskName, String metadata) throws InvalidArgumentException {

        Task task;
        String name = "";
        if (!metadata.contains("/")) {
            name = metadata;
        } else {
            name = metadata.substring(0, metadata.indexOf("/")).trim();
        }

        if (Objects.equals(taskName, "todo")) {
            task = new Todos(name, false);

            if (metadata.isEmpty()) {
                throw new InvalidArgumentException(
                        "Human... You must do something...\nTell me what you want to do after the todo command...");
            }

        } else if (Objects.equals(taskName, "deadline")) {
            if (!metadata.contains("/by")) {
                throw new MissingDeadlineArgumentException(
                        "The autobots normally enter their deadline proceeding a '/by' command...");
            }
            LocalDate[] localDate = Parser.deadlineDateParser(metadata);
            task = new Deadlines(name, localDate, false);

        } else if (Objects.equals(taskName, "event")) {
            String firstSubString = "/from";
            String secondSubString = "/to";

            LocalDate[] localDate = Parser.eventDateParser(metadata);

            if (!metadata.contains(firstSubString) || !metadata.contains(secondSubString)) {
                throw new MissingEventArgumentException(
                        "The autobots normally enter their event proceeding a '/from' and '/to' command...");
            }

            task = new Events(name, localDate, false);
        } else {
            return "Error in reading task!";
        }

        taskList.add(task);

        return "Got it. I've added this task:\n"
                + task.toString() + "\n"
                + "Now you have " + taskList.size() + " tasks in the list";
    }

    /**
     * Marks a task as complete
     *
     * @param i Index of task on list that you wish to mark complete
     * @return Returns the same Task object but modified with complete status
     */
    public Task markComplete(int i) {

        if (i < 1 || i > taskList.size()) {
            return null;
        }
        Task task = taskList.get(i - 1);
        task.markComplete();
        return task;
    }

    /**
     * Marks a task as incomplete
     *
     * @param i Index of task on list that you wish to mark incomplete
     * @return Returns the original Task object but modified with incomplete status
     */
    public Task markIncomplete(int i) {

        if (i < 1 || i > taskList.size()) {
            return null;
        }
        Task task = taskList.get(i - 1);
        task.markUncompleted();
        return task;
    }

    /**
     *  Returns a list of tasks in the task list as a List
     * @param S Instantiated task list
     * @return String of all tasks in iterated fashion
     */
    public String getTasks(TaskList tl) {

        if (tl.isEmpty()) {
            return "You have nothing on your list!";
        }
        ArrayList<Task> tlArrayList = tl.getList();
        String tasks = "";
        for (int i = 1; i <= tlArrayList.size() - 1; i++) {
            tasks += i + ". " + tlArrayList.get(i - 1).toString() + "\n";
        }
        tasks += (tlArrayList.size()) + ". " + tlArrayList.get(tlArrayList.size() - 1).toString();
        return tasks;
    }

    /**
     * Deletes a task based on the task's index number as seen in the list printed by TaskList.getTasks
     *
     *
     * @param i index number of task
     * @return Delete message that includes deleted task
     * @throws InvalidDeleteArgumentException
     */
    public String deleteTask(int i) throws InvalidDeleteArgumentException {
        i--;
        if (i < 0 || i > taskList.size() - 1) {
            throw new InvalidDeleteArgumentException(
                    "Human... You simply cannot delete nothing...\nEnter a valid task number...");
        }

        Task task = taskList.get(i);
        taskList.remove(task);
        return "Noted. I've removed this task:\n"
                + task.toString() + "\n"
                + "Now you have " + taskList.size() + " tasks in the list";
    }

    /**
     * Prints a list of tasks that match a keyword input by user
     *
     * @param keyword Search String
     * @return String of tasks to output to user
     */

    public String findTasks(String keyword) {

        if (taskList.isEmpty()) {
            return "There are no matches with your keyword!";
        }

        TaskList newList = new TaskList();
        ArrayList<Task> currentList = getList();
        boolean foundKeyword = false;
        for (Task task : currentList) {
            if (Task.getName(task).contains(keyword)) {
                newList.addToList(task);
                foundKeyword = true;
            }
        }
        if (!foundKeyword) {
            return "There are no matches with your keyword!";
        } else {
            return getTasks(newList);
        }
    }

    /**
     * Gets all tasks in this list as ArrayList<Task>
     * @return ArrayList<Task> of tasks
     */
    public ArrayList<Task> getList() {
        return this.taskList;
    }
}

