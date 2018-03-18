package pl.com.sremski.task1;

import org.junit.Test;
import pl.com.sremski.reuters.task1.DateParser;

import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class DateParserTest {

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    private final String[] dates = {"2017/10/10", "10102017T0000", "10-10-2017"};
    private final DateParser dateParser = new DateParser();

    @Test
    public void shouldParseDates() {

        List<String> parsedDates = Arrays.stream(dates)
                .map(date -> dateParser.parse(date, formatter)).collect(Collectors.toList());

        assertThat(parsedDates).containsOnly("10-10-2017");
    }

    @Test
    public void shouldNotParseWhenNoPatternMatches() {

        String date = "123456abc";
        String parsedDate = dateParser.parse(date,formatter);

        assertThat(parsedDate).isEqualTo(date);
    }
}
