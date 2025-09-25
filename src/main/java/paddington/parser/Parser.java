package paddington.parser;

import paddington.task.TaskList;
import paddington.ui.PaddingtonException;
import paddington.ui.Ui;

public class Parser {
    private static boolean isQuit = false;

    public static boolean getIsQuit() {
        return isQuit;
    }

    public static void setIsQuit(boolean isQuit) {
        Parser.isQuit = isQuit;
    }

    public static void parseInput(String input) throws PaddingtonException {
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
                } catch (NumberFormatException e) {
                    Ui.printErrorDescription("Index must be an integer.");
                } catch (IndexOutOfBoundsException e) {
                    Ui.printErrorDescription("Invalid Index");
                }
                break;
            case "unmark":
                try {
                    TaskList.unmarkTask(params);
                } catch (NumberFormatException e) {
                    Ui.printErrorDescription("Index must be an integer.");
                } catch (IndexOutOfBoundsException e) {
                    Ui.printErrorDescription("Invalid Index");
                }
                break;
            case "delete":
                try {
                    TaskList.deleteTask(params);
                } catch (NumberFormatException e) {
                    Ui.printErrorDescription("Index must be an integer.");
                } catch (IndexOutOfBoundsException e) {
                    Ui.printErrorDescription("Invalid Index");
                }
                break;
            case "todo":
                TaskList.addTodo(params);
                break;
            case "event":
                TaskList.addEvent(params);
                break;
            case "deadline":
                TaskList.addDeadline(params);
                break;
            default:
                Ui.printErrorDescription("Invalid Command");
        }
    }
}
