package duke.ui;

public class Ui {

    private String output;

    public void showAddTaskMessage(String taskDescription, int listSize) {
        output = "Got it. I've added this task:\n"
                + taskDescription
                + "\nNow you have " + listSize + " tasks in the list.";
    }

    public void showDeleteTaskMessage(String taskDescription, int listSize) {
        output = "Noted. I've removed this task: \n"
                + taskDescription
                + "\nNow you have " + listSize + " tasks in the list.";
    }

    public void showDoneMessage(String taskDescription) {
        output = "Nice! I've marked this task as done: \n" + taskDescription;
    }

    public void showList(String[] taskDescriptionArray) {
        int taskLen = taskDescriptionArray.length;
        if (taskLen == 0) {
            output = "You do not have any tasks in your list";
        } else {
            output = "Here are the tasks in your list: ";
            for (int i = 0; i < taskLen; i ++) {
                String taskDescription = taskDescriptionArray[i];
                output += "\n" + (i + 1) + "." + taskDescription;
            }
        }
    }

    public void showSearchResult(String[] taskDescriptionArray) {
        int taskLen = taskDescriptionArray.length;
        if (taskLen == 0) {
            output = "There are no matching tasks in your list";
        } else {
            output = "Here are the matching tasks in your list: ";
            for (int i = 0; i < taskLen; i ++) {
                String taskDescription = taskDescriptionArray[i];
                output += "\n" + (i + 1) + "." + taskDescription;
            }
        }
    }

    //Old Welcome Message
    /*
    public void showWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String welcomeMessage = "Hello! I'm Duke\n" + "What can I do for you?";

        output = "Hello from\n" + logo;
        output += getLine();
        output += welcomeMessage;
        output += getLine();
    }
    */

    public void showHelpMessage() {
        output = "Welcome to Duke. The following are a list of possible commands: \n" +
                "todo <description> --- Creates a ToDo task\n\n" +
                "deadline <description> /by <dd/mm/yy hhmm> --- Creates a Deadline task\n\n" +
                "event <description> /at <dd/mm/yy hhmm> --- Creates an Event task\n\n" +
                "list --- Lists all tasks\n\n" +
                "done <task number> --- Mark a task as completed\n\n" +
                "delete <task number> --- Delete a task\n\n" +
                "help --- Display the help menu\n\n" +
                "bye --- Exit Duke\n";
    }

    public void showLoadingError() {
        output = "No save file found... creating new save file";
    }

    public void showError(String errorMessage) {
        output = errorMessage;
    }

    public void showExit() {
        output = "Bye. Hope to see you again soon!";
    }

    public String getOutput() {
        return output;
    }

}