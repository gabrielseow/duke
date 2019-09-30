package duke.command;

import java.util.ArrayList;

import duke.tasklist.TaskList;
import duke.ui.Ui;
import duke.storage.Storage;
import duke.task.Task;
import duke.parser.Parser;

public class FindCommand extends Command {
    private String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ArrayList<Task> searchResultTaskList = Parser.findTasksByKeyword(keyword, taskList.getTaskList());
        int searchResultTaskListLen = searchResultTaskList.size();
        String[] taskDescriptionArray = new String[searchResultTaskListLen];
        for (int i = 0; i < searchResultTaskListLen; i++) {
            taskDescriptionArray[i] = searchResultTaskList.get(i).toString();
        }
        ui.showSearchResult(taskDescriptionArray);
    }

    @Override
    public boolean isExit() {
        return false;
    }

}
