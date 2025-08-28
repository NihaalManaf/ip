import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public final class Parser {
    public Parser() {}

    public static LocalDate textToDateTime(String dateTime) {
        return LocalDate.parse(dateTime);
    }

    public static LocalDate deadlineDateParser(String metadata) {
        String byDate = metadata.split("/by ")[1];
        try{
            return LocalDate.parse(byDate);
        } catch (DateTimeParseException e) {
            System.out.println(e);
            return null;
        }
    }

    public static LocalDate[] eventDateParser(String metadata) {
        // get pens /from afadfa /to adfbal
        String subString = metadata.split("/from ")[1];
        String[] dates = metadata.split("/to");
        LocalDate[] parsedDates = new LocalDate[2];
        try{
            int i = 0;
            for(String date: dates){
                parsedDates[i] = LocalDate.parse(date);
                i++;
            }
        } catch(DateTimeParseException e) {
            System.out.println(e);
            return null;
        }
        return parsedDates;
    }


}
