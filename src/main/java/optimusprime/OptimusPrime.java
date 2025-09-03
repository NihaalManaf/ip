package optimusprime;

import optimusprime.database.DatabaseHandler;
import optimusprime.exceptions.InvalidArugmentException;
import optimusprime.parser.Parser;
import optimusprime.tasks.Task;
import optimusprime.tasks.TaskList;
import optimusprime.ui.ui;

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
            FIND,
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
                    case "find" -> FIND;
                    default -> UNKNOWN;
                };
            }
        }


        Scanner scanner = new Scanner(System.in);
        TaskList tasks = DatabaseHandler.readDatabase();;
        ui.sayHi();

        main: while(true){
            System.out.println("User:");
            String input = scanner.nextLine();
            String inputCommand = input.split(" ")[0];
            CommandType commandType = CommandType.fromString(inputCommand);

            switch (commandType){
                case BYE -> {
                    ui.drawLine();
                    ui.sayBye();
                    DatabaseHandler.writeDatabase(tasks);
                    break main;
                }
                case UNMARK -> {
                    char itemToAdd = input.charAt(input.length() - 1);
                    int item = itemToAdd - '0';
                    Task task = tasks.markIncomplete(item);
                    ui.printWithLine("OK, I've marked this task as not done yet:\n" + task);
                    DatabaseHandler.writeDatabase(tasks);
                }
                case MARK -> {
                    char itemToAdd = input.charAt(input.length() - 1);
                    int item = itemToAdd - '0';
                    Task task = tasks.markComplete(item);
                    ui.printWithLine("Nice! I've marked this task as done:\n" + task);
                    DatabaseHandler.writeDatabase(tasks);
                }
                case LIST -> {
                    String list = tasks.getTasks(tasks);
                    ui.printWithLine(list);
                }
                case TASK -> {
                    String taskName = input.split(" ")[0];
                    String metaData = input.replaceAll(taskName, "").trim();

                    try {
                        String response = tasks.createTask(taskName, metaData);
                        ui.printWithLine(response);
                    } catch (InvalidArugmentException e){
                        ui.printWithLine(e.getMessage());
                    }
                    DatabaseHandler.writeDatabase(tasks);
                }
                case DELETE -> {
                    try {
                        int toDelete = Integer.parseInt(input.split(" ")[1]);
                        String response = tasks.deleteTask(toDelete);
                        ui.printWithLine(response);
                    } catch (InvalidArugmentException e) {
                        ui.printWithLine(e.getMessage());
                    }
                    DatabaseHandler.writeDatabase(tasks);
                }
                case FIND -> {
                    try {
                        String parsedInput = Parser.parseKeyword(input);
                        String response = tasks.findTasks(parsedInput);
                        ui.printWithLine(response);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                        System.out.println("Please enter an argument after 'find'");
                    }
                }
                case UNKNOWN -> {
                    ui.printWithLine("Human... Please enter a valid command...");
                    DatabaseHandler.readDatabase();
                }
            }
        }
    }
}
