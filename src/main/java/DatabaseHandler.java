import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.io.FileWriter;
import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;
import java.io.File;

public final class DatabaseHandler {

    protected final class Row {
        // row| TaskType | isComplete | Description | Optional[MetaInfo] | Optional[MetaInfo]
        private final String taskType;
        private final boolean isComplete;
        private final String Description;
        private final String[] metaData;

        public Row(String taskType, boolean isComplete, String Description, String[] metaData) {
            this.taskType = taskType;
            this.isComplete = isComplete;
            this.Description = Description;
            this.metaData = metaData;
        }

        public String toString(){

            StringBuilder metaDataRepresentation = new StringBuilder();
            if (metaData != null) {
                for (String metaDatum : metaData) {
                    metaDataRepresentation.append(metaDatum).append(" | ");
                }
            }

            String line = String.format(
                    "%s | %b | %s | %s $\n",
                    taskType,
                    isComplete,
                    Description,
                    metaDataRepresentation);
            return line;
        }
    }
    public DatabaseHandler() {}
    static String filePath = "/Users/nihaalmanaf/Documents/The Entity's Code/ip/src/main/java/data/duke.txt";

    public static TaskList readDatabase() {
        TaskList tasks = new TaskList();
        try{
            Scanner scanner = new Scanner(new File(filePath));
            while(scanner.hasNext()) {
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
        } catch (FileNotFoundException e){
            System.out.println("FILE NOT FOUND LAH");
            return new TaskList();
        }
    }

    public static void writeDatabase(TaskList tasklist) {
        ArrayList<Task> taskList = tasklist.getList();
        try {
            FileWriter writer = new FileWriter(filePath);
            for(int i = 0; i < taskList.size(); i++) {
                Task task = taskList.get(i);
                LocalDate[] metaData = Task.getMetadata(task);

                StringBuilder metaDataRepresentation = new StringBuilder();

                if (metaData != null) {
                    for(int j = 0; j < metaData.length; j ++) {
                        if (metaData[j] == null) {continue;}
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

/*
row| TaskType | isComplete | Description | Optional[MetaInfo] | Optional[MetaInfo]
1 | event | true | run 5km |
 */
