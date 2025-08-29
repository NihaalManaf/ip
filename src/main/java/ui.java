public final class ui {
    public ui() {}

    private static String line = "-----------------------------------------------";

    public static void drawLine(){
        System.out.println(line);
    }

    public static void printWithLine(String text){
        drawLine();
        System.out.println(text);
    }

}
