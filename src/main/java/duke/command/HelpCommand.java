package duke.command;

import duke.tasklist.TaskList;
import duke.ui.Ui;
import duke.storage.Storage;

public class HelpCommand extends Command {

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.showHelpMessage();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}

