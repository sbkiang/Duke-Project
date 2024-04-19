package nus.trackme.commands;

import nus.trackme.parser.ParseDateTime;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Represents event task.
 *
 */
public class Event extends Task {

    protected String from;
    protected String to;
    public Event(String description, String from, String to) {

        super(description);
        this.from = from;
        this.to = to;

    }

    @Override
    public boolean isUpcoming(LocalDateTime currentDT, int reminderDays, int reminderHours){
        String[] parts = to.split(" ");

        if(parts.length == 5){
            //Parse the date string
            ParseDateTime date = new ParseDateTime(to);
            LocalDateTime deadlineDT = date.deadlineDT(to);
            LocalDateTime reminderDT = currentDT.plusDays(reminderDays).plusHours(reminderHours);

            return (!deadlineDT.isAfter(reminderDT) || deadlineDT.equals(reminderDT) || deadlineDT.toLocalDate().isEqual(reminderDT.toLocalDate())) && (deadlineDT.isAfter(currentDT) || deadlineDT.equals(currentDT) || deadlineDT.toLocalDate().isEqual(currentDT.toLocalDate()));

        }
        else if(parts.length == 3){

            LocalDate currentD = LocalDate.now();
            ParseDateTime date = new ParseDateTime(to);
            LocalDate deadlineD = date.deadlineD(to);
            LocalDate reminderD = currentD.plusDays(reminderDays);



            return ((!deadlineD.isAfter(reminderD) || deadlineD.isEqual(reminderD)) && (deadlineD.isAfter(currentD) || deadlineD.isEqual(currentD)));

        }
        else{
            return false;
        }



    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}
