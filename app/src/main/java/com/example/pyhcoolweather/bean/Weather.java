package com.example.pyhcoolweather.bean;

/**
 * Created by Administrator on 2016/9/11.
 */
public class Weather {
    private String city;      //城市
    private String pinyin;    //城市拼音
    private String citycode;  //城市编码
    private String date;      //日期
    private String time;      //发布时间
    private String postCode;  //邮编
    private String longitude; //经度
    private String latitude;  //维度
    private String altitude;  //海拔
    private String weather;   //天气情况
    private String temp;      //气温
    private String l_tmp;     //最低气温
    private String h_tmp;     //最高气温
    private String WD;   	  //风向
    private String WS;        //风力
    private String sunrise;   //日出时间
    private String sunset;    //日落时间

    public Weather(String city, String pinyin, String citycode, String date, String time, String postCode, String longitude, String latitude, String altitude, String weather, String temp, String l_tmp, String h_tmp, String WD, String WS, String sunrise, String sunset) {
        this.city = city;
        this.pinyin = pinyin;
        this.citycode = citycode;
        this.date = date;
        this.time = time;
        this.postCode = postCode;
        this.longitude = longitude;
        this.latitude = latitude;
        this.altitude = altitude;
        this.weather = weather;
        this.temp = temp;
        this.l_tmp = l_tmp;
        this.h_tmp = h_tmp;
        this.WD = WD;
        this.WS = WS;
        this.sunrise = sunrise;
        this.sunset = sunset;
    }

    public String getCity() {
        return city;
    }

    public String getPinyin() {
        return pinyin;
    }

    public String getCitycode() {
        return citycode;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getPostCode() {
        return postCode;
    }

    public String getLongitude() {
        return longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getAltitude() {
        return altitude;
    }

    public String getWeather() {
        return weather;
    }

    public String getTemp() {
        return temp;
    }

    public String getL_tmp() {
        return l_tmp;
    }

    public String getH_tmp() {
        return h_tmp;
    }

    public String getWD() {
        return WD;
    }

    public String getWS() {
        return WS;
    }

    public String getSunrise() {
        return sunrise;
    }

    public String getSunset() {
        return sunset;
    }
}
