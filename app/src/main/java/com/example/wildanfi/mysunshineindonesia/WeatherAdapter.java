package com.example.wildanfi.mysunshineindonesia;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

/**
 * Created by WildanFI on 14/01/2018.
 */

public class WeatherAdapter extends Adapter implements WeatherViewHolder.WeatherCallback {
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(WeatherViewHolder.getWeatherLayout(), parent, false);
        return new WeatherViewHolder(view, this);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 20;
    }

    @Override
    public void onWeatherClick(WeatherViewHolder holder) {
        Toast.makeText(holder.itemView.getContext(), "Ini item ke " + holder.getAdapterPosition(), Toast.LENGTH_SHORT).show();
    }
}