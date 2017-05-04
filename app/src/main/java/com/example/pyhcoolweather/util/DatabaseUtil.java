package com.example.pyhcoolweather.util;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.pyhcoolweather.application.MyApplication;

/**
 * Created by Administrator on 2016/9/17.
 */
public class DatabaseUtil {

    private static Context context = MyApplication.getContext();
    private static MyDatabaseHelper myDatabaseHelper = new MyDatabaseHelper(context,"City.db",null,1);

    public static void insertCity(String name,String areaId){
        SQLiteDatabase db = myDatabaseHelper.getWritableDatabase();
        db.execSQL("insert into myCity(name,areaId) values(?,?)",new String[]{name,areaId});
    }

    public static void deleteCity(){
        SQLiteDatabase db= myDatabaseHelper.getWritableDatabase();
        db.execSQL("delete from myCity");
    }

    public static String[] queryCity(){
        SQLiteDatabase db= myDatabaseHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from myCity",null);
        String[] city = null;
        if (cursor.moveToFirst()){
            String name = cursor.getString(cursor.getColumnIndex("name"));
            String cityId = cursor.getString(cursor.getColumnIndex("areaId"));
            city = new String[]{name,cityId};
        }
        cursor.close();
        return city;
    }

}
