package com.pangypeng.weather.utils;

import android.graphics.Color;

/**
 * 作者 pangypeng
 * 时间 2016/6/10 19:53
 * 文件 Weather
 * 描述
 */
public class ChooseAirQualityColor {
    public static int getColor(String colorId){
        int color  = Integer.parseInt(colorId);
        if (color>=0 && color <= 50){
            return Color.GREEN;
        } else if (color>50 && color<= 100){
            return Color.BLUE;
        } else if (color>100 && color<= 200){
            return Color.YELLOW;
        }
        return Color.RED;
    }
}
