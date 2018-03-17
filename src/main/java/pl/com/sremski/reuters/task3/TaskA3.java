package pl.com.sremski.reuters.task3;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.List;

public class TaskA3 {

    public static void main(String[] args) {

        Document doc;
        Parser parser = new Parser();
        List<DataRow> data;

        try {
            doc = Jsoup.connect("http://www.mercado.ren.pt/EN/Electr/MarketInfo/Gen/Pages/Forecast.aspx").get();
            data = parser.parse(doc);
        } catch (IOException e) {
            System.out.println("Can't connect with demanded url!");
            return;
        }

        data.forEach(System.out::println);
    }
}
