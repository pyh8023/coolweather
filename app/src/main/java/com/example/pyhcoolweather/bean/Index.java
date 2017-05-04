package com.example.pyhcoolweather.bean;

/**
 * Created by Administrator on 2016/9/11.
 */
public class Index {
    private String name;     //指数指标1名称
    private String code;     //指标编码
    private String index;    //等级
    private String details;  //描述
    private String otherName;//其它信息

    public Index(String name, String code, String index, String details, String otherName) {
        this.name = name;
        this.code = code;
        this.index = index;
        this.details = details;
        this.otherName = otherName;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public String getIndex() {
        return index;
    }

    public String getDetails() {
        return details;
    }

    public String getOtherName() {
        return otherName;
    }
}
