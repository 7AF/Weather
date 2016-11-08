package weather.vreme;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class MainActivity extends FragmentActivity implements OnMapReadyCallback {
    public static final String PREFS_NAME = "my";
    public static int PREFERENCE_MODE_PUBLIC = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);


        MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    public void onMapReady(GoogleMap map) {
        map.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        map.getUiSettings().setRotateGesturesEnabled(false);
        map.setMyLocationEnabled(false);

        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        String city1 = settings.getString("silentMode", "London, UK");

        JSONWeatherTask task1 = new JSONWeatherTask();
        task1.execute(new String[]{city1});

        Weather v = null;

        Context context = getApplicationContext();
        File file = new File(context.getFilesDir(), "list.txt");
        BufferedReader br = null;
        String line;
        if(file.exists()){
        try {

            br = new BufferedReader(new FileReader(file));
            while ((line = br.readLine()) != null) {
                JSONWeatherTask task = new JSONWeatherTask();
                task.execute(new String[]{line});
                //Log.d("abcde", line);
                v = task.get();
                map.addMarker(new MarkerOptions()
                        .position(
                                new LatLng(v.location.getLatitude(),
                                        v.location.getLongitude()))
                        .icon(BitmapDescriptorFactory
                                .fromAsset(v.currentCondition.getIcon()
                                        + ".png"))
                        .title("Temp: " + String.format("%.2f C°", v.temperature.getTemp() - 273.15)));
            }
        } catch (Exception e) {

            Intent error = new Intent(this, ErrorActivity.class);
            startActivity(error);

        }}


    }

    public static final int MENU_CURRENT_LOCATION = Menu.FIRST;
    public static final int MENU_FORECAST = Menu.FIRST + 1;
    public static final int MENU_OPTIONS = Menu.FIRST + 2;
    public static final int MENU_ABOUT = Menu.FIRST + 3;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.

        menu.add(Menu.NONE, MENU_CURRENT_LOCATION, Menu.NONE, "Details");
        menu.add(Menu.NONE, MENU_FORECAST, Menu.NONE, "Forecast");
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
            case MENU_FORECAST:
                Intent forecast = new Intent(this, Forecast.class);
                startActivity(forecast);
                break;

        }
        return super.onOptionsItemSelected(item);
    }

    protected class JSONWeatherTask extends AsyncTask<String, Void, Weather> {

        @Override
        protected Weather doInBackground(String... params) {
            Weather weather = new Weather();
            String data = ((new WeatherHttpClient()).getWeatherData(params[0]));

            try {
                weather = JSONWeatherParser.getWeather(data);

                // Ikona
                Weather.iconData = ((new WeatherHttpClient())
                        .getImage(Weather.currentCondition.getIcon()));
            } catch (Exception e) {

            }
            return weather;

        }

        @Override
        protected void onPostExecute(Weather weather) {
            super.onPostExecute(weather);

        }
    }
}