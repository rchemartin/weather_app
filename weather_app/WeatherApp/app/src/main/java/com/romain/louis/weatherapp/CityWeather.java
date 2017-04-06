package com.romain.louis.weatherapp;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

/**
 * Created by romai on 23/03/2017.
 */

public class CityWeather {
    private JsonObject coord;
    private String base;
    private JsonObject main;
    private JsonArray weather;
    private int visibility;
    private JsonObject wind;
    private JsonObject clouds;
    private int dt;
    private JsonObject sys;
    private int id;
    private String name;
    private int cod;

    public CityWeather(){

    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public JsonObject getMain() {
        return main;
    }

    public void setMain(JsonObject main) {
        this.main = main;
    }

    public JsonArray getWeather() {
        return weather;
    }

    public void setWeather(JsonArray weather) {
        this.weather = weather;
    }

    public int getVisibility() {
        return visibility;
    }

    public void setVisibility(int visibility) {
        this.visibility = visibility;
    }

    public JsonObject getWind() {
        return wind;
    }

    public void setWind(JsonObject wind) {
        this.wind = wind;
    }

    public JsonObject getClouds() {
        return clouds;
    }

    public void setClouds(JsonObject clouds) {
        this.clouds = clouds;
    }

    public int getDt() {
        return dt;
    }

    public void setDt(int dt) {
        this.dt = dt;
    }

    public JsonObject getSys() {
        return sys;
    }

    public void setSys(JsonObject sys) {
        this.sys = sys;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    public JsonObject getCoord() {
        return coord;
    }

    public void setCoord(JsonObject coord) {
        this.coord = coord;
    }

    @Override
    public String toString() {
        return "CityWeather{" +
                "coord=" + coord +
                ", base='" + base + '\'' +
                ", main=" + main +
                ", weather=" + weather +
                ", visibility=" + visibility +
                ", wind=" + wind +
                ", clouds=" + clouds +
                ", dt=" + dt +
                ", sys=" + sys +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", cod=" + cod +
                '}';
    }
}

