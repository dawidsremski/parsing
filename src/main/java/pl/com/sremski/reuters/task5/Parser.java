package pl.com.sremski.reuters.task5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Parser {

    public List<Hurricane> parseToList(String url) throws IOException {

        InputStream inputStream = new URL(url).openStream();

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

        List<List<String>> list = bufferedReader.lines()
                .map(s -> {
                    String[] splitted = s.split(",");
                    return Arrays.stream(splitted).map(x -> x.replaceAll(" ", "")).collect(Collectors.toList());
                })
                .collect(Collectors.toList());

        List<Hurricane> hurricanes = new ArrayList<>();

        Hurricane hurricane = new Hurricane();
        String hurName = null;
        Integer sustainedWS;
        Integer maxSustainedWS = 0;

        for (List<String> row : list) {
            if (row.size() == 3) {
                if (hurName != null) {
                    hurricane.setName(hurName);
                    hurricanes.add(hurricane);
                    hurricane = new Hurricane();
                }
                hurName = row.get(1);
            } else {
                hurricane.setYear(LocalDate.parse(row.get(0), DateTimeFormatter.BASIC_ISO_DATE).getYear());
                sustainedWS = Integer.valueOf(row.get(6));
                hurricane.setMaxSustainedWS((sustainedWS > maxSustainedWS) ? sustainedWS : maxSustainedWS);
            }
        }
        hurricane.setName(hurName);
        hurricanes.add(hurricane);

        return hurricanes;
    }
}