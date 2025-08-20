public class Task {

    boolean completed = false;
    String name;

    public Task(String name) {
        this.name = name;
    }

    public void markComplete(){
        completed = true;
    }

    public void markUncompleted() {
        completed = false;
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
