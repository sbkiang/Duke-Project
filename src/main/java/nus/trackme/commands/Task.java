package nus.trackme.commands;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

/**
 * Represents a task.
 *
 */
public class Task {
    protected String description;
    protected boolean isDone;
    protected Set<String> tags;

    public Task(String description){
        this.description = description;
        this.isDone = false;
        this.tags = new HashSet<>();
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

    public void addTag(String tag){
        tags.add("#"+tag);
    }

    public void removeTag(String tag){
        tags.remove("#"+tag);
    }

    public boolean isUpcoming(LocalDateTime currentDate, int reminderDays, int reminderHours){
        return false;
    }

    public boolean isDone(){
        return isDone;
    }

    public String printTags(){

        String allTag = "";

        for(String tag: tags){
            allTag = allTag+tag;
        }

        return allTag;
    }

    public String toString(){
        if(tags.isEmpty()){
            return "[" + getStatusIcon() +"] " + description;
        }
        else{
            return "[" + getStatusIcon() +"] " + description + " " + printTags();
        }

    }

}
