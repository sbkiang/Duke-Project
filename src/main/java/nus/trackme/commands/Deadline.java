package nus.trackme.commands;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;


public class Deadline extends Task {

    protected String by;
    public Deadline(String description, String by) {

        super(description);
        this.by = by;

    }
    @Override
    public String toString() {
        return "[D]" + super.toString() + " " + "(by: " + by + ")";
    }
}
