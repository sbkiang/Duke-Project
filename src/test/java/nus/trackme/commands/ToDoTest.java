package nus.trackme.commands;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoTest {
    @Test
    public void ToDoTest(){
        String line = "read book";
        Task result = new ToDo(line);
        assertEquals("[T][ ] read book", result.toString());
    }

}