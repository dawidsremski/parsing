package pl.com.sremski.reuters.task3;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Parser {

    public List<DataRow> parse(Document doc) throws IOException {

        Elements rows = doc.select("tr.trPAR,tr.trIMPAR");

        List<DataRow> data = new ArrayList<>();
        int i = 0;

        for (Element tr : rows) {

            i++;
            Elements cells = tr.select("td");
            DataRow dataRow = new DataRow();

            if (i > 24) dataRow.setHour(i - 24);

            int cell = 0;

            for (Element td : cells) {
                cell++;
                if (cells.size() == 4 && cell == 1) cell++;

                dataRow.setActual(!td.hasClass("txtPREV"));

                switch (cell) {
                    case 1:
                        dataRow.setHour(Integer.valueOf(td.text()));
                        break;
                    case 2:
                        dataRow.setWind(Integer.valueOf(td.text()));
                        break;
                    case 3:
                        dataRow.setSolar(Integer.valueOf(td.text()));
                        break;
                    case 4:
                        dataRow.setOthers(Integer.valueOf(td.text()));
                        break;
                    case 5:
                        dataRow.setTotal(Integer.valueOf(td.text()));
                }
            }

            data.add(dataRow);
        }

        return data;
    }
}
