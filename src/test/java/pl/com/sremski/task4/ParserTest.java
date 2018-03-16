package pl.com.sremski.task4;

import org.junit.Before;
import org.junit.Test;
import pl.com.sremski.reuters.task4.Parser;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.HashMap;

import static org.assertj.core.api.Assertions.assertThat;

public class ParserTest {

    private Parser parser;
    private InputStream fileInputStream;

    @Before
    public void before() {
        parser = new Parser();
        String file = "Country\tGDP\nSweden\t511,397\nNorway\t370,449\nArgentina[7]\t545,124\nSaudi Arabia\t646,438";
        fileInputStream = new ByteArrayInputStream(file.getBytes());
    }

    @Test
    public void shouldNotParseHeader() {

        HashMap<String, Integer> countryGDP = parser.parseToMap(fileInputStream);

        assertThat(countryGDP.keySet()).hasSize(4);
    }

    @Test
    public void shouldReturnEmptyMap() {
        String file = "Country\tGDP";
        fileInputStream = new ByteArrayInputStream(file.getBytes());
        HashMap<String, Integer> countryGDP = parser.parseToMap(fileInputStream);

        assertThat(countryGDP).isEmpty();
    }

    @Test
    public void shouldParseIntegers() {

        HashMap<String, Integer> countryGDP = parser.parseToMap(fileInputStream);

        assertThat(countryGDP.values()).containsExactlyInAnyOrder(511397, 370449, 545124, 646438);
    }

    @Test
    public void shouldRemoveTextInBrackets() {

        HashMap<String, Integer> countryGDP = parser.parseToMap(fileInputStream);

        assertThat(countryGDP.keySet()).contains("Argentina");
    }
}
