package nus.trackme.ui;

import nus.trackme.commands.Event;
import nus.trackme.commands.Task;
import nus.trackme.commands.ToDo;
import nus.trackme.commands.Deadline;
import nus.trackme.data.FileIO;
import nus.trackme.parser.ParseDateTime;

import java.time.LocalDate;
import java.util.ArrayList;

public class UITask {
    private static final int MAX_SIZE = 100;



    private ArrayList<Task> tasks;

    private int size;

    /**
     * Construct an ArrayList to store user task.
     *
     */
    public UITask(){
        tasks = new ArrayList<>();
        size = 0;
    }

    /**
     * Lists all tasks in the TaskList to the user.
     *
     */
    public void listTasks(){
        if(tasks.size() == 0){
            System.out.println("Currently, there is no task in TaskList.");
        }
        else{
            for(int index=0; index < tasks.size(); index++){
                if(tasks.get(index) == null){
                    break;
                }
                System.out.println((index+1) + ". " + tasks.get(index).toString());
            }
        }
    }

    /**
     * Mark the task as completed.
     *
     * @param index To pinpoint which task to mark.
     */
    public void markTaskAsDone(int index){
        tasks.get(index).markAsDone();
        new FileIO("MARK", "1", index);
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(tasks.get(index).toString());
    }

    /**
     * Unmark the task as uncompleted.
     *
     * @param index To pinpoint which task to unmark.
     */
    public void unmarkTaskAsDone(int index){
        tasks.get(index).unmarkAsDone();
        new FileIO("UNMARK", "0", index);
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(tasks.get(index).toString());
    }

    /**
     * Delete the task in TaskList.
     *
     * @param index To pinpoint which task to delete.
     */
    public void deleteTask(int index){
        System.out.println("Noted. I've removed this task");
        System.out.println(tasks.get(index).toString());
        tasks.remove(index);
        new FileIO("DELETE", index);
        size--;
        System.out.println("Now you have " + size + " tasks in the list");
    }

    /**
     * Add todo task into TaskList.
     *
     * @param task Task description.
     */
    public void toDoTask(String task){
        if(!task.isEmpty()){
            tasks.add(new ToDo(task));

            new FileIO("T", task, "-", "-", "-");
            size++;
            System.out.println("Got it. I've added this task:");
            System.out.println(tasks.get(size-1).toString());
            System.out.println("Now you have " + size + " tasks in the list");
        }
    }

    /**
     * Add deadline task into TaskList.
     *
     * @param task Task description.
     * @param by Task to be completed by when.
     */
    public void deadlineTask(String task, String by){
        ParseDateTime dateTime = new ParseDateTime(by);
        if(dateTime.isCorrect()){
            tasks.add(new Deadline(task, dateTime.toString()));
            new FileIO("D", task, by, "-", "-");
            size++;
            System.out.println("Got it. I've added this task:");
            System.out.println(tasks.get(size-1).toString());
            System.out.println("Now you have " + size + " tasks in the list");
        }
    }

    /**
     * Add event task into TaskList.
     *
     * @param task Task description.
     * @param from Task start from when.
     * @param to Task end to when.
     */
    public void eventTask(String task, String from, String to) {
        ParseDateTime dateTime1 = new ParseDateTime(from);
        ParseDateTime dateTime2 = new ParseDateTime(to);

        if (dateTime1.isCorrect() && dateTime2.isCorrect()) {
            if(dateTime1.isCompare(dateTime2.date(), dateTime2.time())){
                tasks.add(new Event(task, dateTime1.toString(), dateTime2.toString()));
                new FileIO("E", task, "-", from, to);
                size++;
                System.out.println("Got it. I've added this task:");
                System.out.println(tasks.get(size - 1).toString());
                System.out.println("Now you have " + size + " tasks in the list");
            }
        }
    }

    /**
     * Search the specific task in TaskList.
     *
     * @param arg Keyword to search in TaskList.
     */
    public void findTask(String arg){
        if(!arg.isEmpty()) {
            boolean found = false;
            int index = 1;
            for (Task task : tasks) {
                if (task == null) {
                    break;
                } else if (task.toString().contains(arg)) {
                    if (!found) {
                        System.out.println("Here are the matching tasks in your lists");
                        found = true;
                    }
                    System.out.println(index + ". " + task.toString());
                    index++;
                }
            }
            if (!found) {
                System.out.println("Unable to find the matching tasks in your lists");
            }
        }
    }

    /**
     * Remind the user the upcoming task.
     *
     * @param currentDate Current date.
     * @param reminderDays Remaining day left in a week.
     */
    public void remindUpcomingTask(LocalDate currentDate, int reminderDays){
        boolean found = false;

        for(Task task: tasks){
            if((task.toString().contains("[E]") || task.toString().contains("[D]")) && !task.isDone() && task.isUpcoming(currentDate, reminderDays)){
                if(!found){
                    System.out.println("Reminder: Upcoming Task for this week");
                    System.out.println("-------------------------------------------------");
                    found = true;
                }
                System.out.println(task.toString());
            }
        }

        if(found){
            System.out.println("-------------------------------------------------");
        }
    }

    public void tagTask(int index, String tag){
        tasks.get(index).addTag(tag);
        new FileIO("TAG", index, tag);
        System.out.println("Nice! I've tag this task:");
        System.out.println(tasks.get(index).toString());
    }

    public void untagTask(int index, String tag){
        tasks.get(index).removeTag(tag);
        new FileIO("UNTAG", index, tag);
        System.out.println("Nice! I've untag this task:");
        System.out.println(tasks.get(index).toString());
    }

    /**
     * Return the number of Tasks in TaskList.
     *
     * @return
     */
    public int taskSize(){
        return size;
    }

    /**
     * End the program.
     *
     */
    public void endTaskProgram(){
        System.out.println("Bye. Hope to see you again");
        System.exit(0);
    }


}
