package pl.com.sremski.reuters.task4;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import static org.apache.commons.math3.primes.Primes.isPrime;

public class TaskB4 {

    public static void main(String[] args) {

        String path = "task_B4.txt";
        Parser parser = new Parser();
        InputStream fileInputStream = null;
        HashMap<String, Integer> countryGDP = new HashMap<>();

        try {
            fileInputStream = new FileInputStream(path);
        } catch (FileNotFoundException e) {
            System.out.println("File " + path + " not found!");
            return;
        }

        countryGDP = parser.parseToMap(fileInputStream);

        countryGDP.entrySet().stream()
                .filter(e -> isPrime(e.getValue()))
                .map(Map.Entry::getKey)
                .sorted()
                .forEach(System.out::println);
    }
}