package com.romain.louis.weatherapp;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.romain.louis.weatherapp.R;

import java.util.ArrayList;

/**
 * Created by romai on 23/03/2017.
 */

public class CityAdapter extends RecyclerView.Adapter<CityHolder>{

    private final ArrayList<City> cityFav;
    private OnCityListener listener;
    ArrayList<City> liste = new ArrayList<>();

    public interface OnCityListener {
        void onCityClick(City city);
        void onCityLongClick(City city);
        void onStarClick(City city);

    }

    public CityAdapter(ArrayList<City> cities, OnCityListener listener, ArrayList<City> cityFav){
        this.listener = listener;
        this.liste = cities;
        this.cityFav = cityFav;
    }

    @Override
    public CityHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row, parent, false);
        return new CityHolder(itemView, cityFav);
    }

    @Override
    public void onBindViewHolder(CityHolder holder, int position) {
        holder.bind(liste.get(position), listener);
    }

    @Override
    public int getItemCount() {
        return liste.size();
    }
}
