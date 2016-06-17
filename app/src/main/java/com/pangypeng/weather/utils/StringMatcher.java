package com.pangypeng.weather.utils;

/**
 * 作者 pangypeng
 * 时间 2016/6/15 9:41
 * 文件 Weather
 * 描述
 */
public class StringMatcher {
    /**
     *
     * @param value item文本拼音首字母
     * @param keyword 索引字符
     * @return
     */
    public static boolean match(String value,String keyword){
        value = value.toUpperCase();
        // value和keyword都不能为空
        if (value == null || keyword == null){
            return false;
        }
        if (keyword.length() > value.length()){
            return false;
        }
        int i=0;// value的指针
        int j=0;// keyword的指针
        do {
            if (value.charAt(i) == keyword.charAt(j)){
                i++;
                j++;
            } else {
                i++;
            }
        }while (i<value.length() && j<keyword.length());

        return (j==keyword.length())?true:false;
    }
}
