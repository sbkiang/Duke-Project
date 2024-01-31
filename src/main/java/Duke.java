import java.util.List;
import java.util.Scanner;

public class Duke {

    private static int listNum = 0;

    public static String commandCheck(String inputText){
        String [] words = inputText.split(" ");
        return words[0];
    }

    public static void addTask(String[] list, String inputText){

        Task task = new Task(inputText);

        System.out.println("added: " + inputText);

        list[listNum] = "[" + task.getStatusIcon() + "] " + task.description;
        listNum++;

        return;
    }

    public static void markTask(String[] list, String inputText){
        inputText = inputText.substring(5);
        int x = Integer.parseInt(inputText);

        Task task = new Task(list[x-1].substring(4));
        task.markAsDone();

        list[x-1] = "[" + task.getStatusIcon() + "] " + task.description;

        System.out.println("Nice! I've marked this task as done:");
        System.out.println(list[x-1]);

        return;
    }

    public static void unmarkTask (String[] list, String inputText){
        inputText = inputText.substring(7);
        int x = Integer.parseInt(inputText);

        Task task = new Task(list[x-1].substring(4));

        list[x-1] = "[" + task.getStatusIcon() + "] " + task.description;

        System.out.println("OK, I've marked this task as not done yet:");

        System.out.println(list[x-1]);

        return;
    }

    public static void printList(String [] list){

        int index = 1;

        for(String task: list){
            if(task != null){
                System.out.println(index + ". " + task);
                index++;
            }
        }
        return;
    }

    public static void exitProgram(){
        System.out.println("Bye. Hope to see you again");
        System.exit(0);
    }

    public static void main(String[] args) {

        String [] list = new String[100];
        Scanner input = new Scanner(System.in);
        String inputText = "";

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
                printList(list);
            }
            else if (commandText.equals("blah")) {
                System.out.println("blah");
            }
            else if (commandText.equals("mark")){
                markTask(list, inputText);
            }
            else if (commandText.equals("unmark")){
                unmarkTask(list, inputText);
            }
            else if(commandText.equals("bye")){
                exitProgram();
            }
            else{
                if(!inputText.isBlank()){
                    addTask(list, inputText);
                }
            }

            if(!inputText.isBlank()){
                System.out.println("-------------------------------------------------");
            }

        }
    }
}
