package optimusprime.parser;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public final class Parser {
    public Parser() {}

    /**
     * Returns an array of LocalDate objects which is parsed from the metadata specifically for deadline tasks.
     * metadata should not be empty
     *
     * @param metadata a String that includes the description and the deadline proceeded by the '/by' command
     * @return An array of LocalDate objects of length 1 that contains the deadline
     */
    public static LocalDate[] deadlineDateParser(String metadata) {
        String byDate = metadata.split("/by ")[1];
        try{
            return new LocalDate[] {LocalDate.parse(byDate)};
        } catch (DateTimeParseException e) {
            System.out.println(e);
            return null;
        }
    }

    /**
     * Returns an array of LocalDate objects which is parsed from the metadata specifically for event tasks.
     * metadata should not be empty
     *
     * @param metadata a String that includes the description and the start date proceeded by the '/from' command and
     *                 the end date proceeded by the /to command
     * @return An array of LocalDate objects of length 2 that contains the start date (arr[0]) and end date (arr[1])
     */
    public static LocalDate[] eventDateParser(String metadata) {

        String[] dates;

        try {
            String subString = metadata.split("/from ")[1];
            dates = subString.split("/to");
        } catch (ArrayIndexOutOfBoundsException e) {
            return new LocalDate[] {LocalDate.parse("2000-01-01"), LocalDate.parse("2010-01-01")};
        }
        LocalDate[] parsedDates = new LocalDate[2];
        try{
            int i = 0;
            for(String date: dates){
                parsedDates[i] = LocalDate.parse(date.trim());
                i++;
            }
        } catch(DateTimeParseException e) {
            System.out.println(e);
            return null;
        }
        return parsedDates;
    }

    /**
     * Returns a human-readable String from a LocalDate object for greater readability
     *
     * @param localDate - a LocalDate object
     * @return A prettified string of localdate
     */
    public static String prettifyDate(LocalDate localDate){
        return String.format("%d %s %d",
                localDate.getDayOfMonth(), localDate.getMonth(), localDate.getYear());
    }
}
