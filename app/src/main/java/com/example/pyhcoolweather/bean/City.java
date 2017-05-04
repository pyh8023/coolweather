package com.example.pyhcoolweather.bean;

/**
 * Created by Administrator on 2016/9/11.
 */
public class City{
     private String province_cn; //省
     private String district_cn; //市
     private String name_cn;     //区、县
     private String name_en;     //城市拼音
     private String area_id;  //城市代码

    public City(String province_cn, String district_cn, String name_cn, String name_en, String area_id) {
        this.province_cn = province_cn;
        this.district_cn = district_cn;
        this.name_cn = name_cn;
        this.name_en = name_en;
        this.area_id = area_id;
    }

    public String getProvince_cn() {
        return province_cn;
    }

    public String getDistrict_cn() {
        return district_cn;
    }

    public String getName_cn() {
        return name_cn;
    }

    public String getName_en() {
        return name_en;
    }

    public String getArea_id() {
        return area_id;
    }
}
