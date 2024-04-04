package nus.trackme.commands;

import nus.trackme.parser.ParseDateTime;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Event extends Task {

    protected String from;
    protected String to;
    public Event(String description, String from, String to) {

        super(description);
        this.from = from;
        this.to = to;

    }

    public boolean isUpcoming(LocalDate currentDate, int reminderDays){
        //Parse the date string
        ParseDateTime date = new ParseDateTime(to);
        LocalDate deadlineDate = date.deadlineDate(to);
        LocalDate reminderDate = currentDate.plusDays(reminderDays);
        return !deadlineDate.isAfter(reminderDate) && deadlineDate.isAfter(currentDate);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}
