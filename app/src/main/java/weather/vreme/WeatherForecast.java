package weather.vreme;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tomaz Setina on 30.12.2014.
 */
public class WeatherForecast {

    private List<DayForecast> daysForecast = new ArrayList<DayForecast>();

    public void addForecast(DayForecast forecast) {
        daysForecast.add(forecast);
        System.out.println("Add forecast [" + forecast + "]");
    }

    public DayForecast getForecast(int dayNum) {
        try {
            return daysForecast.get(dayNum);
        } catch (Exception e) {
            return null;
        }
    }
}
