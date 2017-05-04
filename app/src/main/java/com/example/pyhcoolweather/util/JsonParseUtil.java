package com.example.pyhcoolweather.util;

import com.example.pyhcoolweather.bean.City;
import com.example.pyhcoolweather.bean.Forecast;
import com.example.pyhcoolweather.bean.Index;
import com.example.pyhcoolweather.bean.RequestResult;
import com.example.pyhcoolweather.bean.Today;
import com.example.pyhcoolweather.bean.Weather;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by Administrator on 2016/9/11.
 */
public class JsonParseUtil {
    public static RequestResult<City> getCityResult(String response){
        Gson gson = new Gson();
        RequestResult<City> result = gson.fromJson(response,new TypeToken<RequestResult<City>>(){}.getType());
        return result;
    }

    public static RequestResult<Weather> getCityMsgResult(String response){
        Gson gson = new Gson();
        RequestResult<Weather> result = gson.fromJson(response,new TypeToken<RequestResult<Weather>>(){}.getType());
        return result;
    }

    public static Today<Index> getTodayWeatherMsg(String response){
        Today<Index> result = null;
        try {
            JSONObject object = new JSONObject(response);
            JSONObject today = object.getJSONObject("retData").getJSONObject("today");
            Gson gson = new Gson();
            result = gson.fromJson(today.toString(),new TypeToken<Today<Index>>(){}.getType());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static List<Forecast> getForecast(String response){
        List<Forecast>  result = null;
        try {
            JSONObject object = new JSONObject(response);
            JSONArray forecast = object.getJSONObject("retData").getJSONArray("forecast");
            Gson gson = new Gson();
            result = gson.fromJson(forecast.toString(),new TypeToken<List<Forecast>>(){}.getType());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return result;
    }
}
