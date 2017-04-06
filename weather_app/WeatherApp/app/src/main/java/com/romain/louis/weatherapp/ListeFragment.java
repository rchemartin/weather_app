package com.romain.louis.weatherapp;

import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.os.Parcelable;
import android.preference.PreferenceManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import static com.romain.louis.weatherapp.MainActivity.appShared;
import static com.romain.louis.weatherapp.MainActivity.cityFav;
import static com.romain.louis.weatherapp.MainActivity.json;


/**
 * Created by romai on 23/03/2017.
 */

public class ListeFragment extends AbstractFragment{

    private CityAdapter adapter;
    static ArrayList<City> arrayCity;
    ArrayList<City> cityFav;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        View rootView = inflater.inflate(R.layout.liste, container, false);

        arrayCity = new ArrayList<>();

        try {
            AssetManager assetManager = this.getContext().getAssets();
            InputStream inputStream = assetManager.open("city_france.json");
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder out = new StringBuilder();
            String line;
            Gson g = new Gson();
            while ((line = reader.readLine()) != null) {
                out.append(line);
            }

            String stringCities = out.toString();

            arrayCity = g.fromJson(stringCities, new TypeToken<ArrayList<City>>(){}.getType());

        } catch (IOException e) {
            e.printStackTrace();
        }

        cityFav = new ArrayList<City>();

        Gson gsonCreate = new Gson();

        appShared = PreferenceManager.getDefaultSharedPreferences(this.getContext());
        Gson gsonShared = new Gson();
        if (appShared.getString("Favoris", null)!=null){
            json = appShared.getString("Favoris", "");
            Type type = new TypeToken<ArrayList<City>>(){}.getType();

            cityFav = gsonCreate.fromJson(json, type);
        }

        adapter = new CityAdapter(arrayCity, new CityAdapter.OnCityListener() {
            @Override
            public void onCityClick(City city) {
                // Action lors du clic sur un item de la liste
                Intent intent = new Intent(getActivity(),DetailsVille.class);
                intent.putExtra("VILLE", (Parcelable) city);

                if(cityFav.contains(city)){
                    intent.putExtra("cityFav", true);
                }else{
                    intent.putExtra("cityFav", false);
                }

                startActivity(intent);
            }

            @Override
            public void onCityLongClick(City city) {
                // Autre action lors du clic long sur un item de la liste
            }

            @Override
            public void onStarClick(City city){

            }

        },cityFav);

        RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext(), LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(this.adapter);


        return rootView;
    }

    @Override
    public void refresh() {
        this.adapter.notifyDataSetChanged();
    }

    @Override
    public void orderListAZ() {
        Collections.sort(arrayCity, new Comparator<City>() {
            @Override
            public int compare(City o1, City o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });
        Log.i("Liste", arrayCity.toString());
        this.adapter.notifyDataSetChanged();
    }

    public static ArrayList<City> getArrayCity(){
        return arrayCity;
    }

    public void notifyAdapter(){
        this.adapter.notifyDataSetChanged();
    }


}
