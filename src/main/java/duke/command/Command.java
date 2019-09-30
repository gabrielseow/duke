package duke.command;

import duke.tasklist.TaskList;
import duke.ui.Ui;
import duke.storage.Storage;

/**
 * Encapsulates commands given by the user. Handles logic and interactions with TaskList, Ui and Storage.
 */
public abstract class Command {

    /**
     * Abstract method to handle interactions with TaskList, Ui and Storage
     * @param taskList TaskList of application
     * @param ui Ui of application
     * @param storage Storage of application
     */
    public abstract void execute(TaskList taskList, Ui ui, Storage storage);

    /**
     * Getter method for determining if the user is terminating the application
     * @return boolean to determine if the command will terminate the application
     */
    public abstract boolean isExit();
}