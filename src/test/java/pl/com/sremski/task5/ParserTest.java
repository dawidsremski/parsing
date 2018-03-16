package pl.com.sremski.task5;

import org.junit.Before;
import org.junit.Test;
import pl.com.sremski.reuters.task5.Hurricane;
import pl.com.sremski.reuters.task5.Parser;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ParserTest {

    private InputStream inputStream;
    private Parser parser = new Parser();

    @Before
    public void before() {
        String file = "EP042015,                ELA,     21,\n" +
                "20150707, 1200,  , LO, 13.4N, 136.9W,  30, 1005,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,\n" +
                "20150707, 1800,  , LO, 14.4N, 138.3W,  30, 1005,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,\n" +
                "EP052015,            DOLORES,     45,\n" +
                "20150711, 0000,  , LO, 11.4N,  96.6W,  25, 1006,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,\n" +
                "20150711, 0600,  , LO, 11.6N,  97.5W,  30, 1006,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0";

        inputStream = new ByteArrayInputStream(file.getBytes());
    }

    @Test
    public void shouldReturnListOfTwoHurricanes() {

        List<Hurricane> hurricanes = parser.parseToList(inputStream);

        assertThat(hurricanes).hasSize(2);
    }

    @Test
    public void shouldReturnEmptyList() {

        String file = "";
        InputStream inputStream = new ByteArrayInputStream(file.getBytes());
        List<Hurricane> hurricanes = parser.parseToList(inputStream);

        assertThat(hurricanes).isEmpty();
    }

    @Test
    public void sustainedWindSpeedShouldBeMax() {

        List<Hurricane> hurricanes = parser.parseToList(inputStream);

        assertThat(hurricanes.get(1).getMaxSustainedWS()).isEqualTo(30);
    }
}
