package Task;

import commands.Event;
import commands.Task;
import commands.ToDo;
import commands.Deadline;

public class TaskList {
    private static final int MAX_SIZE = 100;
    private Task[] tasks;
    private int size;

    public TaskList(){
        tasks = new Task[MAX_SIZE];
        size = 0;
    }

    public void addTask(String task){
        tasks[size++] = new Task(task);
        System.out.println("added: " + task);
    }

    public void listTasks(){
        for(int index=0; index < tasks.length; index++){
            if(tasks[index] == null){
                break;
            }
            System.out.println((index+1) + ". " + tasks[index].toString());
        }
    }

    public void markTaskAsDone(int index){
        tasks[index].markAsDone();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(tasks[index].toString());
    }

    public void unmarkTaskAsDone(int index){
        tasks[index].unmarkAsDone();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(tasks[index].toString());
    }

    public void toDoTask(String task){
        tasks[size++] = new ToDo(task);
        System.out.println("Got it. I've added this task:");
        System.out.println(tasks[size-1].toString());
        System.out.println("Now you have " + size + " tasks in the list");
    }

    public void deadlineTask(String task, String by){
        tasks[size++] = new Deadline(task, by);
        System.out.println("Got it. I've added this task:");
        System.out.println(tasks[size-1].toString());
        System.out.println("Now you have " + size + " tasks in the list");
    }

    public void eventTask(String task, String from, String to){
        tasks[size++] = new Event(task, from, to);
        System.out.println("Got it. I've added this task:");
        System.out.println(tasks[size-1].toString());
        System.out.println("Now you have " + size + " tasks in the list");
    }

    public void endTaskProgram(){
        System.out.println("Bye. Hope to see you again");
        System.exit(0);
    }

}
