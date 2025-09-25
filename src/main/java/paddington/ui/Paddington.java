package paddington.ui;

import paddington.storage.Storage;
import paddington.task.TaskList;

import java.io.IOException;
import java.util.Scanner;

public class Paddington {
    static final String line = "____________________________________________________________\n";

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

    private static void printErrorDescription(String errorDescription) {
        System.out.println("(!) Error: " + errorDescription);
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
                TaskList.listAllTasks();
                break;
            case "mark":
                try {
                    TaskList.markTask(input);
                } catch (NumberFormatException e) {
                    printErrorDescription("Index must be an integer.");
                } catch (IndexOutOfBoundsException e) {
                    printErrorDescription("Invalid Index");
                }
                break;
            case "unmark":
                try {
                    TaskList.unmarkTask(input);
                } catch (NumberFormatException e) {
                    printErrorDescription("Index must be an integer.");
                } catch (IndexOutOfBoundsException e) {
                    printErrorDescription("Invalid Index");
                }
                break;
            case "todo":
                TaskList.addTodo(input);
                break;
            case "event":
                TaskList.addEvent(input);
                break;
            case "deadline":
                TaskList.addDeadline(input);
                break;
            default:
                // Invalid command.
                throw PaddingtonException.invalidCommand();
        }

        System.out.print(line);
        return false; // Continue
    }

    public static void main(String[] args) throws PaddingtonException {
        // Load data from storage
        try {
            Storage.init();
            Storage.load();
        } catch (IOException e) {
            throw new PaddingtonException(e.getMessage());
        }

        // Start
        printWelcomeMessage();

        boolean isQuit = false;
        while (!isQuit) {
            String userInput = scanner.nextLine();
            System.out.print(line);
            isQuit = userCommands(userInput);
            // Save task list
            Storage.save(TaskList.getTaskList());
        }

        scanner.close();
    }
}
