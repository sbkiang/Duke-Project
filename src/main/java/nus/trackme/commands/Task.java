package nus.trackme.commands;
import java.time.LocalDate;

/**
 * Represents a task.
 *
 */
public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description){
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon(){
        return (isDone ? "X" : " ");
    }

    public void markAsDone(){
       this.isDone = true;
    }

    public void unmarkAsDone(){
        this.isDone = false;
    }

    public boolean isUpcoming(LocalDate currentDate, int reminderDays){
        return false;
    }

    public boolean isDone(){
        return isDone;
    }

    public String toString(){
        return "[" + getStatusIcon() +"] " + description;
    }

}
