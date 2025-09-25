package paddington.task;

public class Deadline extends Task {

    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    public Deadline(String description, boolean isDone, String by) {
        super(description, isDone);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    @Override
    public String formatToSave() {
        return "D" + super.formatToSave() + DELIMITER + by;
    }

    public static Deadline formatFromSave(String saveString) {
        String[] segments = saveString.split(" \\| ");
        boolean isDone = segments[1].trim().equals("1");
        String description = segments[2].trim();
        String by = segments[3].trim();
        return new Deadline(description, isDone, by);
    }
}
