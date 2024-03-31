package nus.trackme;

import nus.trackme.common.EnumList;
import nus.trackme.data.LoadFile;
import nus.trackme.parser.ParseCommand;
import nus.trackme.ui.UITask;
import nus.trackme.exception.TrackMeException;

import static nus.trackme.common.Logo.LOGO;

import java.util.Scanner;

public class Main {
    private static final String LINE = "-------------------------------------------------";

    private static UITask task = new UITask();

    private static void load() throws TrackMeException{
        new LoadFile(task);
    }

    private static void start() throws TrackMeException {

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
                    throw new TrackMeException("Please enter the task command!\n" + LINE);
                }

                System.out.println(LINE);

                ParseCommand parseCommand = new ParseCommand(userInput);

                EnumList.CMD userCMD = EnumList.CMD.valueOf(parseCommand.mainCommand());

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
            catch (TrackMeException e){
                System.out.println(e.getMessage());
            }
            catch (IllegalArgumentException e){ /* Commands does not exist in enum*/
                System.out.println("Incorrect Command: " + userInput);
                System.out.println(LINE);
            }
            catch (ArrayIndexOutOfBoundsException e){ /* For mark and unmark without index  */
                System.out.println("Incorrect Command: " + userInput);
                System.out.println("Please put index number. E.g. " + userInput + " 1");
                System.out.println(LINE);
            }
            catch (IndexOutOfBoundsException e){ /*  */
                System.out.println("Task is currently empty. Please add your task first");
                System.out.println(LINE);
            }

        }
    }

    public static void main(String[] args) {
        try {
            Main.load();
            Main.start();
        } catch (TrackMeException e) {
            throw new RuntimeException(e);
        }
    }
}
