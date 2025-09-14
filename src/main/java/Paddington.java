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

    private static boolean userCommands(String userInput) {
        String[] processedInput = userInput.split(" ", 2);
        String command = processedInput[0];
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
            default:
                // No command given, so add new task.
                Task task = new Task(userInput);
                tasksList.add(tasksList.size(), task);
                System.out.println("added: " + userInput);
        }

        System.out.println(line);
        return false; // Continue
    }

    public static void main(String[] args) {
        printWelcomeMessage();
        while (true) {
            String userInput = scanner.nextLine().toLowerCase();
            System.out.print(line);

            // True: quit; False: continue
            if (userCommands(userInput)) break;
        }

        scanner.close();
    }
}
