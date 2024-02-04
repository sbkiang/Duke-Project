import java.util.Scanner;

public class Duke {

    private static int listNum = 0;
    private static String[] word;

    public static String commandCheck(String inputText){
        String [] words = inputText.split(" ");
        return words[0];
    }

    public static String[] words (String inputText){
        return word = inputText.split(" ");
    }

    public static void addTask(Task[] tasks, String inputText){

        tasks[listNum] = new Task(inputText);

        System.out.println("added: " + inputText);

        listNum++;
    }

    public static void markTask(Task[] tasks, String inputText){
        inputText = inputText.substring(5);
        int x = Integer.parseInt(inputText) - 1;

        tasks[x].markAsDone();

        System.out.println("Nice! I've marked this task as done:");
        System.out.println(tasks[x].toString());
    }

    public static void unmarkTask(Task[] tasks, String inputText){
        inputText = inputText.substring(7);
        int x = Integer.parseInt(inputText) - 1;

        tasks[x].unmarkAsDone();

        System.out.println("Nice! I've marked this task as done:");
        System.out.println(tasks[x].toString());
    }

    public static void toDoTask(Task[] tasks, String inputText){
        tasks[listNum] = new ToDo(inputText);
        System.out.println("Got it. I've added this task:");
        System.out.println(tasks[listNum].toString());
        int x = listNum + 1;
        System.out.println("Now you have " + x + " tasks in the list");
        listNum++;

    }

    public static void deadlineTask(Task[] tasks, String inputText){

        String[] by  = words(inputText);
        tasks[listNum] = new Deadline(inputText, by[4]);
    }

    public static void printList(Task [] tasks){

        int index = 1;

        for(Task task: tasks){
            if(task != null){
                System.out.println(index + ". " + task);
                index++;
            }
        }
    }

    public static void exitProgram(){
        System.out.println("Bye. Hope to see you again");
        System.exit(0);
    }

    public static void main(String[] args) {

        //String [] list = new String[100];
        Scanner input = new Scanner(System.in);
        String inputText;

        Task [] tasks = new Task[100];

        //Design below adapted from "https://patorjk.com/software/taag/#p=display&f=Blocks&t=TrackMe"
        String logo = ".----------------.  .----------------.  .----------------.  .----------------.  .----------------.  .----------------.  .----------------. \n"
                + "| .--------------. || .--------------. || .--------------. || .--------------. || .--------------. || .--------------. || .--------------. |\n"
                + "| |  _________   | || |  _______     | || |      __      | || |     ______   | || |  ___  ____   | || | ____    ____ | || |  _________   | |\n"
                + "| | |  _   _  |  | || | |_   __ \\    | || |     /  \\     | || |   .' ___  |  | || | |_  ||_  _|  | || ||_   \\  /   _|| || | |_   ___  |  | |\n"
                + "| | |_/ | | \\_|  | || |   | |__) |   | || |    / /\\ \\    | || |  / .'   \\_|  | || |   | |_/ /    | || |  |   \\/   |  | || |   | |_  \\_|  | |\n"
                + "| |     | |      | || |   |  __ /    | || |   / ____ \\   | || |  | |         | || |   |  __'.    | || |  | |\\  /| |  | || |   |  _|  _   | |\n"
                + "| |    _| |_     | || |  _| |  \\ \\_  | || | _/ /    \\ \\_ | || |  \\ `.___.'\\  | || |  _| |  \\ \\_  | || | _| |_\\/_| |_ | || |  _| |___/ |  | |\n"
                + "| |   |_____|    | || | |____| |___| | || ||____|  |____|| || |   `._____.'  | || | |____||____| | || ||_____||_____|| || | |_________|  | |\n"
                + "| |              | || |              | || |              | || |              | || |              | || |              | || |              | |\n"
                + "| '--------------' || '--------------' || '--------------' || '--------------' || '--------------' || '--------------' || '--------------' |\n"
                + "'----------------'  '----------------'  '----------------'  '----------------'  '----------------'  '----------------'  '----------------'\n";


        System.out.println(logo);
        System.out.println("Hello! I'm TrackMe");
        System.out.println("What can I do for you?");
        System.out.println("-------------------------------------------------");

        while(true) {
            inputText = input.nextLine();
            inputText = inputText.toLowerCase();

            if(!inputText.isBlank()){
                System.out.println("-------------------------------------------------");
            }

            String commandText = commandCheck(inputText);

            if(commandText.equals("list")){
                printList(tasks);
            }
            else if (commandText.equals("blah")) {
                System.out.println("blah");
            }
            else if (commandText.equals("mark")){
                markTask(tasks, inputText);
            }
            else if (commandText.equals("unmark")){
                unmarkTask(tasks, inputText);
            }
            else if(commandText.equals("bye")){
                exitProgram();
            }
            else if (commandText.equals("todo")){
                inputText = inputText.substring(4); //remove commands
                toDoTask(tasks, inputText);
            }
            else if (commandText.equals("deadline")){
                inputText = inputText.substring(8); //remove commands
                deadlineTask(tasks, inputText);
            }
            else{
                if(!inputText.isBlank()){
                    addTask(tasks, inputText);
                }
            }

            if(!inputText.isBlank()){
                System.out.println("-------------------------------------------------");
            }

        }
    }
}
