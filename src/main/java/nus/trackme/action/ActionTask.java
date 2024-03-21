package nus.trackme.action;

import nus.trackme.commands.Event;
import nus.trackme.commands.Task;
import nus.trackme.commands.ToDo;
import nus.trackme.commands.Deadline;
import nus.trackme.data.FileIO;

import java.util.ArrayList;

public class ActionTask {
    private static final int MAX_SIZE = 100;

    //private Task[] tasks;
    private final ArrayList<Task> tasks;

    private int size;

    public ActionTask(){
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
        tasks.add(new ToDo(task));
        new FileIO("T", task, "-", "-", "-");
        size++;
        System.out.println("Got it. I've added this task:");
        System.out.println(tasks.get(size-1).toString());
        System.out.println("Now you have " + size + " tasks in the list");
    }

    public void deadlineTask(String task, String by){
        tasks.add(new Deadline(task, by));
        new FileIO("D", task, by, "-", "-");
        size++;
        System.out.println("Got it. I've added this task:");
        System.out.println(tasks.get(size-1).toString());
        System.out.println("Now you have " + size + " tasks in the list");
    }

    public void eventTask(String task, String from, String to){
        tasks.add(new Event(task, from, to));
        new FileIO("E", task, "-", from, to);
        size++;
        System.out.println("Got it. I've added this task:");
        System.out.println(tasks.get(size-1).toString());
        System.out.println("Now you have " + size + " tasks in the list");
    }

    public void endTaskProgram(){
        System.out.println("Bye. Hope to see you again");
        System.exit(0);
    }


}
