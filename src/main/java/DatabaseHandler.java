import java.io.IOException;
import java.util.ArrayList;
import java.io.FileWriter;

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

    public static TaskList readDatabase() {

        return null;
    }

    public static void writeDatabase(TaskList tasklist) {
        ArrayList<Task> taskList = tasklist.getList();
        try {
            FileWriter writer = new FileWriter(
                    "/Users/nihaalmanaf/Documents/The Entity's Code/ip/src/main/java/data/duke.txt");
            for(int i = 0; i < taskList.size(); i++) {
                Task task = taskList.get(i);
                String[] metaData = Task.getMetaData(task);
                StringBuilder metaDataRepresentation = new StringBuilder();
                if (metaData != null) {
                    for(int j = 0; j < metaData.length; j ++) {
                        metaDataRepresentation.append(metaData[j]).append(" | ");
                    }
                }
                String line = String.format(
                        "%s | %s | %b | %s | %s $\n",
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
