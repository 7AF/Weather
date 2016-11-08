package weather.vreme;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;


public class DayForecastFragment extends Fragment {
    private DayForecast dayForecast;
    private ImageView iconWeather;
    public static String city;

    public DayForecastFragment() {
    }

    public void setForecast(DayForecast dayForecast) {
        this.dayForecast = dayForecast;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_day_forecast, container, false);
        try {

            TextView getCity = (TextView) v.findViewById(R.id.cityView);
            TextView tempView = (TextView) v.findViewById(R.id.tempForecast);
            TextView descView = (TextView) v.findViewById(R.id.skydescForecast);
            TextView humidity = (TextView) v.findViewById(R.id.humidity);
            TextView pressure = (TextView) v.findViewById(R.id.pressure);
            getCity.setText("Location: " + city);
            tempView.setText("Min-max temp: " + (int) (dayForecast.forecastTemp.min - 273.15) + " - " + (int) (dayForecast.forecastTemp.max - 273.15));
            descView.setText("Day weather: " + dayForecast.weather.currentCondition1.getDescr1());
            humidity.setText(String.format("Humidity: " + "%.2f", dayForecast.weather.currentCondition1.getHumidity1()) + "%");
            pressure.setText(String.format("Pressure: " + "%.2f", dayForecast.weather.currentCondition1.getPressure1()) + "hPa");
            iconWeather = (ImageView) v.findViewById(R.id.forCondIcon);
// Now we retrieve the weather icon
            String name = dayForecast.weather.currentCondition1.getIcon1();
            System.out.println(name);
            Drawable data = null;
            try {
                data = fromAsset(name);
                iconWeather.setImageDrawable(data);
            } catch (Exception e) {

            }

            //JSONIconWeatherTask task = new JSONIconWeatherTask();
            //task.execute(new String[]{Weather.currentCondition.getIcon()});

            return v;
        } catch (Exception e) {
            return v;
        }
    }


    private class JSONIconWeatherTask extends AsyncTask<String, Void, byte[]> {
        @Override
        protected byte[] doInBackground(String... params) {
            byte[] data = null;
            try {
// Let's retrieve the icon
                data = ((new WeatherHttpClient()).getImage(params[0]));
            } catch (Exception e) {
                e.printStackTrace();
            }
            return data;
        }

        @Override
        protected void onPostExecute(byte[] data) {
            super.onPostExecute(data);

            if (data != null) {
                Bitmap img = BitmapFactory.decodeByteArray(data, 0, data.length);
                iconWeather.setImageBitmap(img);
            }
        }
    }

    public Drawable fromAsset(String iconName) throws IOException {
        // Get the AssetManager
        InputStream ims = getActivity().getAssets().open(iconName + ".png");
        Drawable d = Drawable.createFromStream(ims, null);

        return d;
    }

}