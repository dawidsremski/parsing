package pl.com.sremski.reuters.task4;

import java.io.*;
import java.util.AbstractMap;
import java.util.HashMap;
import java.util.stream.Collectors;

public class Parser {

    public HashMap<String, Integer> parseToMap(InputStream fileInputStream) {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));

        return bufferedReader.lines()
                .filter(s -> !s.equals("Country\tGDP"))
                .map(s -> {
                    String[] splitted = s.split("\t");
                    return new AbstractMap.SimpleEntry<>(
                            splitted[0].replaceAll("\\[.*\\]", ""),
                            Integer.valueOf(splitted[1].replaceAll("[^\\d]", ""))
                    );
                })
                .collect(Collectors.toMap(AbstractMap.SimpleEntry::getKey, AbstractMap.SimpleEntry::getValue, (a, b) -> b, HashMap::new));
    }
}