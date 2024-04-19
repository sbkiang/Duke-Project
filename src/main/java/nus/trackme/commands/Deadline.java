package nus.trackme.commands;
import nus.trackme.parser.ParseDateTime;

import java.time.LocalDate;
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

        String[] parts = by.split(" ");

        boolean upcoming = false;




        if(parts.length == 5){
            //Parse the date string
            ParseDateTime date = new ParseDateTime(by);
            LocalDateTime deadlineDT = date.deadlineDT(by);
            LocalDateTime reminderDT = currentDT.plusDays(reminderDays).plusHours(reminderHours);

            return (!deadlineDT.isAfter(reminderDT) || deadlineDT.equals(reminderDT) || deadlineDT.toLocalDate().isEqual(reminderDT.toLocalDate())) && (deadlineDT.isAfter(currentDT) || deadlineDT.equals(currentDT) || deadlineDT.toLocalDate().isEqual(currentDT.toLocalDate()));

        }
        else if(parts.length == 3){

            LocalDate currentD = LocalDate.now();
            ParseDateTime date = new ParseDateTime(by);
            LocalDate deadlineD = date.deadlineD(by);
            LocalDate reminderD = currentD.plusDays(reminderDays);

            return(!deadlineD.isAfter(reminderD) || deadlineD.isEqual(reminderD) && deadlineD.isAfter(currentD) || deadlineD.isEqual(currentD));

        }
        else{
            return false;
        }


    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " " + "(by: " + by + ")";
    }
}
