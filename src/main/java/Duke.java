import Parse.ParseCommand;
import Task.TaskList;

import java.util.Scanner;
import static common.Logo.LOGO;

public class Duke {
    private static final String LINE = "-------------------------------------------------";

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String inputCommand;
        TaskList task = new TaskList();

        System.out.println(LOGO);
        System.out.println("Hello! I'm TrackMe");
        System.out.println("What can I do for you?");
        System.out.println(LINE);

        while(true) {
            inputCommand = input.nextLine();
            inputCommand = inputCommand.toLowerCase();

            if(!inputCommand.isBlank()){
                System.out.println(LINE);
            }

            ParseCommand parseCommand = new ParseCommand(inputCommand);
            String arg, cmd;
            String [] parsedInput;

            if(inputCommand.startsWith("list")){
                task.listTasks();
            }
            else if (inputCommand.startsWith("blah")) {
                System.out.println("blah");
            }
            else if (inputCommand.startsWith("mark")){
                task.markTaskAsDone(1);
            }
            else if (inputCommand.startsWith("unmark")){
                task.unmarkTaskAsDone(1);
            }
            else if(inputCommand.startsWith("bye")){
                task.endTaskProgram();
            }
            else if (inputCommand.startsWith("todo")){
                arg = parseCommand.TodoCommand();
                task.toDoTask(arg);
            }
            else if (inputCommand.startsWith("deadline")){
                parsedInput = parseCommand.DeadlineCommand();
                task.deadlineTask(parsedInput[0], parsedInput[1]);
            }
            else if (inputCommand.startsWith("event")){
                parsedInput = parseCommand.EventCommand();
                task.eventTask(parsedInput[0], parsedInput[1], parsedInput[2]);
            }
            else{
                if(!inputCommand.isBlank()){
                    task.addTask(inputCommand);
                }
            }

            if(!inputCommand.isBlank()){
                System.out.println(LINE);
            }

        }
    }
}
