package com.romain.louis.weatherapp;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.romain.louis.weatherapp.R;

import java.util.ArrayList;

/**
 * Created by romai on 23/03/2017.
 */

public class CityHolder extends RecyclerView.ViewHolder{
    private TextView nomVille;
    private ImageView favIcon;
    private ArrayList<City> cityFav;


    public CityHolder(View itemView, ArrayList<City> cityFav) {
        super(itemView);
        this.cityFav = cityFav;
        favIcon = (ImageView) itemView.findViewById(R.id.favIcon);
        nomVille = (TextView) itemView.findViewById(R.id.villeName);
    }

    public void bind(final City city, final CityAdapter.OnCityListener listener){
        // Affectation du listener
        if(listener != null) {
            nomVille.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onCityClick(city);
                }
            });

            nomVille.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    listener.onCityLongClick(city);
                    return false;
                }
            });

            favIcon.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view) {
                    listener.onStarClick(city);
                }
            });
        }
        favIcon.setImageResource(R.drawable.star_34dp);
        if(cityFav.contains(city)){
            favIcon.setImageResource(R.drawable.filled_star_34dp);
        }
        nomVille.setText(city.getName());
    }
}
