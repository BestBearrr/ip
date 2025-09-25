package paddington.task;

import paddington.ui.PaddingtonException;
import paddington.ui.Ui;

import java.util.ArrayList;

public class TaskList {
    private static final ArrayList<Task> tasks = new ArrayList<>();

    public static ArrayList<Task> getTaskList() {
        return tasks;
    }

    public static int getTaskCount() {
        return tasks.size();
    }

    public static Task getTask(int index) {
        return tasks.get(index);
    }

    public static void addTaskSilently(Task task) {
        tasks.add(task);
    }

    private static void addTask(Task task) {
        tasks.add(task);
        Ui.printAddTask(task.toString(), getTaskCount());
    }

    public static void deleteTask(String input) {
        int taskIndex = Integer.parseInt(input) - 1;
        String taskString = getTask(taskIndex).toString();
        tasks.remove(taskIndex);
        Ui.printDeleteTask(taskString, getTaskCount());
    }

    public static void listAllTasks() {
        // Display all saved tasks
        if (tasks.isEmpty()) {
            System.out.println("No saved tasks.");
            return;
        }

        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.print((i + 1) + ".");
            System.out.println(getTask(i).toString());
        }
    }

    public static void markTask(String input) {
        int taskIndex = Integer.parseInt(input) - 1;
        Task task = getTask(taskIndex);
        task.markAsDone();
        Ui.printMarkTask(task.toString());
    }

    public static void unmarkTask(String input) {
        int taskIndex = Integer.parseInt(input) - 1;
        Task task = getTask(taskIndex);
        task.unmarkAsDone();
        Ui.printUnmarkTask(task.toString());
    }

    public static void addTodo(String input) throws PaddingtonException {
        if (input.isEmpty()) {
            throw PaddingtonException.invalidTodo();
        }

        Todo todo = new Todo(input);
        addTask(todo);
    }

    public static void addEvent(String input) throws PaddingtonException {
        if (input.isEmpty()) {
            throw PaddingtonException.invalidEvent();
        }

        String[] processedInput = input.split(" /from ", 2);
        if (processedInput.length != 2) {
            throw new PaddingtonException("Event task should include start and end time!");
        }

        String[] timings = processedInput[1].split(" /to ", 2);
        if (timings.length != 2) {
            throw new PaddingtonException("Event task should include end time!");
        }

        String description = processedInput[0];
        String from = timings[0];
        String to = timings[1];

        Event event = new Event(description, from, to);
        addTask(event);
    }

    public static void addDeadline(String input) throws PaddingtonException {
        if (input.isEmpty()) {
            throw PaddingtonException.invalidDeadline();
        }

        String[] processedInput = input.split(" /by ", 2);
        if (processedInput.length != 2) {
            throw new PaddingtonException("Deadline task should include due date!");
        }

        String description = processedInput[0];
        String by = processedInput[1];

        Deadline deadline = new Deadline(description, by);
        addTask(deadline);
    }
}
