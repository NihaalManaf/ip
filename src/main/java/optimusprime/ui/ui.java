package optimusprime.ui;

public final class ui {
    public ui() {}

    public static String drawLine(){
        return "-----------------------------------------------";

    }

    public static String printWithLine(String text){
        return drawLine() + "\n" + text + "\n" + drawLine();
    }

    public static String sayHi() {
        String greetText = "Hello! I'm Optimus Prime, Leader of the Autobots\nWhat can I do for you?";
        return drawLine() + "\n" + greetText + "\n" + drawLine();
    }

    public static String sayBye() {
        String byeText = "Autobots, Roll Out!";
        return drawLine() + "\n" + byeText + "\n" + drawLine();
    }

}
