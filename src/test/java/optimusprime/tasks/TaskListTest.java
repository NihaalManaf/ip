package optimusprime.tasks;

import optimusprime.exceptions.InvalidArgumentException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class TaskListTest {

    @Test
    public void createTaskTest() throws InvalidArgumentException {
        TaskList tasks = new TaskList();

        String result1 = """
                Got it. I've added this task:
                [T][ ] Buy Pens
                Now you have 1 tasks in the list
                """;

        String result2 = """
                Got it. I've added this task:
                [D][ ] Finish Task (by: 1 JANUARY 2026)
                Now you have 2 tasks in the list
                """;
        String result3 =
                """
                Got it. I've added this task:
                [E][ ] Lock In (from: 1 JANUARY 2026 to: 1 JANUARY 2027)
                Now you have 3 tasks in the list
                """;

        assertEquals(result1.trim(), tasks.createTask("todo", "Buy Pens"));
        assertEquals(result2.trim(), tasks.createTask("deadline",
                "Finish Task /by 2026-01-01"));
        assertEquals(result3.trim(), tasks.createTask("event",
                "Lock In /from 2026-01-01 /to 2027-01-01"));
        assertThrows(InvalidArgumentException.class, () -> tasks.createTask("event", "event"));
    }

    @Test
    public void deleteTaskTest() throws InvalidArgumentException {
        TaskList originalTaskList = new TaskList();
        originalTaskList.createTask("todo", "Buy Pens");
        originalTaskList.createTask("deadline", "Finish Task /by 2026-01-01");
        originalTaskList.createTask("event", "Lock In /from 2026-01-01 /to 2027-01-01");


        String result1 = """
                Noted. I've removed this task:
                [T][ ] Buy Pens
                Now you have 2 tasks in the list
                """;
        assertEquals(result1.trim(),originalTaskList.deleteTask(1));

        String result2 = """
                Noted. I've removed this task:
                [D][ ] Finish Task (by: 1 JANUARY 2026)
                Now you have 1 tasks in the list
                """;
        assertEquals(result2.trim(), originalTaskList.deleteTask(1));

        String result3 =
                """
                Noted. I've removed this task:
                [E][ ] Lock In (from: 1 JANUARY 2026 to: 1 JANUARY 2027)
                Now you have 0 tasks in the list
                """;
        assertEquals(result3.trim(), originalTaskList.deleteTask(1));
    }


}
