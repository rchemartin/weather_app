package com.romain.louis.weatherapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Parcelable;
import android.preference.PreferenceManager;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity{


    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    public static ArrayList<City> cityFav;

    Gson gson;
    public static SharedPreferences appShared;
    public static SharedPreferences.Editor editor;
    public static String json;
    public static Gson gsonShared;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        cityFav = new ArrayList<City>();

        Gson gsonCreate = new Gson();

        appShared = PreferenceManager.getDefaultSharedPreferences(this.getApplicationContext());
        gsonShared = new Gson();
        if (appShared.getString("Favoris", null)!=null){
            json = appShared.getString("Favoris", "");
            Type type = new TypeToken<ArrayList<City>>(){}.getType();
            cityFav = gsonCreate.fromJson(json, type);
        }




        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        this.setTitle("MyMétéo");

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);


        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

    }


/*
    Gson gsonFav = new Gson();

    String json = gsonFav.toJson(favoris);

                editor.putString("liste", json);*/



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.



        int id = item.getItemId();
        int idItem = mViewPager.getCurrentItem();
        final ListeFragment current;
        if(idItem==1){
            current = (ListeFragment) mSectionsPagerAdapter.getItem(idItem);

            current.orderListAZ();
            //current.notifyAdapter();
        }
        /*
        switch(id){
            case R.id.azOrder:;

                break;
            default:
                Log.i("error", "errorActionAZ");
        }
        */
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPause() {


        appShared = PreferenceManager.getDefaultSharedPreferences(this.getApplicationContext());
        editor = appShared.edit();
        gsonShared = new Gson();
        json = gsonShared.toJson(cityFav);
        editor.putString("Favoris", json);
        editor.commit();

        super.onPause();
    }

    @Override
    protected void onDestroy() {

        appShared = PreferenceManager.getDefaultSharedPreferences(this.getApplicationContext());
        editor = appShared.edit();
        gsonShared = new Gson();
        json = gsonShared.toJson(cityFav);
        editor.putString("Favoris", json);
        editor.commit();

        super.onDestroy();
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        int pageCourante;
        private RechercheFragment rechercheFragment = new RechercheFragment();
        private FavorisFragment favorisFragment = new FavorisFragment();
        private ListeFragment listeFragment = new ListeFragment();


        FavorisFragment fav;

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            this.pageCourante = position;
            switch (position){
                case 0:
                    return this.rechercheFragment;
                case 1:
                    return this.listeFragment;
                case 2:
                    return this.favorisFragment;
                default:
                    return null;


            }
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "RECHERCHE";
                case 1:
                    return "LISTE";
                case 2:
                    return "FAVORIS";
            }
            return null;
        }
    }
}
