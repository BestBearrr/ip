import java.util.Scanner;

public class Paddington {
    public static void main(String[] args) {
        /*String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);*/
        String line = "____________________________________________________________\n";
        System.out.print(line);
        System.out.println("Hello! I'm Paddington");
        System.out.println("What can I do for you?");
        System.out.print(line);

        Task[] tasks = new Task[100];
        int taskCount = 0;

        Scanner scanner = new Scanner(System.in);
        String userInput;

        while (true) {
            userInput = scanner.nextLine().toLowerCase();

            System.out.print(line);

            if (userInput.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                System.out.print(line);
                break;
            } else if (userInput.equals("list")) {
                if (taskCount == 0) {
                    System.out.println("No saved tasks.");
                } else {
                    // Display all saved tasks
                    for (int i = 0; i < taskCount; i++) {
                        System.out.println((i + 1) + ".[" + tasks[i].getStatusIcon() + "] " + tasks[i].getDescription());
                    }
                }
            }
            // Mark as done
            else if (userInput.startsWith("mark ")) {
                int taskIndex = Integer.parseInt(userInput.substring(5)) - 1;
                Task t = tasks[taskIndex];
                System.out.println("Nice! I've marked this task as done:");
                t.markAsDone();
                System.out.println("  [" + t.getStatusIcon() + "] " + t.description);
            }
            // Unmark as done
            else if (userInput.startsWith("unmark ")) {
                int taskIndex = Integer.parseInt(userInput.substring(7)) - 1;
                Task t = tasks[taskIndex];
                System.out.println("OK, I've marked this task as not done yet");
                t.unmarkAsDone();
                System.out.println("  [" + t.getStatusIcon() + "] " + t.description);
            }
            // Add new task
            else {
                Task t = new Task(userInput);
                tasks[taskCount] = t;
                taskCount++;

                System.out.println("added: " + userInput);
            }

            System.out.print(line);
        }

        scanner.close();
    }
}
