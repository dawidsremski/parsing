package pl.com.sremski.task3;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Before;
import org.junit.Test;
import pl.com.sremski.reuters.task3.DataRow;
import pl.com.sremski.reuters.task3.Parser;

import java.io.IOException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ParserTest {

    private List<DataRow> data;

    @Before
    public void before() throws IOException {

        String html = "<table><tr class=\"trPAR\">\n" +
                "\t\t\t\t\t\t\t\t\t<td align=\"left\" style=\"width:40px;\">1</td><td align=\"right\" style=\"width:75px;\">1972</td><td align=\"right\" style=\"width:75px;\">0</td><td align=\"right\" style=\"width:75px;\">4304</td><td align=\"right\" style=\"width:75px;\">6276</td>\n" +
                "\t\t\t\t\t\t\t\t</tr><tr class=\"trIMPAR\">\n" +
                "\t\t\t\t\t\t\t\t\t<td align=\"left\" style=\"width:40px;\">2</td><td align=\"right\" style=\"width:75px;\">1849</td><td align=\"right\" style=\"width:75px;\">0</td><td align=\"right\" style=\"width:75px;\">3815</td><td align=\"right\" style=\"width:75px;\">5664</td>\n" +
                "\t\t\t\t\t\t\t\t</tr><tr class=\"trPAR\">\n" +
                "\t\t\t\t\t\t\t\t\t<td align=\"left\" style=\"width:40px;\">3</td><td align=\"right\" style=\"width:75px;\">1996</td><td align=\"right\" style=\"width:75px;\">0</td><td align=\"right\" style=\"width:75px;\">3269</td><td align=\"right\" style=\"width:75px;\">5265</td>\n" +
                "\t\t\t\t\t\t\t\t</tr><tr class=\"trIMPAR\">\n" +
                "\t\t\t\t\t\t\t\t\t<td class=\"txtPREV\" align=\"right\" style=\"width:75px;\">1220</td><td class=\"txtPREV\" align=\"right\" style=\"width:75px;\">0</td><td class=\"txtPREV\" align=\"right\" style=\"width:75px;\">3855</td><td class=\"txtPREV\" align=\"right\" style=\"width:75px;\">5075</td>\n" +
                "\t\t\t\t\t\t\t\t</tr></table>";

        Parser parser = new Parser();
        Document doc = Jsoup.parse(html);
        data = parser.parse(doc);
    }

    @Test
    public void shouldReturnFourDataRows() {

        assertThat(data).hasSize(4);
    }

    @Test
    public void shouldCorrectlyMarkAsForecast() {

        assertThat(!data.get(3).getActual());
    }

    @Test
    public void shouldReturnRowsWithCorrectData() {

        assertThat(data.get(2))
                .hasFieldOrPropertyWithValue("hour", 3)
                .hasFieldOrPropertyWithValue("wind", 1996)
                .hasFieldOrPropertyWithValue("solar", 0)
                .hasFieldOrPropertyWithValue("others", 3269)
                .hasFieldOrPropertyWithValue("total", 5265)
                .hasFieldOrPropertyWithValue("actual", true);
    }
}