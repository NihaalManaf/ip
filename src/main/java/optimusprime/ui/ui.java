package optimusprime.ui;

/**
 * Ui class handles user interface display formatting.
 */
public final class Ui {
    public Ui() {
    }

    /**
     * Draws a horizontal line for UI formatting.
     *
     * @return A string containing horizontal line characters
     */
    public static String drawLine() {
        return "-----------------------------------------------";
    }

    /**
     * Prints text surrounded by horizontal lines.
     *
     * @param text The text to be displayed
     * @return Formatted text with lines above and below
     */
    public static String printWithLine(String text) {
        return drawLine() + "\n" + text + "\n" + drawLine();
    }

    /**
     * Displays greeting message.
     *
     * @return Formatted greeting message
     */
    public static String sayHi() {
        String greetText = "Hello! I'm Optimus Prime, Leader of the Autobots\nWhat can I do for you?";
        return drawLine() + "\n" + greetText + "\n" + drawLine();
    }

    /**
     * Displays goodbye message.
     *
     * @return Formatted goodbye message
     */
    public static String sayBye() {
        String byeText = "Autobots, Roll Out!";
        return drawLine() + "\n" + byeText + "\n" + drawLine();
    }
}
