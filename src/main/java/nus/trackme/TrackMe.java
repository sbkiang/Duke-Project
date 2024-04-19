package nus.trackme;

import nus.trackme.common.CommandType;
import nus.trackme.data.LoadFile;
import nus.trackme.parser.ParseCommand;
import nus.trackme.ui.UITask;
import nus.trackme.exception.TrackMeException;

import static nus.trackme.common.Logo.LOGO;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.Scanner;


/**
 * The project is implements an application for task tracking. It is providing users with a convenient and efficient
 * way to manage their tasks. The application will allow users to add, delete, mark as done and search for task.
 * With its intuitive and user-friendly interface, this task tracking application will streamline task management for
 * users and helping them stay organized and productive.
 *
 * @author  Kiang Siong Boon
 * @version 1.0
 * @since   2024-01-29
 */
public class TrackMe {
    private static final String LINE = "-------------------------------------------------";

    private static UITask command = new UITask();


    private static void load() throws TrackMeException{
        new LoadFile(command);
    }

    private static void start() throws TrackMeException {

        Scanner input = new Scanner(System.in);
        String userInput = null;

        if(command.taskSize() != 0){
            /* Get the current date and time */
            LocalDateTime currentDT = LocalDateTime.now();

            /* Calculate the end of the current week (Sunday) */
            LocalDateTime endOfWeek = currentDT.with(TemporalAdjusters.next(DayOfWeek.SUNDAY));

            /* Calculate the number of days and time left in the current week */
            long daysLeftInWeek = ChronoUnit.DAYS.between(currentDT, endOfWeek);
            long hoursLeftInWeek = ChronoUnit.HOURS.between(currentDT, endOfWeek);

            /* Calculate the reminder period based on the days left in the current week */
            int reminderDays = (int) Math.min(daysLeftInWeek, 7); // Maximum reminder period is 7 days
            int reminderHours = (int) Math.min(hoursLeftInWeek, 7 * 24); // Maximum reminder period in hours is 7 days * 24 hours/day

            command.remindUpcomingTask(currentDT, reminderDays, reminderHours);
        }

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

                CommandType.CMD userCMD = CommandType.CMD.valueOf(parseCommand.mainCommand());

                String arg;
                String [] parsedInput;

                switch (userCMD){
                    case LIST:
                        command.listTasks();
                        break;
                    case TODO:
                        arg = parseCommand.todoCommand();
                        command.toDoTask(arg);
                        break;
                    case EVENT:
                        parsedInput = parseCommand.EventCommand();
                        command.eventTask(parsedInput[0], parsedInput[1], parsedInput[2]);
                        break;
                    case DEADLINE:
                        parsedInput = parseCommand.DeadlineCommand();
                        command.deadlineTask(parsedInput[0], parsedInput[1]);
                        break;
                    case MARK:
                        command.markTaskAsDone(Integer.parseInt(parseCommand.indexCommand()) - 1);
                        break;
                    case UNMARK:
                        command.unmarkTaskAsDone(Integer.parseInt(parseCommand.indexCommand()) - 1);
                        break;
                    case DELETE:
                        command.deleteTask(Integer.parseInt(parseCommand.indexCommand()) - 1);
                        break;
                    case FIND:
                        arg = parseCommand.findCommand();
                        command.findTask(arg);
                        break;
                    case TAG:
                        parsedInput = parseCommand.tagCommand();
                        command.tagTask(Integer.parseInt(parsedInput[0]) - 1, parsedInput[1]);
                        break;
                    case UNTAG:
                        parsedInput = parseCommand.untagCommand();
                        command.untagTask(Integer.parseInt(parsedInput[0]) - 1, parsedInput[1]);
                        break;
                    case BYE:
                        command.endTaskProgram();
                        break;
                }
                System.out.println(LINE);
            } catch (TrackMeException e){
                System.out.println(e.getMessage());
            } catch (IllegalArgumentException e){ /* Commands does not exist in enum*/
                System.out.println("Invalid Command: " + userInput);
                System.out.println(LINE);
            } catch (ArrayIndexOutOfBoundsException e){ /* For mark and unmark without index  */
                System.out.println("Incomplete Command: " + userInput);
                System.out.println("Please put index number. E.g. " + userInput + " 1");
                System.out.println(LINE);
            } catch (IndexOutOfBoundsException e){ /*  */
                if(command.taskSize() == 0){
                    System.out.println("Task is currently empty. Please add your task first.");
                }
                else{
                    System.out.println("Please choose valid index in current task list.");
                    System.out.println(LINE);
                    command.listTasks();
                }
                System.out.println(LINE);
            } catch (NullPointerException e){
                System.out.println("Incomplete Command: " + userInput);
                if(userInput.contains("todo")){
                    System.out.println("Eg. todo borrow book");
                }
                else if(userInput.contains("deadline")){
                    System.out.println("E.g. deadline return book /by 07/04/2024 1200");
                }
                else if(userInput.contains("event")){
                    System.out.println("E.g. event loan book /from 07/04/2024 1200 /to 08/04/2024 1200");
                }
                else if(userInput.contains("find")){
                    System.out.println("E.g. find book");
                }
                System.out.println(LINE);
            }
        }
    }

    /**
     * Main method to start the TrackMe application.
     * Calls the load() method to begin the application.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        try {
            System.out.println(LOGO);
            System.out.println("Hello! I'm TrackMe");
            TrackMe.load();
            TrackMe.start();
        } catch (TrackMeException e) {
            throw new RuntimeException(e);
        }
    }
}
