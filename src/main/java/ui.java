public final class ui {
    public ui() {}

    private static String line = "-----------------------------------------------";
    private static String greetText = "Hello! I'm Optimus Prime, Leader of the Autobots\nWhat can I do for you?";
    private static String byeText = "Autobots, Roll Out!";

    public static void drawLine(){
        System.out.println(line);
    }

    public static void printWithLine(String text){
        drawLine();
        System.out.println(text);
        drawLine();
    }

    public static void sayHi() {drawLine(); System.out.println(greetText); drawLine();}
    public static void sayBye() {System.out.println(byeText); drawLine();}

}
