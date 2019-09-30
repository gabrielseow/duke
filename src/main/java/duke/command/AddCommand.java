package duke.command;

import duke.tasklist.TaskList;
import duke.task.Task;
import duke.ui.Ui;
import duke.storage.Storage;

public class AddCommand extends Command {
    private Task newTask;

    public AddCommand(Task newTask) {
        this.newTask = newTask;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.addTask(newTask);
        String taskDescription = newTask.toString();
        ui.showAddTaskMessage(taskDescription, taskList.getListSize());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}