package com.pangypeng.weather.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.ContextThemeWrapper;

/**
 * 作者 pangypeng
 * 时间 2016/6/10 14:35
 * 文件 Weather
 * 描述
 */
public class SaveOrGetCity {
    private Context mContext;
    private static String CITY = "CITY";
    public SaveOrGetCity(Context context){
        this.mContext = context;
    }

    public void saveCity(String cityName){
        SharedPreferences sp = mContext.getSharedPreferences(CITY,Context.MODE_PRIVATE);
        sp.edit().putString("cityName",cityName).commit();
    }

    public String getCity(){
        SharedPreferences sp = mContext.getSharedPreferences(CITY,Context.MODE_PRIVATE);
        return sp.getString("cityName","");
    }
}
