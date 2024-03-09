import Parse.ParseCommand;
import Task.TaskList;
import Exception.DukeException;


import java.util.Scanner;
import static common.Logo.LOGO;

public class Duke {
    private static final String LINE = "-------------------------------------------------";

    private static void start() throws DukeException {

        TaskList task = new TaskList();
        Scanner input = new Scanner(System.in);
        String userInput = null;


        System.out.println(LOGO);
        System.out.println("Hello! I'm TrackMe");
        System.out.println("What can I do for you?");
        System.out.println(LINE);

        while(true) {

            try{
                userInput = input.nextLine();

                //if the input is empty
                if(userInput.isEmpty()){
                    throw new DukeException("Please enter the task command!\n" + LINE);
                }

                System.out.println(LINE);

                ParseCommand parseCommand = new ParseCommand(userInput);

                Commands.CMD userCMD = Commands.CMD.valueOf(parseCommand.mainCommand());

                String arg;
                String [] parsedInput;

                switch (userCMD){
                    case LIST:
                        task.listTasks();
                        break;
                    case TODO:
                        arg = parseCommand.TodoCommand();
                        task.toDoTask(arg);
                        break;
                    case EVENT:
                        parsedInput = parseCommand.EventCommand();
                        task.eventTask(parsedInput[0], parsedInput[1], parsedInput[2]);
                        break;
                    case DEADLINE:
                        parsedInput = parseCommand.DeadlineCommand();
                        task.deadlineTask(parsedInput[0], parsedInput[1]);
                        break;
                    case MARK:
                        task.markTaskAsDone(Integer.parseInt(parseCommand.indexCommand()) - 1);
                        break;
                    case UNMARK:
                        task.unmarkTaskAsDone(Integer.parseInt(parseCommand.indexCommand()) - 1);
                        break;
                    case DELETE:
                        task.deleteTask(Integer.parseInt(parseCommand.indexCommand()) - 1);
                        break;
                    case BYE:
                        task.endTaskProgram();
                        break;
                }

                System.out.println(LINE);

            }
            catch (DukeException e){
                System.out.println(e.getMessage());
            }
            catch (IllegalArgumentException e){
                System.out.println("Incorrect Command: " + userInput);
                System.out.println(LINE);
            }

        }
    }

    public static void main(String[] args) {
        try {
            Duke.start();
        } catch (DukeException e) {
            throw new RuntimeException(e);
        }
    }
}
