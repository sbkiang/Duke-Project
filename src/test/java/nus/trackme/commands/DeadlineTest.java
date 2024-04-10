package nus.trackme.commands;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    public void DeadlineTest(){
        String line = "read book";
        String by = "10/04/24 1500H";
        Task result = new Deadline(line, by);
        assertEquals("[D][ ] read book (by: 10/04/24 1500H)", result.toString());
    }
}
