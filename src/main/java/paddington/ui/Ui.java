package paddington.ui;

public class Ui {
    static final String line = "____________________________________________________________\n";

    static void printWelcomeMessage() {
        /* Old Duke Logo
        String logo = " ____        _        \n"
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

    static void printErrorDescription(String errorDescription) {
        System.out.println("(!) Error: " + errorDescription);
    }

    static void printGoodbye() {
        System.out.println("Bye. Hope to see you again soon!");
        System.out.print(line);
    }

    public static void paddingtonPrint(String string) {
        System.out.print(string);
    }
    public static void paddingtonPrintln(String string) {
        System.out.println(string);
    }

    public static void printTask(String taskString) {
        System.out.println("  " + taskString);
    }

    public static void printAddTask(String taskString, int taskCount) {
        System.out.println("Got it. I've added this task:");
        printTask(taskString);
        System.out.println("Now you have " + taskCount + " tasks in the list.");
    }

    public static void printDeleteTask(String taskString, int taskCount) {
        System.out.println("Noted. I've removed this task:");
        printTask(taskString);
        System.out.println("Now you have " + taskCount + " tasks in the list.");
    }

    public static void printMarkTask(String taskString) {
        System.out.println("Nice! I've marked this task as done:");
        printTask(taskString);
    }

    public static void printUnmarkTask(String taskString) {
        System.out.println("OK, I've marked this task as not done yet");
        printTask(taskString);
    }

}
