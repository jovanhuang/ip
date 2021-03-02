package duke.accessfile;

import duke.command.MainUI;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.io.*;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import static duke.command.MainUI.FILE_PATH;
import static duke.command.MainUI.taskArrayList;

public class FileManager {
    private Deadline deadline;
    private Event event;
    private Todo todo;

    public FileManager() {

    }

    public static void createDirectory(String directoryName) {
        File relativePathFileName = new File(directoryName);
        String fullPath = relativePathFileName.getAbsolutePath();
        File absolutePathFileName = new File(fullPath);
        boolean bool = absolutePathFileName.mkdirs();
        if (bool) {
            System.out.println("'data' folder created successfully");
        } else {
            System.out.println("Sorry, couldn't create 'data' folder in your directory");
        }
    }

    public void printMessageForCreatingNewFile(File data) {
        System.out.println("Your file that stores all your tasks is not found! (data/duke.txt) :(");
        System.out.println("Creating new file now to store your future tasks! :)");
        System.out.println("File created: " + data.getName());
        MainUI.printDivider();
    }

    public ArrayList<Task> loadData() throws FileNotFoundException {
        ArrayList<Task> taskArrayList = new ArrayList<>();
        System.out.println("Loading data from your file, 'duke.txt'...");
        File data = new File(FILE_PATH);
        taskArrayList = new ArrayList<>();
        Scanner sc = new Scanner(data);
        while (sc.hasNext()) {
            String taskSentence = sc.nextLine();
            char taskCategory = taskSentence.charAt(1);
            boolean isDone = taskSentence.substring(4, 5).compareTo("\u2713") == 0;
            switch (taskCategory) {
            case 'T':
                String toDoDescription = taskSentence.substring(7);
                todo = new Todo(toDoDescription);
                if (isDone) {
                    todo.markAsDone();
                }
                taskArrayList.add(todo);
                break;
            case 'D':
                int dueDateStartingIndex = taskSentence.indexOf("(by:");
                String dueDate = taskSentence.substring(dueDateStartingIndex + 5, taskSentence.length() - 1);
                String deadlineDescription = taskSentence.substring(7, dueDateStartingIndex);
                deadline = new Deadline(deadlineDescription, dueDate);
                if (isDone) {
                    deadline.markAsDone();
                }
                taskArrayList.add(deadline);
                break;
            case 'E':
                int durationStartingIndex = taskSentence.indexOf("(at:");
                String duration = taskSentence.substring(durationStartingIndex + 5, taskSentence.length() - 1);
                String eventDescription = taskSentence.substring(7, durationStartingIndex);
                event = new Event(eventDescription, duration);
                if (isDone) {
                    event.markAsDone();
                }
                taskArrayList.add(event);
                break;
            default:
                break;
            }
        }
        System.out.println("Done loading the data!");
        MainUI.printDivider();
        return taskArrayList;
    }

    public void updateDataFile() throws IOException {
        PrintStream out = new PrintStream(new FileOutputStream(MainUI.FILE_PATH));
        PrintStream stdout = System.out;
        System.setOut(out);
        for (Task task: taskArrayList){
            System.out.println(task);
        }
        System.setOut(stdout);
    }

}
