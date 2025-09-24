package paddington.ui;

public class PaddingtonException extends Exception {
    public PaddingtonException(String s) {
        super(s);
    }

    public static PaddingtonException invalidCommand() {
        return new PaddingtonException("Invalid command. Try again.");
    }

    public static PaddingtonException invalidTodo() {
        return new PaddingtonException("Missing description of paddington.task.Todo task!");
    }

    public static PaddingtonException invalidEvent() {
        return new PaddingtonException("Missing description of paddington.task.Event task!");
    }

    public static PaddingtonException invalidDeadline() {
        return new PaddingtonException("Missing description of paddington.task.Deadline task!");
    }
}
