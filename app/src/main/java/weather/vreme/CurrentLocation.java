package weather.vreme;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class CurrentLocation extends MainActivity {
    public static String city = "";
    public static boolean temperature = false;
    public static boolean speed = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_location);

        Button button = (Button) findViewById(R.id.button);

        button.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {

                        EditText input = (EditText) findViewById(R.id.editText);
                        EditText input1 = (EditText) findViewById(R.id.editText2);
                        EditText input2 = (EditText) findViewById(R.id.editText3);
                        EditText input3 = (EditText) findViewById(R.id.editText4);
                        EditText input4 = (EditText) findViewById(R.id.editText5);
                        EditText input5 = (EditText) findViewById(R.id.editText6);
                        EditText input6 = (EditText) findViewById(R.id.editText7);
                        EditText input7 = (EditText) findViewById(R.id.editText8);

                        city = input.getText().toString();

                        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
                        SharedPreferences.Editor editor = settings.edit();
                        editor.putString("silentMode", city);

                        // Commit the edits!
                        editor.commit();

                        JSONWeatherTask task1 = new JSONWeatherTask();
                        task1.execute(new String[]{city});

                        input1.setText(Weather.location.getCity() + "," + Weather.location.getCountry());
                        input2.setText(Weather.currentCondition.getCondition() + "(" + Weather.currentCondition.getDescr() + ")");
                        double degrees = Weather.temperature.getTemp() - 273.15;
                        if (temperature == false) {
                            input3.setText("" + String.format("%.1f", Weather.temperature.getTemp() - 273.15) + "°C");
                        } else if (temperature == true) {
                            input3.setText("" + String.format("%.1f", degrees * (9.0 / 5) + 32) + "°F");
                        }
                        input4.setText("" + Weather.currentCondition.getHumidity() + "%");
                        input5.setText("" + Weather.currentCondition.getPressure() + " hPa");
                        if (speed == false) {
                            input6.setText("" + String.format("%.1f", Weather.wind.getSpeed() * 3.6) + " km/h");
                        } else if (speed == true) {
                            input6.setText("" + Weather.wind.getSpeed() + " m/s");
                        }
                        input7.setText("" + Weather.wind.getDeg() + "°");
                    }
                }
        );
    }

    public static final int MENU_FORECAST = Menu.FIRST;
    public static final int MENU_OPTIONS = Menu.FIRST + 1;
    public static final int MENU_ABOUT = Menu.FIRST + 2;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.

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
            case MENU_FORECAST:
                Intent forecast = new Intent(this, Forecast.class);
                startActivity(forecast);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
