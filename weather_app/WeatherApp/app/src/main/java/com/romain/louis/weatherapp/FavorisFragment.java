package com.romain.louis.weatherapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.preference.PreferenceManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import static com.romain.louis.weatherapp.MainActivity.appShared;
import static com.romain.louis.weatherapp.MainActivity.cityFav;
import static com.romain.louis.weatherapp.MainActivity.json;

/**
 * Created by romai on 23/03/2017.
 */

public class FavorisFragment extends AbstractFragment{
    CityAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.favoris, container, false);

        cityFav = new ArrayList<City>();

        Gson gsonCreate = new Gson();

        appShared = PreferenceManager.getDefaultSharedPreferences(this.getContext());
        Gson gsonShared = new Gson();
        if (appShared.getString("Favoris", null)!=null){
            json = appShared.getString("Favoris", "");
            Type type = new TypeToken<ArrayList<City>>(){}.getType();
            cityFav = gsonCreate.fromJson(json, type);
        }

        this.adapter = new CityAdapter(cityFav, new CityAdapter.OnCityListener() {
            @Override
            public void onCityClick(City city) {
                // Action lors du clic sur un item de la liste
                Intent intent = new Intent(getActivity(),DetailsVille.class);
                intent.putExtra("VILLE", (Parcelable) city);
                startActivity(intent);
            }

            @Override
            public void onCityLongClick(City city) {
                cityFav.remove(city);
            }

            public void onStarClick(City city){
                cityFav.remove(city);
                FavorisFragment.this.adapter.notifyDataSetChanged();

            }
        }, cityFav);

        RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerFavoris);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext(), LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(this.adapter);

        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        refresh();
    }

    @Override
    public void refresh() {
        if(adapter!=null){
            this.adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void orderListAZ() {
        Collections.sort(cityFav, new Comparator<City>() {
            @Override
            public int compare(City o1, City o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });
    }

}
