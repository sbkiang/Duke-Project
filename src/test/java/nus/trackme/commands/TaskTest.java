package nus.trackme.commands;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {
    @Test
    public void TaskTest(){
        String line = "read book";
        Task result = new Task(line);
        assertEquals("[ ] read book", result.toString());

        result.markAsDone();
        assertEquals("[X] read book", result.toString());

        result.unmarkAsDone();
        assertEquals("[ ] read book", result.toString());

    }
}
