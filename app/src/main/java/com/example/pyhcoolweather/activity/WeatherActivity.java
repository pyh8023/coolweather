package com.example.pyhcoolweather.activity;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.apistore.sdk.ApiCallBack;
import com.baidu.apistore.sdk.ApiStoreSDK;
import com.baidu.apistore.sdk.network.Parameters;
import com.example.pyhcoolweather.R;
import com.example.pyhcoolweather.bean.Forecast;
import com.example.pyhcoolweather.bean.Index;
import com.example.pyhcoolweather.bean.Today;
import com.example.pyhcoolweather.util.DatabaseUtil;
import com.example.pyhcoolweather.util.JsonParseUtil;
import com.example.pyhcoolweather.util.ProgressDialogUtil;
import com.example.pyhcoolweather.util.StringParse;

import java.util.List;

public class WeatherActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView changeCity,currentTemp,cityName,weather,pm,fengli,fengxiang;
    private TextView todayWeather,todayTemp;
    private ImageView todayImg,tomorrowImg,nextTomorrowImg,nextNextTomorrowImg,refresh;
    private TextView tomorrowWeather,tomorrowTemp;
    private TextView nextTomorrowWeather,nextTomorrowTemp;
    private TextView nextNextTomorrowWeather,nextNextTomorrowTemp,nextNextDay;
    private TextView coldIndex,coldDetails;
    private TextView sunshineIndex,sunshineDatails;
    private TextView dressIndex,dressDetail;
    private TextView sportIndex,sportDatails;
    private TextView cleanCarIndex,cleanCarDatails;
    private TextView hangClothIndex,hangClothDetails;
    private String[] city;
    private ProgressDialogUtil progressDialogUtil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        progressDialogUtil = new ProgressDialogUtil(WeatherActivity.this);
        initView();
        updateWeather();
    }

    private void updateWeather(){
        progressDialogUtil.showProgressDialog();
        city = DatabaseUtil.queryCity();
        Parameters para = new Parameters();
        if(city == null){
            para.put("cityname","北京");
            para.put("cityid","101010100");
            city = new String[]{"北京","101010100"};
        }else {
            para.put("cityname",city[0]);
            para.put("cityid",city[1]);
        }
        ApiStoreSDK.execute("http://apis.baidu.com/apistore/weatherservice/recentweathers", ApiStoreSDK.GET,para,new ApiCallBack(){
            @Override
            public void onSuccess(int i, String s) {
                s = StringParse.unicodeToString(s);
                Today result = JsonParseUtil.getTodayWeatherMsg(s);
                List<Forecast> forecasts = JsonParseUtil.getForecast(s);
                loadView(result,forecasts);
            }
        });
        progressDialogUtil.closeProgressDialog();
    }

    private void loadView(Today result,List<Forecast> forecasts) {
        currentTemp.setText(result.getCurTemp().replace("℃","°"));
        cityName.setText(city[0]);
        String weatherType = result.getType();
        weather.setText(weatherType);
        todayWeather.setText(weatherType);
        setWeatherImg(weatherType,todayImg);
        if (result.getAqi()==null){
            pm.setText("PM 无");
        }else {
            pm.setText("PM "+result.getAqi());
        }
        fengli.setText(result.getFengli());
        fengxiang.setText(result.getFengxiang());
        todayTemp.setText(result.getHightemp()+"/"+result.getLowtemp());
        Forecast tomorrow = forecasts.get(0);
        String tomorrowType = tomorrow.getType();
        setWeatherImg(tomorrowType,tomorrowImg);
        tomorrowWeather.setText(tomorrowType);
        setWeatherImg(tomorrowType,tomorrowImg);
        tomorrowTemp.setText(tomorrow.getHightemp()+"/"+tomorrow.getLowtemp());
        Forecast nextTomorrow = forecasts.get(1);
        String nextTomorrowType = nextTomorrow.getType();
        nextTomorrowTemp.setText(nextTomorrow.getHightemp()+"/"+nextTomorrow.getLowtemp());
        nextTomorrowWeather.setText(nextTomorrowType);
        setWeatherImg(nextTomorrowType,nextTomorrowImg);
        Forecast nextNextTomorrow = forecasts.get(2);
        String nextNextTomorrowType = nextNextTomorrow.getType();
        nextNextTomorrowTemp.setText(nextNextTomorrow.getHightemp()+"/"+nextNextTomorrow.getLowtemp());
        nextNextTomorrowWeather.setText(nextNextTomorrowType);
        nextNextDay.setText(nextNextTomorrow.getWeek());
        setWeatherImg(nextNextTomorrowType,nextNextTomorrowImg);
        Index cold = (Index) result.getIndex().get(0);
        if (TextUtils.isEmpty(cold.getIndex())){
            coldIndex.setHeight(10);
        }else {
            coldIndex.setText(cold.getIndex());
        }
        coldDetails.setText(cold.getDetails());
        Index sunshine = (Index) result.getIndex().get(1);
        if (TextUtils.isEmpty(sunshine.getIndex())){
            sunshineIndex.setHeight(10);
        }else {
            sunshineIndex.setText(sunshine.getIndex());
        }
        sunshineDatails.setText(sunshine.getDetails());
        Index dress = (Index) result.getIndex().get(2);
        if (TextUtils.isEmpty(dress.getIndex())){
            dressIndex.setHeight(10);
        }else {
            dressIndex.setText(dress.getIndex());
        }
        dressDetail.setText(dress.getDetails());
        Index sport = (Index) result.getIndex().get(3);
        if (TextUtils.isEmpty(sport.getIndex())){
            sportIndex.setHeight(10);
        }else {
            sportIndex.setText(sport.getIndex());
        }
        sportDatails.setText(sport.getDetails());
        Index cleanCar = (Index) result.getIndex().get(4);
        if (TextUtils.isEmpty(cleanCar.getIndex())){
            cleanCarIndex.setHeight(10);
        }else{
            cleanCarIndex.setText(cleanCar.getIndex());
        }
        cleanCarDatails.setText(cleanCar.getDetails());
        Index hang = (Index) result.getIndex().get(5);
        if (TextUtils.isEmpty(hang.getIndex())){
            hangClothIndex.setHeight(10);
        }else {
            hangClothIndex.setText(hang.getIndex());
        }
        hangClothDetails.setText(hang.getDetails());

    }

    private void initView() {
        if (getSupportActionBar()!=null) {
            getSupportActionBar().hide();
        }
        refresh = (ImageView) findViewById(R.id.refresh);
        changeCity = (TextView) findViewById(R.id.change_city);
        currentTemp = (TextView) findViewById(R.id.current_temp);
        cityName = (TextView) findViewById(R.id.city);
        weather = (TextView) findViewById(R.id.weather);
        pm = (TextView) findViewById(R.id.pm);
        fengli = (TextView) findViewById(R.id.fengli);
        fengxiang = (TextView) findViewById(R.id.fengxiang);
        todayImg = (ImageView) findViewById(R.id.today_weather_img);
        todayTemp = (TextView) findViewById(R.id.today_temp);
        todayWeather = (TextView) findViewById(R.id.today_weather);
        tomorrowImg = (ImageView) findViewById(R.id.tomorrow_weather_img);
        nextTomorrowImg = (ImageView) findViewById(R.id.next_tomorrow_weather_img);
        tomorrowTemp = (TextView) findViewById(R.id.tomorrow_temp);
        tomorrowWeather = (TextView) findViewById(R.id.tomorrow_weather);
        nextTomorrowTemp = (TextView) findViewById(R.id.next_tomorrow_temp);
        nextTomorrowWeather = (TextView) findViewById(R.id.next_tomorrow_weather);
        nextNextTomorrowWeather = (TextView) findViewById(R.id.next_next_tomorrow_weather);
        nextNextTomorrowTemp = (TextView) findViewById(R.id.next_next_tomorrow_temp);
        nextNextTomorrowImg = (ImageView) findViewById(R.id.next_next_tomorrow_weather_img);
        nextNextDay = (TextView) findViewById(R.id.next_next_day);
        coldDetails = (TextView) findViewById(R.id.cold_details);
        coldIndex = (TextView) findViewById(R.id.cold_index);
        sunshineDatails = (TextView) findViewById(R.id.sunshine_details);
        sunshineIndex = (TextView) findViewById(R.id.sunshine_index);
        dressDetail = (TextView) findViewById(R.id.dress_details);
        dressIndex = (TextView) findViewById(R.id.dress_index);
        sportDatails = (TextView) findViewById(R.id.sport_details);
        sportIndex = (TextView) findViewById(R.id.sport_index);
        cleanCarDatails = (TextView) findViewById(R.id.clean_car_details);
        cleanCarIndex = (TextView) findViewById(R.id.clean_car_index);
        hangClothDetails = (TextView) findViewById(R.id.hang_cloth_details);
        hangClothIndex = (TextView) findViewById(R.id.hang_cloth_index);
        changeCity.setOnClickListener(this);
        refresh.setOnClickListener(this);
    }

    private void setWeatherImg(String weatherType,ImageView imageView){
        if (weatherType.contains("雷")){
            imageView.setImageResource(R.drawable.thunder);
        }else if(weatherType.contains("雨")) {
            imageView.setImageResource(R.drawable.rain);
        }else if (weatherType.contains("阴")||weatherType.contains("多云")){
            imageView.setImageResource(R.drawable.cloudy);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.change_city :
                Intent intent = new Intent(WeatherActivity.this,ChooseAreaActivity.class);
                startActivity(intent);
                break;
            case R.id.refresh :
                updateWeather();
                Toast.makeText(WeatherActivity.this, "更新成功", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }
    class AutoUpdateService extends Service {
        @Nullable
        @Override
        public IBinder onBind(Intent intent) {
            return null;
        }

        @Override
        public int onStartCommand(Intent intent, int flags, int startId) {
            return super.onStartCommand(intent, flags, startId);
        }
    }
}
