import exceptions.InvalidArugmentException;

import javax.xml.crypto.Data;
import java.util.Scanner;

public class OptimusPrime {

    public static void main(String[] args) {

        enum CommandType {
            BYE,
            MARK,
            UNMARK,
            LIST,
            TASK,
            DELETE,
            UNKNOWN;


            public static CommandType fromString(String input) {
                if (input == null) {
                    return UNKNOWN;
                }
                return switch (input.toLowerCase()) {
                    case "bye" -> BYE;
                    case "mark" -> MARK;
                    case "unmark" -> UNMARK;
                    case "list" -> LIST;
                    case "todo" -> TASK;
                    case "deadline" -> TASK;
                    case "event" -> TASK;
                    case "delete" -> DELETE;
                    default -> UNKNOWN;
                };
            }
        }


        Scanner scanner = new Scanner(System.in);

        String line = "-----------------------------------------------";
        String greetText = "Hello! I'm Optimus Prime, Leader of the Autobots\nWhat can I do for you?";
        String byeText = "Autobots, Roll Out!";

        System.out.println(line);
        System.out.println(greetText);
        System.out.println(line);
        TaskList tasks = DatabaseHandler.readDatabase();;


        main: while(true){
            System.out.println("User:");
            String input = scanner.nextLine();
            String inputCommand = input.split(" ")[0];
            CommandType commandType = CommandType.fromString(inputCommand);
            System.out.println(line);
            switch (commandType){
                case BYE -> {
                    System.out.println(byeText);
                    System.out.println(line);
                    DatabaseHandler.writeDatabase(tasks);
                    break main;
                }
                case UNMARK -> {
                    char itemToAdd = input.charAt(input.length() - 1);
                    int item = itemToAdd - '0';
                    Task task = tasks.markIncomplete(item);
                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println(task);
                    DatabaseHandler.writeDatabase(tasks);
                }
                case MARK -> {
                    char itemToAdd = input.charAt(input.length() - 1);
                    int item = itemToAdd - '0';
                    Task task = tasks.markComplete(item);
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println(task);
                    DatabaseHandler.writeDatabase(tasks);
                }
                case LIST -> {
                    String list = tasks.getTasks(tasks);
                    System.out.println(list);
                }
                case TASK -> {
                    String taskName = input.split(" ")[0];
                    String metaData = input.replaceAll(taskName, "").trim();

                    try {
                        String response = tasks.createTask(taskName, metaData);
                        System.out.println(response);
                    } catch (InvalidArugmentException e){
                        System.out.println(e.getMessage());
                    }
                    DatabaseHandler.writeDatabase(tasks);
                }
                case DELETE -> {
                    try {
                        int toDelete = Integer.parseInt(input.split(" ")[1]);
                        String response = tasks.deleteTask(toDelete);
                        System.out.println(response);
                    } catch (InvalidArugmentException e) {
                        System.out.println(e.getMessage());
                    }
                    DatabaseHandler.writeDatabase(tasks);
                }
                case UNKNOWN -> {
                    System.out.println("Human... Please enter a valid command...");
                    DatabaseHandler.readDatabase();
                }
            }
            System.out.println(line);
        }
    }
}
