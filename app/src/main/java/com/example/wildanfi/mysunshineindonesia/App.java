package com.example.wildanfi.mysunshineindonesia;

import android.app.Application;

import com.example.wildanfi.mysunshineindonesia.util.WeatherApi;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.greenrobot.eventbus.EventBus;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by WildanFI on 20/01/2018.
 */

public class App extends Application {
    private static App instance;
    private EventBus eventBus;
    private Gson gson;
    private Retrofit retrofit;


    public App() {
        instance = this;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        createEventBus();
        createGson();
        createRetrofit();
    }

    private void createGson() {
        gson = new GsonBuilder().create();
    }

    private void createEventBus() {
        eventBus = EventBus.builder()
                .logNoSubscriberMessages(false)
                .sendNoSubscriberEvent(false)
                .build();
    }

    private void createRetrofit() {
        retrofit = new Retrofit.Builder()
                .baseUrl(WeatherApi.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }


    public static App getInstance() {
        return instance;
    }

    public EventBus getEventBus() {
        return eventBus;
    }

    public Gson getGson() {
        return gson;
    }

    public Retrofit getRetrofit() {
        return retrofit;
    }

    public WeatherApi getWeatherApi(){
        return getRetrofit().create(WeatherApi.class);
    }

}
