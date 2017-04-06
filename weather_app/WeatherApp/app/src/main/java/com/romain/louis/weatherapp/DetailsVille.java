package com.romain.louis.weatherapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.TimeZone;

import static com.romain.louis.weatherapp.MainActivity.cityFav;

public class DetailsVille extends AppCompatActivity {

    public final static String API_KEY = "5a0b1491398087a10f95d2b4f1c9dd13";
    CityWeather cityWeather ;
    TextView idVille;
    TextView temp;
    TextView humidity;
    TextView temp_min;
    TextView temp_max;
    TextView vitesse_vent;
    TextView direction_vent;
    TextView lever_soleil;
    TextView coucher_soleil;
    ImageView weatherIcon;
    TextView weatherDesc;
    TextView clouds;
    TextView visibility;

    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    ArrayList<City> favoris;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_ville);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        editor = preferences.edit();
        favoris = new ArrayList<>();

        temp = (TextView) findViewById(R.id.temp);
        humidity = (TextView) findViewById(R.id.humidity);
        temp_min = (TextView) findViewById(R.id.temp_min);
        temp_max = (TextView) findViewById(R.id.temp_max);
        vitesse_vent = (TextView) findViewById(R.id.vitesse_vent);
        direction_vent = (TextView) findViewById(R.id.direction_vent);
        coucher_soleil = (TextView) findViewById(R.id.coucher_soleil);
        lever_soleil = (TextView) findViewById(R.id.lever_soleil);
        weatherIcon = (ImageView) findViewById(R.id.weatherIcon);
        weatherDesc = (TextView) findViewById(R.id.weatherDesc);
        clouds = (TextView) findViewById(R.id.clouds);
        visibility = (TextView) findViewById(R.id.visibility);

        Intent intent = getIntent();
        final City city = (City) intent.getParcelableExtra("VILLE");
        final boolean cityBool = intent.getExtras().getBoolean("cityFav");;

        CityWeather cityWeather ;

        setTitle(city.getName());

        String url = "http://api.openweathermap.org/data/2.5/weather?id="+city.getId()+"&appid="+API_KEY;

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        if(cityBool){
            fab.setImageDrawable(getResources().getDrawable(R.drawable.filled_star_34dp));
        }
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            if(!cityFav.contains(city)){
                cityFav.add(city);

                Snackbar.make(view, city.getName()+" ajouté aux favoris !", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }else{
                Toast.makeText(DetailsVille.this, city.getName()+" est déja dans vos favoris !", Toast.LENGTH_LONG).show();
            }


            }
        });


        GsonRequest<CityWeather> request = new GsonRequest<>(url, CityWeather.class, null,
                new Response.Listener<CityWeather>() {
                    @Override
                    public void onResponse(CityWeather city) {

                        Typeface roboto = Typeface.createFromAsset(getAssets(), "fonts/roboto.ttf");
                        Typeface quicksand = Typeface.createFromAsset(getAssets(), "fonts/quicksand.otf");

                        // Températures
                        if(city.getMain().get("temp")!=null){
                            temp.setText(String.valueOf(round(city.getMain().get("temp").getAsDouble()-273.15,1)));
                        }else{
                            temp.setText("N/A");
                        }

                        if(city.getMain().get("temp_min")!=null){
                            temp_min.setText(String.valueOf(round(city.getMain().get("temp_min").getAsDouble()-273.15,1)));
                        }else{
                            temp_min.setText("N/A");
                        }

                        if(city.getMain().get("temp_max")!=null){
                            temp_max.setText(String.valueOf(round(city.getMain().get("temp_max").getAsDouble()-273.15,1)));
                        }else{
                            temp_max.setText("N/A");
                        }

                        temp.setTypeface(quicksand);
                        temp_min.setTypeface(quicksand);
                        temp_max.setTypeface(quicksand);

                        // Pression atmosphérique
                        // Humidité (%)
                        if(city.getMain().get("humidity")!=null){
                            humidity.setText(String.valueOf(city.getMain().get("humidity").getAsInt())+"%");
                        }else{
                            humidity.setText("N/A");
                        }
                        humidity.setTypeface(quicksand);


                        // Vent
                        if(city.getWind().get("speed")!=null){
                            vitesse_vent.setText(String.valueOf(round(city.getWind().get("speed").getAsDouble()*3.6,1)+" km/h"));

                        }else{
                            vitesse_vent.setText("N/A");
                        }
                        if(city.getWind().get("deg")!=null){
                            direction_vent.setText(getDirection(round(city.getWind().get("deg").getAsDouble(),1)));
                        }else{
                            direction_vent.setText("N/A");
                        }

                        vitesse_vent.setTypeface(quicksand);
                        direction_vent.setTypeface(quicksand);

                        // Heure de lever du soleil
                        Timestamp stamp;
                        Date date;
                        String formattedDate;
                        SimpleDateFormat sdf;
                        if(city.getSys().get("sunrise")!=null){
                            stamp = new Timestamp(city.getSys().get("sunrise").getAsLong()*1000);
                            date = new Date(stamp.getTime());
                            sdf = new SimpleDateFormat("HH:mm");
                            sdf.setTimeZone(TimeZone.getTimeZone("GMT+1"));
                            formattedDate = sdf.format(date);
                            lever_soleil.setText(formattedDate);
                        }else{
                            lever_soleil.setText("N/A");
                        }

                        lever_soleil.setTypeface(roboto);

                        // Heure du coucher de soleil
                        if(city.getSys().get("sunset")!=null){
                            stamp = new Timestamp(city.getSys().get("sunset").getAsLong()*1000);
                            date = new Date(stamp.getTime());
                            sdf = new SimpleDateFormat("HH:mm");
                            sdf.setTimeZone(TimeZone.getTimeZone("GMT+1"));
                            formattedDate = sdf.format(date);
                            coucher_soleil.setText(formattedDate);
                        }else{
                            coucher_soleil.setText("N/A");
                        }
                        coucher_soleil.setTypeface(roboto);

                        if(city.getWeather().get(0).getAsJsonObject().get("icon")!=null){
                            Glide.with(DetailsVille.this).load("http://openweathermap.org/img/w/"+city.getWeather().get(0).getAsJsonObject().get("icon").getAsString()+".png").into(weatherIcon);
                        }

                        if(city.getWeather().get(0).getAsJsonObject().get("id")!=null){
                            weatherDesc.setText(TraductTemps.getDescTemps(city.getWeather().get(0).getAsJsonObject().get("id").getAsInt()));
                        }else{
                            weatherDesc.setText("N/A");
                        }
                        weatherDesc.setTypeface(quicksand);

                        if(city.getClouds().get("all")!=null){
                            clouds.setText(String.valueOf(city.getClouds().get("all").getAsString())+" %");
                        }else{
                            clouds.setText("N/A");
                        }
                        clouds.setTypeface(roboto);

                        if(city.getVisibility()!=0){
                            visibility.setText(String.valueOf(city.getVisibility()/1000)+" km");
                        }else{
                            visibility.setText("N/A");
                        }

                        visibility.setTypeface(roboto);

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                Toast.makeText(DetailsVille.this, "Erreur lors du chargement des données météo", Toast.LENGTH_LONG).show();
            }
        });
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(request);

    }
    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }
    public String getDirection(double degres){
        if((degres>337.5)||(degres<22.5)){
            return "Nord";
        }else if((degres>22.5)&&(degres<=67.5)){
            return "Nord-Est";
        }else if((degres>67.5)&&(degres<=112.5)){
            return "Est";
        }else if((degres>112.5)&&(degres<=157.5)){
            return "Sud-Est";
        }else if((degres>157.5)&&(degres<=202.5)){
            return "Sud";
        }else if((degres>202.5)&&(degres<=247.5)){
            return "Sud-Ouest";
        }else if((degres>247.5)&&(degres<=292.5)){
            return "Ouest";
        }else{
            return "Nord-Ouest";
        }
    }
}
