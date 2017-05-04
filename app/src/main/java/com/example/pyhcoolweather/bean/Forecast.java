package com.example.pyhcoolweather.bean;

/**
 * Created by Administrator on 2016/9/11.
 */
public class Forecast {
    private String date;
    private String week;
    private String fengxiang;
    private String fengli;
    private String hightemp;
    private String lowtemp;
    private String type;

    public Forecast(String date, String week, String fengxiang, String fengli, String hightemp, String lowtemp, String type) {
        this.date = date;
        this.week = week;
        this.fengxiang = fengxiang;
        this.fengli = fengli;
        this.hightemp = hightemp;
        this.lowtemp = lowtemp;
        this.type = type;
    }

    public String getDate() {
        return date;
    }

    public String getWeek() {
        return week;
    }

    public String getFengxiang() {
        return fengxiang;
    }

    public String getFengli() {
        return fengli;
    }

    public String getHightemp() {
        return hightemp;
    }

    public String getLowtemp() {
        return lowtemp;
    }

    public String getType() {
        return type;
    }
}
