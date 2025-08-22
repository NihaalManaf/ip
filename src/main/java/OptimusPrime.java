import javax.swing.plaf.synth.SynthTextAreaUI;
import java.util.Scanner;

public class OptimusPrime {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        String line = "-----------------------------------------------";
        String greetText = "Hello! I'm Optimus Prime, Leader of the Autobots\nLWhat can I do for you?";
        String byeText = "Autobots, Roll Out!";

        System.out.println(line);
        System.out.println(greetText);
        System.out.println(line);
        TaskList tasks = new TaskList();


        while(true){
            System.out.println("User:");
            String input = scanner.nextLine();
            System.out.println(line);
            if (input.equalsIgnoreCase("bye")) {
                System.out.println(byeText);
                System.out.println(line);
                break;

            } else if (input.toLowerCase().contains("unmark")) {
                char itemToAdd = input.charAt(input.length() - 1);
                int item = itemToAdd - '0';
                Task task = tasks.markIncomplete(item);
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println(task);

            } else if (input.toLowerCase().contains("mark")) {
                char itemToAdd = input.charAt(input.length() - 1);
                int item = itemToAdd - '0';
                Task task = tasks.markComplete(item);
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(task);

            } else if (input.equalsIgnoreCase("list")) {
                String list = tasks.getTasks(tasks);
                System.out.println(list);

            } else if (input.toLowerCase().contains("todo")
                        || input.toLowerCase().contains("deadline")
                        || input.toLowerCase().contains("event")) {
                String taskName = input.split(" ")[0];
                String metaData = input.replaceAll(taskName, "").trim();

                try {
                    String response = tasks.createTask(taskName, metaData);
                    System.out.println(response);
                } catch (MissingArugmentException e){
                    System.out.println(e.getMessage());
                }

            } else {
                System.out.println("Human... Please enter a valid command...");
            }
            System.out.println(line);
        }
    }
}
