package pl.com.sremski.reuters.task3;

import java.io.IOException;
import java.util.List;

public class TaskA3 {

    public static void main(String[] args) {

        Parser parser = new Parser();
        List<DataRow> data;

        try {
            data = parser.parse();
        } catch (IOException e) {
            System.out.println("Can't connect with demanded url!");
            return;
        }

        data.forEach(System.out::println);
    }
}
