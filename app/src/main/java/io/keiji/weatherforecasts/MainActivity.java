package io.keiji.weatherforecasts;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextView result;

    public class ApiTask extends GetWeatherForecastTask {
        @Override
        protected void onPostExecute(WeatherForecast data) {
            super.onPostExecute(data);

            if (data != null) {
                result.setText(data.location.area + " "
                        + data.location.prefecture + " "
                        + data.location.city);

                for (WeatherForecast.Forecast forecast : data.forecastList) {
                    // Windowsの場合は¥（円マーク）、MacやLinuxの場合は/（スラッシュ）を入力
                    result.append("\n");
                    result.append(forecast.dateLabel + " " + forecast.telop);
                }
            } else if (exception != null) {
                Toast.makeText(getApplicationContext(), exception.getMessage(),
                        Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        result = (TextView) findViewById(R.id.tv_result);

        new ApiTask().execute("400040");
    }
}
