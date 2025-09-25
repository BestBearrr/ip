package paddington.parser;

import paddington.task.TaskList;
import paddington.ui.PaddingtonException;
import paddington.ui.Ui;

public class Parser {
    private static boolean isQuit = false;
    private static boolean isTaskListChanged = false;

    public static boolean getIsQuit() {
        return isQuit;
    }

    public static void setIsQuit(boolean isQuit) {
        Parser.isQuit = isQuit;
    }

    public static boolean getIsTaskListChanged() {
        return isTaskListChanged;
    }

    public static void parseInput(String input) throws PaddingtonException {
        isTaskListChanged = false;

        String[] processedInput = input.split(" ", 2);
        String command = processedInput[0].toLowerCase();
        String params = (processedInput.length > 1) ? processedInput[1] : "";

        switch (command) {
            case "bye":
                Ui.printGoodbye();
                isQuit = true;
                break;
            case "list":
                TaskList.listAllTasks();
                break;
            case "find":
                TaskList.findTask(params);
                break;
            case "mark":
                try {
                    TaskList.markTask(params);
                    isTaskListChanged = true;
                } catch (NumberFormatException e) {
                    Ui.printErrorDescription("Index must be an integer.");
                } catch (IndexOutOfBoundsException e) {
                    Ui.printErrorDescription("Invalid Index");
                }
                break;
            case "unmark":
                try {
                    TaskList.unmarkTask(params);
                    isTaskListChanged = true;
                } catch (NumberFormatException e) {
                    Ui.printErrorDescription("Index must be an integer.");
                } catch (IndexOutOfBoundsException e) {
                    Ui.printErrorDescription("Invalid Index");
                }
                break;
            case "delete":
                try {
                    TaskList.deleteTask(params);
                    isTaskListChanged = true;
                } catch (NumberFormatException e) {
                    Ui.printErrorDescription("Index must be an integer.");
                } catch (IndexOutOfBoundsException e) {
                    Ui.printErrorDescription("Invalid Index");
                }
                break;
            case "todo":
                TaskList.addTodo(params);
                isTaskListChanged = true;
                break;
            case "event":
                TaskList.addEvent(params);
                isTaskListChanged = true;
                break;
            case "deadline":
                TaskList.addDeadline(params);
                isTaskListChanged = true;
                break;
            default:
                Ui.printErrorDescription("Invalid Command");
        }
    }
}
