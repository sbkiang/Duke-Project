import java.util.Scanner;
public class Duke {

    public static void printList(){
        System.out.println("List");
        return;
    }

    public static void exitProgram(){
        System.out.println("Bye. Hope to see you again");
        System.exit(0);
    }

    public static void main(String[] args) {

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
        while(true) {
            inputText = input.nextLine();
            inputText = inputText.toLowerCase();

            System.out.println("");

            switch (inputText) {
                case "list":
                    printList();
                    break;
                case "bye":
                    exitProgram();
                default:
                    System.out.println("Sorry I don't understand it.");
            }

            System.out.println("");

        }

    }
}
