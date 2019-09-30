import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    private static ArrayList<Task> tasks = new ArrayList<Task>();
    private static int tasksCount = 0;

    public static void main(String[] args) throws DukeException{
        Scanner sc = new Scanner(System.in);

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        printGreetingMessage();

        while(sc.hasNextLine()) {
            String newTaskLine = sc.nextLine();
            String[] newTaskSplit = newTaskLine.split(" ");
            String taskType = newTaskSplit[0];

            switch(taskType) {
            case "list":
                printList();
                break;
            case "bye":
                printExitMessage();
                return;
            case "done":
                int completedTaskNum = Integer.parseInt(newTaskSplit[1]) - 1;
                Task completedTask = tasks.get(completedTaskNum);
                printAndEvaluateTaskDone(completedTask);
                break;
            case "event":
                try {
                    Event newEvent = parseAndEvaluateEvent(newTaskSplit);
                    addEvent(newEvent);
                } catch (DukeException e) {
                    addBorder(e.getMessage());
                }
                break;
            case "deadline":
                try {
                    Deadline newDeadline = parseAndEvaluateDeadline(newTaskSplit);
                    addDeadline(newDeadline);
                } catch (DukeException e) {
                    addBorder(e.getMessage());
                }
                break;
            case "todo":
                try {
                    ToDo newToDo = parseAndEvaluateToDo(newTaskSplit);
                    addToDo(newToDo);
                } catch (DukeException e) {
                    addBorder(e.getMessage());
                }
                break;
            case "delete":
                Integer index = parseAndEvaluateDelete(newTaskSplit);
                if (index != null) {
                    deleteTask(index);
                }
                break;
            default:
                addBorder(new DukeIllegalArgumentException().getMessage());
                break;
            }
        }
    }

    static void addBorder(String input) {
        String border = "__________________________________________________________";
        System.out.print(border + "\n" + input + "\n" + border + "\n\n");
    }

    static void printGreetingMessage() {
        addBorder("Hello! I'm Duke\n" + "What can I do for you?");
    }

    static void printAndEvaluateTaskDone(Task completedTask) {
        completedTask.taskComplete();
        addBorder("Nice! I've marked this task as done: \n" + completedTask.toString());
    }

    static void printExitMessage() {
        addBorder("Bye. Hope to see you again soon!");
    }

    static Integer parseAndEvaluateDelete(String[] newTaskSplit) {
        return Integer.parseInt(newTaskSplit[1]);
    }

    static void deleteTask(int index) {
        if (index >= 1 && index <= tasksCount) {
            tasksCount --;
            Task removedTask = tasks.get(index - 1);
            String output = "Noted. I've removed this task:\n" +
                    removedTask.toString() + "\n" +
                    "Now you have " +
                    tasksCount +
                    " tasks in the list.";
            addBorder(output);
        }
    }

    static ToDo parseAndEvaluateToDo(String[] newTaskSplit) throws DukeException {
        try {
            int newTaskLen = newTaskSplit.length;
            String description = newTaskSplit[1];
            for (int i = 2; i < newTaskLen; i++) {
                description += " " + newTaskSplit[i];
            }
            return new ToDo(description);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeToDoIllegalArgumentException();
        }
    }
    static void addToDo(ToDo newToDo) {
        tasks.add(newToDo);
        tasksCount++;
        String output = "Got it. I've added this task:\n"
                + newToDo.toString()
                + "\nNow you have " + tasksCount + " tasks in the list.";
        addBorder(output);
    }

    static Deadline parseAndEvaluateDeadline(String[] newTaskSplit) throws DukeException {
        try {
            int newTaskLen = newTaskSplit.length;
            boolean foundDeadline = false;
            String description = newTaskSplit[1];
            String by = "";
            for (int i = 2; i < newTaskLen; i++) {
                if (foundDeadline) {
                    if (i == newTaskLen - 1) {
                        by += newTaskSplit[i];
                    } else {
                        by += newTaskSplit[i] + " ";
                    }
                } else {
                    if (newTaskSplit[i].equals("/by")) {
                        foundDeadline = true;
                    } else {
                        description += " " + newTaskSplit[i];
                    }
                }
            }
            if (foundDeadline) {
                return new Deadline(description, by);
            } else {
                throw new DukeDeadlineIllegalArgumentException("deadline");
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeDeadlineIllegalArgumentException("description");
        }
    }

    static void addDeadline(Deadline newDeadline) {
        tasks.add(newDeadline);
        tasksCount++;
        String output = "Got it. I've added this task:\n"
                + newDeadline.toString()
                + "\nNow you have " + tasksCount + " tasks in the list.";
        addBorder(output);
    }

    static Event parseAndEvaluateEvent(String[] newTaskSplit) throws DukeException {
        int newTaskLen = newTaskSplit.length;
        try {
            boolean foundEvent = false;
            String description = newTaskSplit[1];
            String at = "";
            for (int i = 2; i < newTaskLen; i++) {
                if (foundEvent) {
                    if (i == newTaskLen - 1) {
                        at += newTaskSplit[i];
                    } else {
                        at += newTaskSplit[i] + " ";
                    }
                } else {
                    if (newTaskSplit[i].equals("/at")) {
                        foundEvent = true;
                    } else {
                        description += " " + newTaskSplit[i];
                    }
                }
            }
            if (foundEvent) {
                return new Event(description, at);
            } else {
                throw new DukeEventIllegalArgumentException("timing");
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeEventIllegalArgumentException("description");
        }
    }

    static void addEvent(Event newEvent) {
        tasks.add(newEvent);
        tasksCount++;
        String output = "Got it. I've added this task:\n"
                + newEvent.toString()
                + "\nNow you have " + tasksCount + " tasks in the list.";
        addBorder(output);
    }

    static void printList() {
        String str = "";

        for (int i = 1; i < tasksCount + 1; i++) {
            String newTask = tasks.get(i-1).toString();
            if (i == tasksCount) {
                str += i + ". " + newTask;
            } else {
                str += i + ". " + newTask + "\n";
            }
        }

        addBorder(str);
    }

}