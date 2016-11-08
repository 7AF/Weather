package weather.vreme;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;

public class OptionsActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.options);

        final ToggleButton tgbutton = (ToggleButton) findViewById(R.id.toggleButton);
        final ToggleButton tgbutton1 = (ToggleButton) findViewById(R.id.toggleButton2);

        tgbutton.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tgbutton.isChecked()) {
                    CurrentLocation.temperature = true;
                } else {
                    CurrentLocation.temperature = false;
                }
            }
        }));

        tgbutton1.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tgbutton1.isChecked()) {
                    CurrentLocation.speed = true;
                } else {
                    CurrentLocation.speed = false;
                }
            }
        }));

        String txt = "";
        Context context = getApplicationContext();
        File file = new File(context.getFilesDir(), "list.txt");
        BufferedReader br = null;
        String line;
        try {
            br = new BufferedReader(new FileReader(file));
            while ((line = br.readLine()) != null) {
                txt += line + "\n";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        EditText output = (EditText) findViewById(R.id.output);
        output.setText(txt);

    }

    public void saveList(View v) {
        try {
            String file = "list.txt";
            FileOutputStream filein = openFileOutput("list.txt", MODE_PRIVATE);
            EditText output = (EditText) findViewById(R.id.output);
            String t = output.getText().toString();
            filein.write(t.getBytes());
            filein.close();
            Toast.makeText(getApplicationContext(), "Saved!", Toast.LENGTH_LONG).show();

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public static final int MENU_CURRENT_LOCATION = Menu.FIRST;
    public static final int MENU_FORECAST = Menu.FIRST + 1;
    public static final int MENU_ABOUT = Menu.FIRST + 2;


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        menu.add(Menu.NONE, MENU_CURRENT_LOCATION, Menu.NONE, "Details");
        menu.add(Menu.NONE, MENU_FORECAST, Menu.NONE, "Forecast");
        menu.add(Menu.NONE, MENU_ABOUT, Menu.NONE, "About");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {

            case MENU_CURRENT_LOCATION:
                Intent curLoc = new Intent(this, CurrentLocation.class);
                startActivity(curLoc);
                break;
            case MENU_ABOUT:
                Intent about = new Intent(this, About.class);
                startActivity(about);
                break;
            case MENU_FORECAST:
                Intent forecast = new Intent(this, Forecast.class);
                startActivity(forecast);
                break;

        }
        return super.onOptionsItemSelected(item);
    }
}
