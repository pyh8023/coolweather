package com.example.pyhcoolweather.application;

import android.app.Application;
import android.content.Context;

import com.baidu.apistore.sdk.ApiStoreSDK;

/**
 * Created by Administrator on 2016/9/11.
 */
public class MyApplication extends Application {

    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        ApiStoreSDK.init(this, "96d78ac98b443d22cad129d9046ce891");
        context = getApplicationContext();
    }

    public static Context getContext(){
        return context;
    }
}
