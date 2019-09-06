import java.io.IOException;

public class Parser {
    public Parser() {

    }

    public static Command parse(String userInput) throws DukeException {
        String[] newTaskSplit = userInput.split(" ");
        String taskType = newTaskSplit[0];

        switch(taskType) {
        case "list":
            return new  ListCommand();
        case "bye":
            return new ExitCommand();
        case "done":
            int completedTaskNum = parseDoneCommand(newTaskSplit);
            return new DoneCommand(completedTaskNum);
        case "delete":
            int deletionNum = parseDeleteCommand(newTaskSplit);
            return new DeleteCommand(deletionNum);
        case "event":
            Task newEvent = parseAddEventCommand(newTaskSplit);
            return new AddCommand(newEvent);
        case "deadline":
            Task newDeadline = parseAddDeadlineCommand(newTaskSplit);
            return new AddCommand(newDeadline);
        case "todo":
            Task newToDo = parseAddToDoCommand(newTaskSplit);
            return new AddCommand(newToDo);
        default:
            throw new DukeIllegalArgumentException();
        }
    }

    private static int parseDoneCommand(String[] newTaskSplit) {
        int completedTaskNum = Integer.parseInt(newTaskSplit[1]) - 1;
        return completedTaskNum;
    }

    private static int parseDeleteCommand(String[] newTaskSplit) throws DukeDeleteIllegalArgumentException {
        int newTaskLen = newTaskSplit.length;
        if (newTaskLen < 2) {
            throw new DukeDeleteIllegalArgumentException("You have not entered a number for deletion");
        } else if (newTaskLen > 2) {
            throw new DukeDeleteIllegalArgumentException("You have entered too many arguments for deletion");
        } else {
            try {
                int deletionNum = Integer.parseInt(newTaskSplit[1]) - 1;
                return deletionNum;
            } catch (NumberFormatException e) {
                throw new DukeDeleteIllegalArgumentException("Please enter a valid number for deletion");
            } catch (IndexOutOfBoundsException e) {
                throw new DukeDeleteIllegalArgumentException("Please enter a valid number within the range");
            }
        }
    }

    private static ToDo parseAddToDoCommand(String[] newTaskSplit) throws DukeToDoIllegalArgumentException {
        try {
            int newTaskLen = newTaskSplit.length;
            String description = newTaskSplit[1];
            for (int i = 2; i < newTaskLen; i++) {
                description += " " + newTaskSplit[i];
            }
            ToDo newToDo = new ToDo(description);
            return newToDo;
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeToDoIllegalArgumentException();
        }
    }

    private static Deadline parseAddDeadlineCommand(String[] newTaskSplit) throws DukeDeadlineIllegalArgumentException {
        try {
            int newTaskLen = newTaskSplit.length;
            boolean foundDeadline = false;
            String description = newTaskSplit[1];
            String deadlineTimeString = "";
            for (int i = 2; i < newTaskLen; i++) {
                if (foundDeadline) {
                    if (i == newTaskLen - 1) {
                        deadlineTimeString += newTaskSplit[i];
                    } else {
                        deadlineTimeString += newTaskSplit[i] + " ";
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
                DateTime deadlineTime = convertDateTime(deadlineTimeString);
                Deadline newDeadline = new Deadline(description, deadlineTime);
                return newDeadline;
            } else {
                throw new DukeDeadlineIllegalArgumentException("deadline");
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeDeadlineIllegalArgumentException("description");
        }
    }

    private static Event parseAddEventCommand(String[] newTaskSplit) throws DukeEventIllegalArgumentException {
        try {
            int newTaskLen = newTaskSplit.length;
            boolean foundEvent = false;
            String description = newTaskSplit[1];
            String eventTimeString = "";
            for (int i = 2; i < newTaskLen; i++) {
                if (foundEvent) {
                    if (i == newTaskLen - 1) {
                        eventTimeString += newTaskSplit[i];
                    } else {
                        eventTimeString += newTaskSplit[i] + " ";
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
                DateTime eventTime = convertDateTime(eventTimeString);
                Event newEvent = new Event(description, eventTime);
                return newEvent;
            } else {
                throw new DukeEventIllegalArgumentException("event timing");
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeEventIllegalArgumentException("description");
        }
    }

    public static DateTime convertDateTime(String dateTimeString) {
        String[] dateTimeStringSplit = dateTimeString.split(" ");
        String[] dateStringSplit = dateTimeStringSplit[0].split("/");
        int day = Integer.parseInt(dateStringSplit[0]);
        int month = Integer.parseInt(dateStringSplit[1]);
        int year = Integer.parseInt(dateStringSplit[2]);
        int time = Integer.parseInt(dateTimeStringSplit[1]);
        return new DateTime(day, month, year, time, dateTimeString);
    }

    public static Task parseSaveData(String newTaskString) throws DukeSaveFileCorruptedError {
        String[] newTaskSplit = newTaskString.split(" \\| ");
        String taskType = newTaskSplit[0];
        boolean taskIsDone = Integer.parseInt(newTaskSplit[1]) == 1
                ? true
                : false;
        String description = newTaskSplit[2];
        Task newTask;
        switch(taskType) {
        case "T":
            newTask = new ToDo(description);
            break;
        case "D":
            DateTime deadlineTime = convertDateTime(newTaskSplit[3]);
            newTask = new Deadline(description, deadlineTime);
            break;
        case "E":
            DateTime eventTime = convertDateTime(newTaskSplit[3]);
            newTask = new Event(description, eventTime);
            break;
        default:
            throw new DukeSaveFileCorruptedError();
        }
        if (newTask != null) {
            if (taskIsDone) {
                newTask.taskComplete();
            }
            return newTask;
        } else  {
            throw new DukeSaveFileCorruptedError();
        }
    }
}