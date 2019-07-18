import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ToDo {


  public ToDo() throws FileNotFoundException {
  }
  static Path filePath;
  static ArrayList<String> lines;

  public static void main(String[] menu) throws FileNotFoundException {

    try {
      filePath = Paths.get("ToDo.txt");
      lines = (ArrayList<String>) Files.readAllLines(filePath);
    }
    catch (IOException ex) {
      System.out.println(ex.toString());
      System.out.println("Could not find file!");
    }

    System.out.println(
            "\n $ todo \n" +
                    " Command Line Todo application\n" +
                    "=============================\n" +
                    "Command line arguments:\n" +
                    "-l   Lists all the tasks \n" +
                    "-a   Adds a new task \n" +
                    "-r   Removes a task \n" +
                    "-c   Completes a task \n");

    try {
      if (menu[0].equals("-l")) {
        readAndListAllTheTasks(lines);
      }
      if (menu[0].equals("-a")) {
        addsNewTask(menu[1], filePath, lines);
      }
      if (menu[0].equals("-r")) {
        removesTask(menu[1], filePath, lines);
      }
      if (menu[0].equals("-c")) {
        completesAtask(menu[1], filePath, lines);
      }
    } catch (ArrayIndexOutOfBoundsException ignored) {
    } catch (IOException e) {
      e.printStackTrace();
    }
  }


  private static void completesAtask(String newTask, Path filePath, ArrayList lines) throws IOException {

    int OverrideTask = Integer.parseInt(newTask);
    String braces = "[X] ";
    String newTaskPlusBrace = braces + newTask;
    lines.add(OverrideTask-1);
    lines.remove(OverrideTask);
    Files.write(filePath, lines);

  }

  private static void removesTask(String newTask, Path filePath, ArrayList lines) throws IOException{

  if(lines.size() > 2){
    int removeTask = Integer.parseInt(newTask);
    lines.remove(removeTask-1);
    Files.write(filePath, lines);
  }

  }

  private static void addsNewTask(String newTask, Path filePath, ArrayList lines) throws IOException {

    String braces = "[ ] ";
    String newTaskPlusBrace = braces + newTask;
    lines.add(newTaskPlusBrace);
    Files.write(filePath, lines);

  }

  public static void readAndListAllTheTasks (List<String> lines) {

    if (lines.isEmpty()){
      System.out.println("No todos for today! :)");
    }
    else {
      for (int i = 0; i < lines.size(); i++) {
        System.out.println(i + 1 + " - " + lines.get(i));
      }
    }
  }
}
