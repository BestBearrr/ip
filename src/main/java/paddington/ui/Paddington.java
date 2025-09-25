package paddington.ui;

import paddington.parser.Parser;
import paddington.storage.Storage;
import paddington.task.TaskList;

import java.io.IOException;
import java.util.Scanner;

public class Paddington {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws PaddingtonException {
        // Load data from storage
        try {
            Storage.init();
            Storage.load();
        } catch (IOException e) {
            throw new PaddingtonException(e.getMessage());
        }

        // Start
        Ui.printWelcomeMessage();

        while (!Parser.getIsQuit()) {
            String userInput = scanner.nextLine();
            Ui.printHorizontalLine();
            Parser.parseInput(userInput);
            Ui.printHorizontalLine();

            if (Parser.getIsTaskListChanged()) {
                Storage.save(TaskList.getTaskList());
            }
        }

        scanner.close();
    }
}
