public class PaddingtonException extends Exception {
    public PaddingtonException(String s) {
        super(s);
    }

    public static PaddingtonException invalidCommand() {
        return new PaddingtonException("Invalid command. Try again.");
    }

    public static PaddingtonException invalidTodo() {
        return new PaddingtonException("Missing description of todo task!");
    }
}
