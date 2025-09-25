package paddington.task;

import paddington.storage.Storage;
import paddington.ui.PaddingtonException;

import java.util.ArrayList;

public class TaskList {
    private static ArrayList<Task> tasks = new ArrayList<>();

    private static void printTask(int index) {
        System.out.println(tasks.get(index).toString());
    }

    private static void printAddedTask() {
        System.out.println("Got it. I've added this task:");
        System.out.print("  ");
        int latestTaskIndex = tasks.size() - 1;
        printTask(latestTaskIndex);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }

    public static ArrayList<Task> getTaskList() {
        return tasks;
    }

    public static void addTask(Task task) {
        tasks.add(task);
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
            printTask(i);
        }
    }

    public static void markTask(String input) {
        int taskIndex = Integer.parseInt(input) - 1;
        Task task = tasks.get(taskIndex);
        task.markAsDone();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  [" + task.getStatusIcon() + "] " + task.getDescription());
    }

    public static void unmarkTask(String input) {
        int taskIndex = Integer.parseInt(input) - 1;
        Task task = tasks.get(taskIndex);
        task.unmarkAsDone();
        System.out.println("OK, I've marked this task as not done yet");
        System.out.println("  [" + task.getStatusIcon() + "] " + task.getDescription());
    }

    public static void addTodo(String input) throws PaddingtonException {
        if (input.isEmpty()) {
            throw PaddingtonException.invalidTodo();
        }

        Todo task = new Todo(input);
        tasks.add(tasks.size(), task);
        printAddedTask();
    }

    public static void addEvent(String input) throws PaddingtonException {
        if (input.isEmpty()) {
            throw PaddingtonException.invalidEvent();
        }

        String[] processedInput = input.split(" /from ", 2);
        if (processedInput.length != 2) {
            throw new PaddingtonException("paddington.task.Event task should include start and end time!");
        }

        String[] timings = processedInput[1].split(" /to ", 2);
        if (timings.length != 2) {
            throw new PaddingtonException("paddington.task.Event task should include end time!");
        }

        String description = processedInput[0];
        String from = timings[0];
        String to = timings[1];

        Event task = new Event(description, from, to);
        tasks.add(tasks.size(), task);
        printAddedTask();
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

        Deadline task = new Deadline(description, by);
        tasks.add(tasks.size(), task);
        printAddedTask();
    }
}
