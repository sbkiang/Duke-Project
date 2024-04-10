package nus.trackme.commands;
import nus.trackme.parser.ParseDateTime;

import java.time.LocalDate;

/**
 * Represents deadline task.
 *
 */
public class Deadline extends Task {

    protected String by;
    public Deadline(String description, String by) {

        super(description);
        this.by = by;

    }

    public boolean isUpcoming(LocalDate currentDate, int reminderDays){
        //Parse the date string
        ParseDateTime date = new ParseDateTime(by);
        LocalDate deadlineDate = date.deadlineDate(by);
        LocalDate reminderDate = currentDate.plusDays(reminderDays);
        return !deadlineDate.isAfter(reminderDate) && deadlineDate.isAfter(currentDate);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " " + "(by: " + by + ")";
    }
}
