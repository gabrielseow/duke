# User Guide
Welcome to Duke, a simple and easy-to-use chat bot that will help manage your tasks.
## Features 

### Add new tasks
Duke allows you to add new Events, Deadlines or To Do tasks to your list of tasks.

### Mark a task as completed
Once a task is added, you can then update Duke once you have completed the task.

### Find tasks by keyword
You can also filter tasks according to the keyword you input.

### List all tasks
You may also enter a command and Duke will list your current tasks.

### Delete a task
Duke also allows you to delete a task when you no longer need it.

## Usage

### `help` - Display user commands

The help command brings up a list of tasks the user can use in Duke.

Expected outcome:

        Welcome to Duke. The following are a list of possible commands:
        
        todo <description> --- Creates a ToDo task
                
        deadline <description> /by <dd/mm/yy hhmm> --- Creates a Deadline task
                
        event <description> /at <dd/mm/yy hhmm> --- Creates an Event task
                
        list --- Lists all tasks
                
        done <task number> --- Mark a task as completed
                
        delete <task number> --- Delete a task
                
        help --- Display the help menu
                
        bye --- Exit Duke

### `todo` - Add a To Do task

Example of usage: 

`todo CS2100 Tutorial`

Expected outcome:

```
Got it. I have added this task:
 [T][✗] CS2100 Tutorial
 Now you have 1 tasks in the list.
```
 
### `event` - Add an Event task

Example of usage: 

`event Sister's Birthday /at 20/10/19 1900`

Expected outcome:

```
Got it. I have added this task:
 [E][✗] Sister's Birthday (at: 20th of October 2019, 7pm)
 Now you have 1 tasks in the list.
 ```
 
### `deadline` - Add a Deadline task

Example of usage: 

`deadline CS2103T Post Lecture Quiz /by 30/9/19 2359`

Expected outcome:

```
Got it. I have added this task:
 [D][✗] CS2103T Post Lecture Quiz (by: 30th of September 2019, 11.59pm)
 Now you have 1 tasks in the list.
 ```

### `done` - Mark a task as complete

The user also inputs the number of the task he completed.

Example of usage: 

`done 2`

Expected outcome:

```
Nice! I've marked this task as done:
 [D][✓] CS2103T Post Lecture Quiz (by: 30th of September 2019, 11.59pm)
 ```
 
### `find` - Find tasks containing a keyword

The user also inputs the keyword that he is searching for.

Example of usage: 

`find CS2100`

Expected outcome:
```
Here are the matching tasks in your list:
 1.[T][✗] CS2100 Tutorial
 ```

### `list` - List all tasks in Duke

Expected outcome:

```
Here are the matching tasks in your list:
 1.[T][✗] CS2100 Tutorial
 2.[E][✗] Sister's Birthday (at: 20th of October 2019, 7pm)
 3.[D][✓] CS2103T Post Lecture Quiz (by: 30th of September 2019, 11.59pm)
 ```
 
### `delete` - Delete a task in Duke

The user also inputs the number of the task to delete.

Example of usage: 

`delete 1`

Expected outcome:

```
Noted. I've removed this task:
[E][✗] Sister's Birthday (at: 20th of October 2019, 7pm)
Now you have 2 tasks in the list.
```

### `bye` - Close Duke

Exits the Duke application.
