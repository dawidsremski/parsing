package pl.com.sremski.reuters.task1;

import java.io.File;
import java.io.IOException;

public class TaskA1 {

    public static void main(String[] args) {

        String path = "task_A1.html";
        String charset = "UTF-8";
        File file = new File(path);
        Parser parser = new Parser();

        try {
            parser.parseToList(file, charset).forEach(System.out::println);
        } catch (IOException e) {
            System.out.println("Can't read the file " + file + "!");
        }
    }
}
