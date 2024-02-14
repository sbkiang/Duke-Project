import java.util.Scanner;
import static common.Logo.LOGO;

public class Duke {
    private static final String LINE = "-------------------------------------------------";
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String inputText;
        TaskList task = new TaskList();

        System.out.println(LOGO);
        System.out.println("Hello! I'm TrackMe");
        System.out.println("What can I do for you?");
        System.out.println(LINE);

        while(true) {
            inputText = input.nextLine();
            inputText = inputText.toLowerCase();

            if(!inputText.isBlank()){
                System.out.println(LINE);
            }

            String [] command = inputText.split("\\s+");

            if(command[0].equals("list")){
                task.listTasks();
            }
            else if (command[0].equals("blah")) {
                System.out.println("blah");
            }
            else if (command[0].equals("mark")){
                task.markTaskAsDone(1);
            }
            else if (command[0].equals("unmark")){
                task.unmarkTaskAsDone(1);
            }
            else if(command[0].equals("bye")){
                task.endTaskProgram();
            }
            else if (command[0].equals("todo")){
                inputText = inputText.substring(4); //remove commands
                task.toDoTask(inputText);
            }
            else if (command[0].equals("deadline")){
                inputText = inputText.substring(8); //remove commands
                task.deadlineTask(inputText, inputText);
            }
            else if (command[0].equals("event")){
                task.eventTask(inputText, inputText, inputText);
            }
            else{
                if(!inputText.isBlank()){
                    task.addTask(inputText);
                }
            }

            if(!inputText.isBlank()){
                System.out.println(LINE);
            }

        }
    }
}
