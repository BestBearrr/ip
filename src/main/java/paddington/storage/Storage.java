package paddington.storage;

import paddington.task.*;
import paddington.ui.Paddington;
import paddington.ui.PaddingtonException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {

    private static final String DATA_FILE_PATH = "./data/PaddingtonData.txt";

    private static Task readFromSave(String line) throws PaddingtonException {
        char taskType = line.charAt(0);

        return switch (taskType) {
            case 'T' -> Todo.formatFromSave(line);
            case 'D' -> Deadline.formatFromSave(line);
            case 'E' -> Event.formatFromSave(line);
            default -> throw new PaddingtonException("Invalid task format.");
        };
    }

    public static void init() throws IOException {
        File file = new File(DATA_FILE_PATH);

        // Found save file.
        if (file.exists()) {
            System.out.println("Found save file.");
            return;
        }

        // Else, create save file.
        File parentDir = file.getParentFile();
        if (parentDir.mkdirs()) {
            System.out.println("Created directory at " + parentDir);
        }
        if (file.createNewFile()) {
            System.out.println("Created new save file at " + file.getPath());
        }
    }

    public static void load() throws PaddingtonException {
        File file = new File(DATA_FILE_PATH);

        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                Task task = readFromSave(line);
                TaskList.addTask(task);
            }
        } catch (FileNotFoundException e) {
            throw new PaddingtonException("Error loading saved data file.");
        }
    }

    public static void save(ArrayList<Task> tasks) throws PaddingtonException {
        FileWriter writer = null;
        try {
            writer = new FileWriter(DATA_FILE_PATH);
            for (Task task : tasks) {
                writer.write(task.formatToSave() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            throw new PaddingtonException("Error saving file. " + e);
        }
    }
}
