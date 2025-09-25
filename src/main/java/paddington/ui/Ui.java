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

    public static void printAddTask() {
        System.out.println("Got it. I've added this task:");
    }

    public static void printDeleteTask() {
        System.out.println("Noted. I've removed this task:");
    }

}
