package duke.task;

/**
 * Encapsulates a simple Task and stores the description of the Task and whether it has been completed.
 */
public abstract class Task {

    protected String description;
    protected boolean taskIsDone;
    protected int taskIsDoneState;

    /**
     * Creates a simple incomplete Task with a description
     * @param description description of the Task
     */
    public Task(String description) {
        this.description = description;
        this.taskIsDone = false;
        this.taskIsDoneState = 0;
    }

    /**
     * Marks a Task as completed.
     */
    public void taskComplete() {
        taskIsDone = true;
        taskIsDoneState = 1;
    }

    /**
     * Converts a Task into a String representing its format when saved.
     * @return String representing information about the Task
     */
    public abstract String toSaveFormat();

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        String output;
        if (taskIsDone) {
            output = "Complete";
        } else {
            output = "Incomplete";
        }
        output += " " + description;
        return output;
    }
}