package pl.com.sremski.reuters.task3;

import lombok.Data;

@Data
public class DataRow {

    Integer hour;
    Integer wind;
    Integer solar;
    Integer others;
    Integer total;
    Boolean actual;

    @Override
    public String toString() {

        return (actual ? "Actual: " : "Forecast: ") + "\t" +
                hour + "\t" +
                "Wind: " + wind + "\t" +
                "Solar: " + solar + "\t" +
                "Others: " + others;
    }
}
