package pl.com.sremski.reuters.task1;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Parser {

    public List<String> parseToList(File file, String charset) throws IOException {

        List<String> list = new ArrayList<>();
        StringBuilder stringBuilder;
        DateParser dateParser = new DateParser();
        Document document = Jsoup.parse(file, charset);

        Element tableBody = document.select("tbody").get(0);
        Elements rows = tableBody.select("tr");

        for (Element row : rows) {
            stringBuilder = new StringBuilder();
            Elements theads = row.select("th");

            int i = 0;

            for (Element th : theads) {
                String text = th.text();

                if (i == 0) {
                    text = dateParser.parse(text, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
                }

                if (i == 2) {
                    String value = text.split("\\(")[1]
                            .replaceAll("[^\\d]", "");
                    value = String.valueOf(Double.valueOf(Double.valueOf(value) / 100))
                            .replaceAll("\\.", ",");
                    text = text.split("\\(")[0] + "(" + value + ")";
                }

                text = text.toUpperCase()
                        .replaceAll("%", " ")
                        .replaceAll("\\+", "Y")
                        .replaceAll("\\(\\d\\)", "")
                        .trim();

                if (i == theads.size() - 1) {
                    text = text.replaceAll(" ", "\\.");
                }

                stringBuilder.append(text).append("\t");
                i++;
            }
            list.add(stringBuilder.toString());
        }

        return list;
    }
}
