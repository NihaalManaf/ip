import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Arrays;

public final class Parser {
    public Parser() {}

    public static LocalDate textToDateTime(String dateTime) {
        return LocalDate.parse(dateTime);
    }

    public static LocalDate[] deadlineDateParser(String metadata) {
        String byDate = metadata.split("/by ")[1];
        try{
            return new LocalDate[] {LocalDate.parse(byDate)};
        } catch (DateTimeParseException e) {
            System.out.println(e);
            return null;
        }
    }

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


}
