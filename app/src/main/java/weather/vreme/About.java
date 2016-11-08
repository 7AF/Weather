package weather.vreme;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class About extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
    }

    public static final int MENU_CURRENT_LOCATION = Menu.FIRST;
    public static final int MENU_FORECAST = Menu.FIRST + 1;
    public static final int MENU_OPTIONS = Menu.FIRST + 2;


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.

        menu.add(Menu.NONE, MENU_CURRENT_LOCATION, Menu.NONE, "Details");
        menu.add(Menu.NONE, MENU_FORECAST, Menu.NONE, "Forecast");
        menu.add(Menu.NONE, MENU_OPTIONS, Menu.NONE, "Options");


        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
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
}