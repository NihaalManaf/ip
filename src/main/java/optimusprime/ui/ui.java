package optimusprime.ui;

public final class ui {
    public ui() {}

    public static void drawLine(){
        String line = "-----------------------------------------------";
        System.out.println(line);
    }

    public static void printWithLine(String text){
        drawLine();
        System.out.println(text);
        drawLine();
    }

    public static void sayHi() {
        drawLine();
        String greetText = "Hello! I'm Optimus Prime, Leader of the Autobots\nWhat can I do for you?";
        System.out.println(greetText); drawLine();
    }

    public static void sayBye() {
        String byeText = "Autobots, Roll Out!";
        System.out.println(byeText); drawLine();
    }

}
