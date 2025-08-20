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
            System.out.print("User: ");
            String input = scanner.nextLine();
            System.out.println(line);
            if (input.equalsIgnoreCase("bye")) {
                System.out.println(byeText);
                System.out.println(line);
                break;
            } else if (input.equalsIgnoreCase("list")) {
                String list = tasks.getTasks(tasks);
                System.out.println(list);
            } else {
                tasks.addToList(input);
                System.out.println("added: " +  input);
            }
            System.out.println(line);
        }
    }
}
