package com.pangypeng.weather.utils;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Date;

/**
 * 作者 pangypeng
 * 时间 2016/6/9 18:20
 * 文件 Weather
 * 描述
 */
public class SaveUpdateTime {
    private Context mContext;
    public  static String FILENAME = "TIME";
    private static String KEY = "UpdateTime";
    public SaveUpdateTime(Context context){
        this.mContext =  context;
    }

    /**
     * 保存本次更新时间
     */
    public void setUpdateTime(){
        Date date = new Date();
        SharedPreferences sp = mContext.getSharedPreferences(FILENAME,Context.MODE_PRIVATE);
        sp.edit().putLong(KEY,date.getTime()).commit();
    }

    /**
     * 获取上次更新时间
     * @return
     */
    public Long getUpdateTime(){
        SharedPreferences sp = mContext.getSharedPreferences(FILENAME, Context.MODE_APPEND);
        return  sp.getLong(KEY,0);
    }

    /**
     * 判断是否需要更新
     * @return
     */
    public boolean isUpdate(){
        long oldTime = getUpdateTime();
        Date date = new Date();
        long newTime = date.getTime();
        if (oldTime==0 || newTime - oldTime > 360000){
            return true;
        }
        return false;
    }
}
