package pl.com.sremski.reuters.task5;

import lombok.Data;

@Data
public class Hurricane {

    private Integer year;
    private String name;
    private Integer maxSustainedWS;

    @Override
    public String toString() {
        return year + " " + name + " " + maxSustainedWS;
    }
}
