package com.romain.louis.weatherapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import java.util.ArrayList;

import static com.romain.louis.weatherapp.MainActivity.cityFav;

/**
 * Created by romai on 23/03/2017.
 */

public class RechercheFragment extends AbstractFragment{

    private SearchView searchView;
    private RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.recherche, container, false);
        this.searchView = (SearchView) rootView.findViewById(R.id.searchView);
        this.recyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerRechercher);




        this.searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextChange(String newText) {
                Log.i("Search","typing");
                return true;
            }

            @Override
            public boolean onQueryTextSubmit(String query) {
                Log.i("Search","validating");
                ArrayList<City> arrayCity = ListeFragment.getArrayCity();
                ArrayList<City> arrayCitySearch = new ArrayList<City>();
                for(City c : arrayCity){

                    if(c.getName().toLowerCase().contains(searchView.getQuery().toString().toLowerCase())){
                        arrayCitySearch.add(c);
                    }
                }

                recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
                recyclerView.setAdapter(new CityAdapter(arrayCitySearch, new CityAdapter.OnCityListener() {
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
                    public void onStarClick(City city) {

                    }

                },cityFav));
                return true;
            }
        });
        return rootView;
    }

    @Override
    public void refresh() {

    }

    @Override
    public void orderListAZ() {
    }

}
