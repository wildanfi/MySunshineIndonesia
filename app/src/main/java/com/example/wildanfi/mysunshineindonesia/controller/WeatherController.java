package com.example.wildanfi.mysunshineindonesia.controller;

import com.example.wildanfi.mysunshineindonesia.App;
import com.example.wildanfi.mysunshineindonesia.util.WeatherApi;

/**
 * Created by WildanFI on 20/01/2018.
 */

public class WeatherController {
    public void getWeather(){
        App.getInstance().getWeatherApi().getDailyForecast("Jakarta","json","metric",10, WeatherApi.API_KEY);
    }
}
