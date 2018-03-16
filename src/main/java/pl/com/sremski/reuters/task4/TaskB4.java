package pl.com.sremski.reuters.task4;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

import static org.apache.commons.math3.primes.Primes.isPrime;

public class TaskB4 {

    public static void main( String[] args ) {

        String path = "task_B4.txt";
        Parser parser = new Parser();
        HashMap<String,Integer> countryGDP = new HashMap<>();

        try {
            countryGDP = parser.parseToMap(path);
        } catch (FileNotFoundException e) {
            System.out.println("File not found " + path);
        }

        countryGDP.entrySet().stream()
                .filter(e -> isPrime(e.getValue()))
                .map(Map.Entry::getKey)
                .sorted()
                .forEach(System.out::println);
    }
}