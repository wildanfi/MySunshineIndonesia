package com.example.wildanfi.mysunshineindonesia.adapter;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.example.wildanfi.mysunshineindonesia.App;
import com.example.wildanfi.mysunshineindonesia.R;
import com.example.wildanfi.mysunshineindonesia.activity.DetailWeatherActivity;
import com.example.wildanfi.mysunshineindonesia.model.Forecast;
import com.example.wildanfi.mysunshineindonesia.viewholder.WeatherViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by WildanFI on 14/01/2018.
 */

public class WeatherAdapter extends RecyclerView.Adapter implements WeatherViewHolder.WeatherCallback {
    List<Forecast> forecasts;

    public WeatherAdapter() {
        forecasts = new ArrayList<>();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(WeatherViewHolder.getWeatherLayout(), parent, false);
        return new WeatherViewHolder(view, this);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        setWeatherItem((WeatherViewHolder) holder);

    }

    private void setWeatherItem(WeatherViewHolder holder) {
        Forecast forecast = forecasts.get(holder.getAdapterPosition());
        Glide.with(holder.itemView.getContext()).load(getWeatherImageUrl(forecast.getWeatherList().get(0).getWeatherIcon())).into(holder.getWeatherImage());
        holder.getWeatherDate().setText(DateUtils.getRelativeTimeSpanString(holder.itemView.getContext(), forecast.getForecastDate() * 1000));
        holder.getWeatherDesc().setText(forecast.getWeatherList().get(0).getWeatherDesc());
        holder.getWeatherTemp().setText(forecast.getTemperature().getTempDay() + holder.itemView.getContext().getString(R.string.degree));
    }

    private String getWeatherImageUrl(String weatherIcon) {
        return "http://openweathermap.org/img/w/" + weatherIcon + ".png";
    }

    @Override
    public int getItemCount() {
        return forecasts.size();
    }

    public void setData(List<Forecast> forecasts) {
        this.forecasts.addAll(forecasts);
        notifyDataSetChanged();
    }

    @Override
    public void onWeatherClick(WeatherViewHolder holder) {
        Intent intent = new Intent(holder.itemView.getContext(), DetailWeatherActivity.class);
        String forecastJson = App.getInstance().getGson().toJson(forecasts.get(holder.getAdapterPosition()));
        intent.putExtra("forecast", forecastJson);
        holder.itemView.getContext().startActivity(intent);
    }
}