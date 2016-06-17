package com.pangypeng.weather.bean;

import java.io.Serializable;

/**
 * 作者 pangypeng
 * 时间 2016/6/9 19:35
 * 文件 Weather
 * 描述
 */
public class Result implements Serializable{
    /**
     data: {}
     */
    private Data data;
    public Result(){

    }

    public Result(Data data) {
        this.data = data;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Result{" +
                "data=" + data +
                '}';
    }
}
