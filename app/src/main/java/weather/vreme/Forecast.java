package weather.vreme;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;

import org.json.JSONException;


public class Forecast extends MainActivity {
    private static String forecastDaysNum = "3";
    private ViewPager pager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {

            SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
            String city1 = settings.getString("silentMode", "London, UK");

            JSONForecastWeatherTask task2 = new JSONForecastWeatherTask();
            task2.execute(new String[]{city1, "en", "3"});

            setContentView(R.layout.activity_forecast);
            pager = (ViewPager) findViewById(R.id.pager);
        } catch (Exception e) {
            Intent error = new Intent(this, ErrorActivity.class);
            startActivity(error);
        }

    }

    public static final int MENU_CURRENT_LOCATION = Menu.FIRST;
    public static final int MENU_OPTIONS = Menu.FIRST + 1;
    public static final int MENU_ABOUT = Menu.FIRST + 2;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        menu.add(Menu.NONE, MENU_CURRENT_LOCATION, Menu.NONE, "Details");
        menu.add(Menu.NONE, MENU_OPTIONS, Menu.NONE, "Options");
        menu.add(Menu.NONE, MENU_ABOUT, Menu.NONE, "About");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        switch (item.getItemId()) {
            case MENU_ABOUT:
                Intent about = new Intent(this, About.class);
                startActivity(about);
                break;
            case MENU_OPTIONS:
                Intent options = new Intent(this, OptionsActivity.class);
                startActivity(options);
                break;
            case MENU_CURRENT_LOCATION:
                Intent curLoc = new Intent(this, CurrentLocation.class);
                startActivity(curLoc);
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    private class JSONForecastWeatherTask extends AsyncTask<String, Void, WeatherForecast> {
        @Override
        protected WeatherForecast doInBackground(String... params) {
            String data = ((new WeatherHttpClient()).getForecastWeatherData(params[0], params[1], params[2]));
            WeatherForecast forecast = new WeatherForecast();
            try {
                forecast = JSONWeatherParser.getForecastWeather(data);
                System.out.println("Weather [" + forecast + "]");

                Weather.iconData = ((new WeatherHttpClient()).getImage(Weather.currentCondition.getIcon()));
            } catch (Exception e) {

            }
            return forecast;
        }

        @Override
        protected void onPostExecute(WeatherForecast forecastWeather) {
            super.onPostExecute(forecastWeather);
            DailyForecastPageAdapter adapter = new DailyForecastPageAdapter(Integer.parseInt(forecastDaysNum), getSupportFragmentManager(), forecastWeather);
            pager.setAdapter(adapter);
        }
    }
}
