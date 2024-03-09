package Task;

import commands.Event;
import commands.Task;
import commands.ToDo;
import commands.Deadline;

import java.util.ArrayList;

public class TaskList {
    private static final int MAX_SIZE = 100;
    //private Task[] tasks;
    private ArrayList<Task> tasks;

    private int size;

    public TaskList(){
        //tasks = new Task[MAX_SIZE];
        tasks = new ArrayList<>();
        size = 0;
    }

    /* Remove from it
    public void addTask(String task){
        tasks[size++] = new Task(task);
        System.out.println("added: " + task);
    }
    */

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
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(tasks.get(index).toString());
    }

    public void unmarkTaskAsDone(int index){
        tasks.get(index).unmarkAsDone();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(tasks.get(index).toString());
    }

    public void deleteTask(int index){
        System.out.println("Noted. I've removed this task");
        System.out.println(tasks.get(index).toString());
        tasks.remove(index);
        size--;
        System.out.println("Now you have " + size + " tasks in the list");
    }

    public void toDoTask(String task){
        tasks.add(new ToDo(task));
        size++;
        System.out.println("Got it. I've added this task:");
        System.out.println(tasks.get(size-1).toString());
        System.out.println("Now you have " + size + " tasks in the list");
    }

    public void deadlineTask(String task, String by){
        tasks.add(new Deadline(task, by));
        size++;
        System.out.println("Got it. I've added this task:");
        System.out.println(tasks.get(size-1).toString());
        System.out.println("Now you have " + size + " tasks in the list");
    }

    public void eventTask(String task, String from, String to){
        tasks.add(new Event(task, from, to));
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
