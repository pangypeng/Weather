package com.pangypeng.weather.sql;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

/**
 * 作者 pangypeng
 * 时间 2016/6/10 22:41
 * 文件 Weather
 * 描述
 */
public class SelectedCity {
    private static MyOpenHelper myOpenHelper;
    private static SQLiteDatabase mDb;

    public static ArrayList<String> queryCitiesByCity(Context context){
        myOpenHelper = new MyOpenHelper(context);
        mDb = myOpenHelper.getWritableDatabase();
        ArrayList<String> cities = new ArrayList<String>();
        Cursor cursor = mDb.query("city",new String[]{"city"},null,null,null,null,"pinyin");
        while (cursor.moveToNext()){
            cities.add(cursor.getString(cursor.getColumnIndex("city")));
        }
        Log.e("SelectedCity",cities.size()+"");
        return cities;
    }
    public static ArrayList<String> queryCitiesByPinyin(Context context){
        myOpenHelper = new MyOpenHelper(context);
        SQLiteDatabase database = myOpenHelper.getWritableDatabase();
        ArrayList<String> cities = new ArrayList<String>();
        Cursor cursor = database.query("city",new String[]{"pinyin"},null,null,null,null,"pinyin");
        while (cursor.moveToNext()){
            cities.add(cursor.getString(cursor.getColumnIndex("pinyin")));
        }
        return cities;
    }
}
