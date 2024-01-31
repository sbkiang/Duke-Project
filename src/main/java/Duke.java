import java.util.List;
import java.util.Scanner;

public class Duke {

    private static int listNum = 0;

    public static void addList(String[] list, String inputText){
        list[listNum] = inputText;
        System.out.println("added: " + inputText);
        listNum++;
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

            System.out.println("-------------------------------------------------");

            if(inputText.contains("list")){
                printList(list);
            }
            else if (inputText.contains("blah")) {
                System.out.println("blah");
            }
            else if(inputText.contains("bye")){
                exitProgram();
            }
            else{
                if(!inputText.isBlank()){
                    addList(list, inputText);
                }
            }

            System.out.println("-------------------------------------------------");

        }
    }
}
