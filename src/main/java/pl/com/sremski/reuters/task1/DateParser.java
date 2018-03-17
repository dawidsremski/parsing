package pl.com.sremski.reuters.task1;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

public class DateParser {

    private static final String[] patterns = {"yyyy/MM/dd", "ddMMyyyy'T'HHmm", "dd-MM-yyyy"};

    public String parse(String inputDate, DateTimeFormatter outputFormatter) {
        for (String pattern : Arrays.asList(patterns)) {
            try {
                LocalDate date = LocalDate.parse(inputDate, DateTimeFormatter.ofPattern(pattern));
                return date.format(outputFormatter);
            } catch (DateTimeException ignored) {}
        }
        return inputDate;
    }
}
