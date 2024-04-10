package nus.trackme.commands;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    @Test
    public void EventTest(){
        String line = "read book";
        String from = "10/04/24 1500H";
        String to = "10/04/24 1600H";
        Task result = new Event(line, from, to);
        assertEquals("[E][ ] read book (from: 10/04/24 1500H to: 10/04/24 1600H)", result.toString());
    }
}
