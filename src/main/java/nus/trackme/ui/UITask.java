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

    //private Task[] tasks;
    private final ArrayList<Task> tasks;

    private int size;

    public UITask(){
        tasks = new ArrayList<>();
        size = 0;
    }

    public void listTasks(){
        for(int index=0; index < tasks.size(); index++){
            if(tasks.get(index) == null){
                break;
            }
            System.out.println((index+1) + ". " + tasks.get(index).toString());
        }
    }

    public void markTaskAsDone(int index){
        tasks.get(index).markAsDone();
        new FileIO("MARK", "1", index);
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(tasks.get(index).toString());
    }

    public void unmarkTaskAsDone(int index){
        tasks.get(index).unmarkAsDone();
        new FileIO("UNMARK", "0", index);
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(tasks.get(index).toString());
    }

    public void deleteTask(int index){
        System.out.println("Noted. I've removed this task");
        System.out.println(tasks.get(index).toString());
        tasks.remove(index);
        new FileIO("DELETE", index);
        size--;
        System.out.println("Now you have " + size + " tasks in the list");
    }

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

    public int taskSize(){
        return size;
    }

    public void endTaskProgram(){
        System.out.println("Bye. Hope to see you again");
        System.exit(0);
    }


}
