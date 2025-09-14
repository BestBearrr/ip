import java.util.ArrayList;
import java.util.Scanner;

public class Paddington {
    static final String line = "____________________________________________________________\n";
    static final int MAX_TASK_SIZE = 100;

    static ArrayList<Task> tasksList = new ArrayList<>(MAX_TASK_SIZE);
    static Scanner scanner = new Scanner(System.in);

    private static void printWelcomeMessage() {
        /*String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);*/

        System.out.print(line);
        System.out.println("Hello! I'm Paddington");
        System.out.println("What can I do for you?");
        System.out.print(line);
    }

    private static void printTask(int index) {
        System.out.println(tasksList.get(index).toString());
    }

    private static void listAllTasks() {
        // Display all saved tasks
        if (tasksList.isEmpty()) {
            System.out.println("No saved tasks.");
            return;
        }

        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasksList.size(); i++) {
            System.out.print((i + 1) + ".");
            printTask(i);
        }
    }

    private static void markTask(String input) {
        int taskIndex = Integer.parseInt(input) - 1;
        Task task = tasksList.get(taskIndex);
        task.markAsDone();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  [" + task.getStatusIcon() + "] " + task.getDescription());
    }

    private static void unmarkTask(String input) {
        int taskIndex = Integer.parseInt(input) - 1;
        Task task = tasksList.get(taskIndex);
        task.unmarkAsDone();
        System.out.println("OK, I've marked this task as not done yet");
        System.out.println("  [" + task.getStatusIcon() + "] " + task.getDescription());
    }

    private static void printAddedTask() {
        System.out.println("Got it. I've added this task:");
        System.out.print("  ");
        int latestTaskIndex = tasksList.size() - 1;
        printTask(latestTaskIndex);
        System.out.println("Now you have " + tasksList.size() + " tasks in the list.");
    }

    private static void addTodo(String input) throws PaddingtonException {
        if (input.isEmpty()) {
            throw PaddingtonException.invalidTodo();
        }

        Todo task = new Todo(input);
        tasksList.add(tasksList.size(), task);
        printAddedTask();
    }

    private static void addEvent(String input) {
        String[] processedInput = input.split(" /from ", 2);
        String[] timings = processedInput[1].split(" /to ", 2);
        String description = processedInput[0];
        String from = timings[0];
        String to = timings[1];

        Event task = new Event(description, from, to);
        tasksList.add(tasksList.size(), task);
        printAddedTask();
    }

    private static void addDeadline(String input) {
        String[] processedInput = input.split(" /by ", 2);
        String description = processedInput[0];
        String by = processedInput[1];

        Deadline task = new Deadline(description, by);
        tasksList.add(tasksList.size(), task);
        printAddedTask();
    }

    private static boolean userCommands(String userInput) throws PaddingtonException {
        String[] processedInput = userInput.split(" ", 2);
        String command = processedInput[0].toLowerCase();
        String input = (processedInput.length > 1) ? processedInput[1] : "";

        switch(command) {
            case "bye":
                System.out.println("Bye. Hope to see you again soon!");
                System.out.print(line);
                return true;
            case "list":
                listAllTasks();
                break;
            case "mark":
                markTask(input);
                break;
            case "unmark":
                unmarkTask(input);
                break;
            case "todo":
                addTodo(input);
                break;
            case "event":
                addEvent(input);
                break;
            case "deadline":
                addDeadline(input);
                break;
            default:
                // Invalid command.
                throw PaddingtonException.invalidCommand();
        }

        System.out.print(line);
        return false; // Continue
    }

    public static void main(String[] args) throws PaddingtonException {
        printWelcomeMessage();
        while (true) {
            String userInput = scanner.nextLine();
            System.out.print(line);

            // True: quit; False: continue
            if (userCommands(userInput)) break;
        }

        scanner.close();
    }
}
