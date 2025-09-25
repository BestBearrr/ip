package paddington.task;

public class Event extends Task {
    protected String from;
    protected String to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    public Event(String description, boolean isDone, String from, String to) {
        super(description, isDone);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }

    @Override
    public String formatToSave() {
        return "E" + super.formatToSave() + DELIMITER + from + "-" + to;
    }

    public static Event formatFromSave(String saveString) {
        String[] segments = saveString.split(" \\| ");
        boolean isDone = segments[1].trim().equals("1");
        String description = segments[2].trim();
        String[] timings = segments[3].trim().split("-");
        String from = timings[0];
        String to = timings[1];
        return new Event(description, isDone, from, to);
    }
}
