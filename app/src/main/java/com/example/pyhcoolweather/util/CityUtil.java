package com.example.pyhcoolweather.util;

import com.example.pyhcoolweather.bean.City;
import com.example.pyhcoolweather.bean.RequestResult;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/9/11.
 */
public class CityUtil {

    public static List<String> getProvinces(){
        String provinceStr = "北京，天津，上海，重庆，河北，山西，辽宁，吉林，黑龙江，江苏，浙江，安徽，福建，江西，山东，河南，湖北，湖南，广东，海南，四川，贵州，云南，陕西，甘肃，青海，台湾，内蒙古，广西，西藏，宁夏，新疆，香港，澳门";
        String[] provinces = provinceStr.split("，");
        List<String> list;
        list = new ArrayList<>();
        for (int i = 0; i <provinces.length ; i++) {
            list.add(provinces[i]);
        }
        return list;
    }

    public static List<String> getCities(String response,List<String> list){
        RequestResult<City> result = JsonParseUtil.getCityResult(response);
        if (result.getErrMsg().equals("success")){
            List<City> allCity = result.getRetData();
            String lastCity = allCity.get(0).getDistrict_cn();
            list.add(allCity.get(0).getDistrict_cn());
            for (int i = 1; i < allCity.size(); i++) {
                String city = allCity.get(i).getDistrict_cn();
                if (!city.equals(lastCity)){
                    list.add(city);
                }
                lastCity = city;
            }
        }
        return list;
    }

    public static List<City> getCountyList(String response){
        RequestResult<City> result = JsonParseUtil.getCityResult(response);
        return result.getRetData();
    }

    public static List<City> getCountyList(String response,String district){
        RequestResult<City> result = JsonParseUtil.getCityResult(response);
        List<City> cities = result.getRetData();
        for (int i = 0; i < cities.size() ; i++) {
            City city = cities.get(i);
            if (city.getDistrict_cn().equals(district)){
                cities.remove(city);
            }
        }
        return cities;
    }
}
