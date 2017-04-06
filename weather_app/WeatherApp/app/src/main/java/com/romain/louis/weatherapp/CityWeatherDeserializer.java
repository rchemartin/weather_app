package com.romain.louis.weatherapp;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

/**
 * Created by romai on 23/03/2017.
 */

public class CityWeatherDeserializer implements JsonDeserializer<CityWeather> {


    @Override
    public CityWeather deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();

        CityWeather cityWeather = new CityWeather();
        cityWeather.setBase(jsonObject.get("base").getAsString());
        cityWeather.setCoord(jsonObject.get("coord").getAsJsonObject());
        cityWeather.setWeather(jsonObject.get("weather").getAsJsonArray());
        cityWeather.setMain(jsonObject.get("main").getAsJsonObject());
        cityWeather.setVisibility(jsonObject.get("visibility").getAsInt());
        cityWeather.setWind(jsonObject.get("wind").getAsJsonObject());
        cityWeather.setClouds(jsonObject.get("clouds").getAsJsonObject());
        cityWeather.setDt(jsonObject.get("dt").getAsInt());
        cityWeather.setSys(jsonObject.get("sys").getAsJsonObject());
        cityWeather.setId(jsonObject.get("id").getAsInt());
        cityWeather.setName(jsonObject.get("name").getAsString());
        cityWeather.setCod(jsonObject.get("cod").getAsInt());

        return cityWeather;
    }
}
