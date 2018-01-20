package com.example.wildanfi.mysunshineindonesia.controller;

import android.util.Log;

import com.example.wildanfi.mysunshineindonesia.App;
import com.example.wildanfi.mysunshineindonesia.WeatherEvent;
import com.example.wildanfi.mysunshineindonesia.model.Forecast;
import com.example.wildanfi.mysunshineindonesia.model.WeatherResponse;
import com.example.wildanfi.mysunshineindonesia.util.WeatherApi;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by WildanFI on 20/01/2018.
 */

public class WeatherController {
    private static final String TAG = "WeatherController";
    private EventBus eventBus = App.getInstance().getEventBus();

    public void getWeatherList() {
        Call<WeatherResponse> dailyForecast = App.getInstance().getWeatherApi().getDailyForecast("Jakarta", "json", "metric", 10, WeatherApi.API_KEY);
        dailyForecast.enqueue(new Callback<WeatherResponse>() {
            @Override
            public void onResponse(Call<WeatherResponse> call, Response<WeatherResponse> response) {
                Log.d(TAG, "onResponse: Success");
                Log.d(TAG, "onResponse: JSON = " + App.getInstance().getGson().toJson(response.body()));
                List<Forecast> forecastList = response.body().getForecastList();
                WeatherEvent weatherEvent = new WeatherEvent(true, forecastList);
                eventBus.post(weatherEvent);
            }

            @Override
            public void onFailure(Call<WeatherResponse> call, Throwable t) {
                Log.e(TAG, "onFailure: Failed");
                Log.e(TAG, "onFailure: Message = " + t.getMessage());
                WeatherEvent weatherEvent = new WeatherEvent(false, t.getMessage());
                eventBus.post(weatherEvent);
            }
        });
    }
}