package optimusprime.parser;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class ParserTest {

    @Test
    public void eventMetadataParseTest() {
        LocalDate[] ld1Pass = Parser.eventDateParser("meeting /from 2010-12-11 /to 2096-01-09");
        LocalDate[] ld2Pass = Parser.eventDateParser("full day school /from 2013-06-25 /to 2014-01-01");
        LocalDate[] ld1fail = Parser.eventDateParser("full day school /from 2013-06-235 /to 2014-01-01");

        LocalDate[] result1 = new LocalDate[]{
                LocalDate.parse("2010-12-11"), LocalDate.parse("2096-01-09")};
        LocalDate[] result2 = new LocalDate[]{
                LocalDate.parse("2013-06-25"), LocalDate.parse("2014-01-01")};

        assertArrayEquals(ld1Pass, result1);
        assertArrayEquals(ld2Pass, result2);
        assertNull(ld1fail);
    }

    @Test
    public void deadlineMetadataParseTest() {
        LocalDate[] ld1Pass = Parser.deadlineDateParser("finish hw /by 2024-10-10");
        LocalDate[] ld2Pass = Parser.deadlineDateParser("get a life /by 2025-01-21");
        LocalDate[] ld1fail = Parser.deadlineDateParser("research ai /by 2023-00-91");

        LocalDate[] result1 = new LocalDate[] {
                LocalDate.parse("2024-10-10")};
        LocalDate[] result2 = new LocalDate[] {
                LocalDate.parse("2025-01-21")};

        assertArrayEquals(ld1Pass, result1);
        assertArrayEquals(ld2Pass, result2);
        assertNull(ld1fail);
    }
}
