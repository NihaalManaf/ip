package optimusprime;

import optimusprime.database.DatabaseHandler;
import optimusprime.exceptions.InvalidArgumentException;
import optimusprime.parser.Parser;
import optimusprime.tasks.Task;
import optimusprime.tasks.TaskList;

/**
 * Main application class for OptimusPrime task management system.
 */
public class OptimusPrime {

    public String sayHi() {
        return "\"Hello! I'm Optimus Prime, Leader of the Autobots\\nWhat can I do for you?";
    }

    public String getResponse(String input) {

        enum CommandType {
            BYE,
            MARK,
            UNMARK,
            LIST,
            TASK,
            DELETE,
            FIND,
            SORT,
            UNKNOWN;

            public static CommandType runCommand(String input) {
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
                    case "sort" -> SORT;
                    default -> UNKNOWN;
                };
            }
        }

        TaskList tasks;
        try {
            tasks = DatabaseHandler.readDatabase();
        } catch (Exception e) {
            tasks = new TaskList();
        }

        System.out.println("User:");
        String inputCommand = input.split(" ")[0];
        CommandType commandType = CommandType.runCommand(inputCommand);

        switch (commandType) {
            case BYE -> {
                return "Autobots, Roll Out!";
            }
            case UNMARK -> {
                char itemToAdd = input.charAt(input.length() - 1);
                int item = itemToAdd - '0';
                Task task = tasks.markIncomplete(item);
                DatabaseHandler.writeDatabase(tasks);
                return "OK, I've marked this task as not done yet:\n" + task;
            }
            case MARK -> {
                char itemToAdd = input.charAt(input.length() - 1);
                int item = itemToAdd - '0';
                Task task = tasks.markComplete(item);
                DatabaseHandler.writeDatabase(tasks);
                return "Nice! I've marked this task as done:\n" + task;
            }
            case LIST -> {
                return tasks.getTasks(tasks);
            }
            case TASK -> {
                String taskName = input.split(" ")[0];
                String metaData = input.replaceAll(taskName, "").trim();

                try {
                    String response = tasks.createTask(taskName, metaData);
                    DatabaseHandler.writeDatabase(tasks);
                    return response;
                } catch (InvalidArgumentException e) {
                    return e.getMessage();
                } catch (Exception e) {
                    System.out.println("Uh oh...The Decepticons are coming...\nLet's add your task later");
                }
            }
            case DELETE -> {
                try {
                    int toDelete = Integer.parseInt(input.split(" ")[1]);
                    String response = tasks.deleteTask(toDelete);
                    DatabaseHandler.writeDatabase(tasks);
                    return response;
                } catch (InvalidArgumentException e) {
                    return e.getMessage();
                }
            }
            case FIND -> {
                try {
                    String parsedInput = Parser.parseKeyword(input);
                    return tasks.findTasks(parsedInput);
                } catch (Exception e) {
                    return "Please enter an argument after 'find'";
                }
            }
            case SORT -> {
                try {
                    String parsedInput = Parser.parseKeyword(input);
                    return tasks.sort(parsedInput);
                } catch (Exception e) {
                    return "Please enter ascending or descending after 'sort'";
                }
            }
            default -> {
                return "Human... Please enter a valid command...";
            }
        }

        return "";
    }
}
