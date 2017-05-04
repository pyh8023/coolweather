package com.example.pyhcoolweather.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/9/11.
 */
public class Today<Index> {
    private String date;       //今天日期
    private String week;       //今日星期
    private String curTemp;    //当前温度
    private String aqi;        //pm值
    private String fengxiang;  //风向
    private String fengli;     //风力
    private String hightemp;   //最高温度
    private String lowtemp;    //最低温度
    private String type;       //天气状态
    private List<Index> index; //指标列表

    public Today(String date, String week, String curTemp, String aqi, String fengxiang, String fengli, String hightemp, String lowtemp, String type, List<Index> index, List<Forecast> forecast, List<History> history) {
        this.date = date;
        this.week = week;
        this.curTemp = curTemp;
        this.aqi = aqi;
        this.fengxiang = fengxiang;
        this.fengli = fengli;
        this.hightemp = hightemp;
        this.lowtemp = lowtemp;
        this.type = type;
        this.index = index;
    }

    public String getDate() {
        return date;
    }

    public String getWeek() {
        return week;
    }

    public String getCurTemp() {
        return curTemp;
    }

    public String getAqi() {
        return aqi;
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

    public List<Index> getIndex() {
        return index;
    }

}
