package duke.command;

import duke.tasklist.TaskList;
import duke.ui.Ui;
import duke.storage.Storage;

public class ExitCommand extends Command {

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.showExit();
        storage.saveTasks(taskList.getTaskList());
    }

    @Override
    public boolean isExit() {
        return true;
    }
}