package com.example.wildanfi.mysunshineindonesia.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by WildanFI on 20/01/2018.
 */

public class Forecast {
    @SerializedName("dt")
    private long forecastDate;
    private double pressure;
    private double humidity;
    private double speed;
    @SerializedName("temp")
    private Temperature temperature;
    @SerializedName("weather")
    private List<Weather> weatherList;

    public class Temperature{
        @SerializedName("day")
        private double tempDay;

        public double getTempDay() {
            return tempDay;
        }
    }

    public class Weather{
        @SerializedName("main")
        private String weatherMain;
        @SerializedName("desc")
        private String weatherDesc;
        @SerializedName("icon")
        private String weatherIcon;

        public String getWeatherMain() {
            return weatherMain;
        }

        public String getWeatherDesc() {
            return weatherDesc;
        }

        public String getWeatherIcon() {
            return weatherIcon;
        }
    }

    public long getForecastDate() {
        return forecastDate;
    }

    public double getPressure() {
        return pressure;
    }

    public double getHumidity() {
        return humidity;
    }

    public double getSpeed() {
        return speed;
    }

    public Temperature getTemperature() {
        return temperature;
    }

    public List<Weather> getWeatherList() {
        return weatherList;
    }
}
