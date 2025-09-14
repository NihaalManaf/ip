package optimusprime.database;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

import optimusprime.tasks.Task;
import optimusprime.tasks.TaskList;

/**
 * Handles database operations for storing and retrieving tasks.
 */
public final class DatabaseHandler {

    private static String[] possiblePaths = { "db.txt", "../db.txt", "src/main/java/db.txt" };
    private static String filePath;

    static {
        filePath = findDatabaseFile();
    }

    public DatabaseHandler() {
    }

    private static String findDatabaseFile() {
        for (String path : possiblePaths) {
            if (new File(path).exists()) {
                return path;
            }
        }
        return "db.txt"; // Default fallback
    }

    /**
     * Reads your text file, which acts as a long form storage to maintain your data
     * between user sessions.
     * There must be a file to read. Else, a FileNotFoundException will be thrown.
     * This method is not generic. It returns an instantiated object of TaskList.
     * FilePath has been already set and not editable.
     *
     * @return It returns an instantiated object of TaskList with all the data from
     *         the database
     **/
    public static TaskList readDatabase() {
        TaskList tasks = new TaskList();
        try {
            Scanner scanner = new Scanner(new File(filePath));
            while (scanner.hasNext()) {
                String row = scanner.nextLine();
                String[] rowInputs = row.split("\\s*\\|\\s*");

                if (rowInputs.length < 4) {
                    continue;
                }

                String taskType = rowInputs[1].trim();
                boolean isComplete = Boolean.parseBoolean(rowInputs[2].trim());
                String description = rowInputs[3].trim();

                LocalDate[] metadata = new LocalDate[2];
                if (rowInputs.length > 4 && !rowInputs[4].trim().isEmpty()) {
                    metadata[0] = LocalDate.parse(rowInputs[4].trim());
                }
                if (rowInputs.length > 5 && !rowInputs[5].trim().isEmpty()) {
                    metadata[1] = LocalDate.parse(rowInputs[5].trim());
                }

                Task task = Task.createTask(taskType, isComplete, description, metadata);
                if (task != null) {
                    tasks.addToList(task);
                }
            }
            scanner.close();
            return tasks;
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
            System.out.println("Text File has not been found.");
            return new TaskList();
        }
    }

    /**
     * Takes in an object of TaskList and writes the data
     * to a file in a preset txt file. The data written follows
     * a schema, which allows the readDatabase method to read the data
     * and return a newly instantiated TaskList object.
     *
     * @param tasklist is an object of TaskList
     */

    public static void writeDatabase(TaskList tasklist) {
        ArrayList<Task> taskList = tasklist.getList();
        try {
            FileWriter writer = new FileWriter(filePath);
            for (int i = 0; i < taskList.size(); i++) {
                Task task = taskList.get(i);
                LocalDate[] metaData = Task.getMetadata(task);

                StringBuilder metaDataRepresentation = new StringBuilder();

                if (metaData != null) {
                    for (int j = 0; j < metaData.length; j++) {
                        if (metaData[j] == null) {
                            continue;
                        }
                        if (j != metaData.length - 1) {
                            metaDataRepresentation.append(metaData[j]).append(" | ");
                        } else {
                            metaDataRepresentation.append(metaData[j]);
                        }
                    }
                }
                String line = String.format(
                        "%d | %s | %b | %s | %s\n",
                        i + 1,
                        task.getType(),
                        Task.getStatus(task),
                        Task.getName(task),
                        metaDataRepresentation);
                writer.write(line);
            }
            writer.close();
        } catch (IOException e) {
            System.out.println(e);
            System.out.println("File already exists");
        }
    }
}
