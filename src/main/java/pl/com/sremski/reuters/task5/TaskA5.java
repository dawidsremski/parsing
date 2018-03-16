package pl.com.sremski.reuters.task5;

import java.io.IOException;
import java.util.List;

public class TaskA5 {

    public static void main(String[] args) {

        Parser parser = new Parser();
        String url = "https://www.nhc.noaa.gov/data/hurdat/hurdat2-nepac-1949-2016-041317.txt";
        List<Hurricane> hurricanes = null;

        try {
            hurricanes = parser.parseToList(url);
        } catch (IOException e) {
            System.out.println("Can't connect with requested URL or URL is not valid!");
            return;
        }

        hurricanes.stream()
                .filter(hur -> (hur.getYear() >= 2015) && (hur.getName().endsWith("A")))
                .forEach(System.out::println);
    }
}
