public class Duke {

    public static void printIntroMessage(){
        System.out.println("________________________________");
        System.out.println("Hello, I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println();
        System.out.println("________________________________");
    }

    public static void printExitMessage(){
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("________________________________");
        System.exit(0);
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        printIntroMessage();
        printExitMessage();
    }
}
