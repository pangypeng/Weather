package com.pangypeng.weather.utils;

import com.google.gson.Gson;
import com.pangypeng.weather.bean.JsonData;
import com.pangypeng.weather.bean.Result;

/**
 * 作者 pangypeng
 * 时间 2016/6/10 14:04
 * 文件 Weather
 * 描述
 */
public class JsonUtil {
    public static Result parseJson(String jsonsStr){
        Gson gson = new Gson();
        JsonData jsonData =  gson.fromJson(jsonsStr,JsonData.class);
        return jsonData.getResult();
    }
}
