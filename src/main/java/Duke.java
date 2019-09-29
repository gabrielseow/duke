import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    private static final ArrayList<String> taskList = new ArrayList<String>();
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        printGreetingMessage();

        while(sc.hasNextLine()) {
            String input = sc.nextLine();

            switch(input) {
            case "bye":
                printExitMessage();
                return;

            case "list":
                printListMessage();
                break;

            default:
                printAddMessage(input.toLowerCase());
                break;
            }
        }
    }


    public static void addBorder(String input) {

        String border = "__________________________________________________________";

        System.out.print(border + "\n\n" + input + "\n" + border + "\n\n");
    }


    public static void printGreetingMessage() {
        addBorder("Hello! I'm Duke\n" + "What can I do for you?");
    }


    public static void printAddMessage(String input) {
        addBorder("added: " + input);
        taskList.add(input);
    }

    public static void printListMessage() {
        String output = "";
        int taskListLen = taskList.size();
        for (int i = 0; i < taskListLen; i ++) {
            if (i == 0) {
                output += i + 1 + ". " + taskList.get(i);
            } else {
                output += "\n" + i + 1 + ". " + taskList.get(i);
            }
        }
        addBorder(output);
    }

    public static void printExitMessage() {
        addBorder("Bye. Hope to see you again soon!");
    }

}