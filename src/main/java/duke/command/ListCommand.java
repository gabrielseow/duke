package duke.command;

import duke.tasklist.TaskList;
import duke.task.Task;
import duke.ui.Ui;
import duke.storage.Storage;

import java.util.ArrayList;

public class ListCommand extends Command {

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ArrayList<Task> taskListArray = taskList.getTaskList();
        int taskListSize = taskListArray.size();
        String[] taskDescriptionArray = new String[taskListSize];
        for (int i = 0; i < taskListSize; i++) {
            taskDescriptionArray[i] = taskListArray.get(i).toString();
        }
        ui.showList(taskDescriptionArray);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}