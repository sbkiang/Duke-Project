package nus.trackme.commands;
import nus.trackme.parser.ParseDateTime;
import java.time.LocalDateTime;

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

    @Override
    public boolean isUpcoming(LocalDateTime currentDT, int reminderDays, int reminderHours){
        //Parse the date string
        ParseDateTime date = new ParseDateTime(by);
        LocalDateTime deadlineDT = date.deadlineDT(by);
        LocalDateTime reminderDT = currentDT.plusDays(reminderDays).plusHours(reminderHours);
        return (!deadlineDT.isAfter(reminderDT) || deadlineDT.equals(reminderDT) || deadlineDT.toLocalDate().isEqual(reminderDT.toLocalDate())) && (deadlineDT.isAfter(currentDT) || deadlineDT.equals(currentDT) || deadlineDT.toLocalDate().isEqual(currentDT.toLocalDate()));

    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " " + "(by: " + by + ")";
    }
}
