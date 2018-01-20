package com.example.wildanfi.mysunshineindonesia.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.format.DateUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.wildanfi.mysunshineindonesia.App;
import com.example.wildanfi.mysunshineindonesia.R;
import com.example.wildanfi.mysunshineindonesia.model.Forecast;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by WildanFI on 14/01/2018.
 */

public class DetailWeatherActivity extends AppCompatActivity {
    @BindView(R.id.detail_weather_date)
    TextView detailWeatherDate;

    @BindView(R.id.detail_weather_image)
    ImageView detailWeatherImage;

    @BindView(R.id.detail_weather_desc)
    TextView detailWeatherDesc;

    @BindView(R.id.detail_weather_temp)
    TextView detailWeatherTemp;

    @BindView(R.id.detail_weather_humidity)
    TextView detailWeatherHumidity;

    @BindView(R.id.detail_weather_pressure)
    TextView detailWeatherPressure;

    @BindView(R.id.detail_weather_wind)
    TextView detailWeatherWind;

    private String shareContent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_detail);

        ButterKnife.bind(this);

        String forecastJson = getIntent().getStringExtra("forecast");
        Forecast forecast = App.getInstance().getGson().fromJson(forecastJson, Forecast.class);

        detailWeatherDate.setText(DateUtils.getRelativeTimeSpanString(this, forecast.getForecastDate() * 1000));
        Glide.with(this).load(getWeatherImageUrl(forecast.getWeatherList().get(0).getWeatherIcon())).into(detailWeatherImage);
        detailWeatherDesc.setText(forecast.getWeatherList().get(0).getWeatherDesc());
        detailWeatherTemp.setText(forecast.getTemperature().getTempDay() + getString(R.string.degree));

        detailWeatherHumidity.setText(forecast.getHumidity() + "%");
        detailWeatherPressure.setText(forecast.getPressure() + " hPa");
        detailWeatherWind.setText(forecast.getSpeed() + " km/h");

        shareContent = "Cuaca hari ini " + forecast.getWeatherList().get(0).getWeatherDesc() + " suhu " + forecast.getTemperature().getTempDay() + " " + getString(R.string.degree);
    }

    private String getWeatherImageUrl(String weatherIcon) {
        return "http://openweathermap.org/img/w/" + weatherIcon + ".png";
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.detail_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_share) {
            shareWeather();
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    private void shareWeather() {
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, shareContent);
        sendIntent.setType("text/plain");
        startActivity(sendIntent);
    }
}