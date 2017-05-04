package com.example.pyhcoolweather.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.apistore.sdk.ApiCallBack;
import com.baidu.apistore.sdk.ApiStoreSDK;
import com.baidu.apistore.sdk.network.Parameters;
import com.example.pyhcoolweather.R;
import com.example.pyhcoolweather.bean.City;
import com.example.pyhcoolweather.util.CityUtil;
import com.example.pyhcoolweather.util.DatabaseUtil;
import com.example.pyhcoolweather.util.ProgressDialogUtil;
import com.example.pyhcoolweather.util.StringParse;

import java.util.List;

public class ChooseAreaActivity extends AppCompatActivity {

    private ProgressDialogUtil progressDialogUtil;

    private static List<String> dataList;
    private ListView cityList;
    private TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_area);
        initView();
    }

    private void initView() {
        if (getSupportActionBar()!=null){
            getSupportActionBar().hide();
        }
        cityList = (ListView) findViewById(R.id.city_list);
        title = (TextView) findViewById(R.id.title);
        dataList = CityUtil.getProvinces();
        final ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,dataList);
        cityList.setAdapter(adapter);
        title.setText("省份");
        cityList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                progressDialogUtil = new ProgressDialogUtil(ChooseAreaActivity.this);
                progressDialogUtil.showProgressDialog();
                String city = dataList.get(i);
                if (city.equals("重庆")||city.equals("北京")||city.equals("天津")||city.equals("上海")){
                    chooseCountry(city,adapter);
                }else {
                    Parameters para = new Parameters();
                    para.put("cityname",dataList.get(i));
                    ApiStoreSDK.execute("http://apis.baidu.com/apistore/weatherservice/citylist",ApiStoreSDK.GET,para,new ApiCallBack(){
                        @Override
                        public void onSuccess(int i, String s) {
                            s = StringParse.unicodeToString(s);
                            dataList.clear();
                            dataList = CityUtil.getCities(s,dataList);
                            adapter.notifyDataSetChanged();
                            cityList.setSelection(0);
                            title.setText("市");
                            cityList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                    chooseCountry(dataList.get(i),adapter);
                                }
                            });
                        }

                        @Override
                        public void onError(int i, String s, Exception e) {
                            Toast.makeText(ChooseAreaActivity.this,"加载失败！",Toast.LENGTH_SHORT).show();
                        }
                    });
                }
                progressDialogUtil.closeProgressDialog();
            }
        });

    }

    private void chooseCountry(final String city, final ArrayAdapter adapter) {
        Parameters para = new Parameters();
        para.put("cityname",city);
        progressDialogUtil.showProgressDialog();
        ApiStoreSDK.execute("http://apis.baidu.com/apistore/weatherservice/citylist",ApiStoreSDK.GET,para,new ApiCallBack(){
            @Override
            public void onSuccess(int i, String s) {
                s = StringParse.unicodeToString(s);
                dataList.clear();
                final List<City> country= CityUtil.getCountyList(s,city);
                for (int j = 0; j < country.size(); j++) {
                    dataList.add(country.get(j).getName_cn());
                }
                adapter.notifyDataSetChanged();
                cityList.setSelection(0);
                title.setText("区县");
                cityList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        Intent intent = new Intent(ChooseAreaActivity.this,WeatherActivity.class);
                        City city = country.get(i);
                        DatabaseUtil.deleteCity();
                        DatabaseUtil.insertCity(city.getDistrict_cn(),city.getArea_id());
                        startActivity(intent);
                    }
                });
            }
        });
        progressDialogUtil.closeProgressDialog();
    }

}
