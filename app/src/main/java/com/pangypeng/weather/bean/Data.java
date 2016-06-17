package com.pangypeng.weather.bean;

import java.io.Serializable;
import java.util.List;

/**
 * 作者 pangypeng
 * 时间 2016/6/9 19:38
 * 文件 Weather
 * 描述
 */
public class Data implements Serializable {
    /**
         realtime: {},
         life: {},
         weather: [],
         pm25: {},
         date: null,
         isForeign: 0

     */
    private int isForeign;
    private String date;
    private Life life;
    private PM25 pm25;
    private Realtime realtime;
    private List<Weather> weather;
    public Data(){}
    public Data(int isForeign, String date, Life life, PM25 pm25, Realtime realtime, List<Weather> weather) {
        this.isForeign = isForeign;
        this.date = date;
        this.life = life;
        this.pm25 = pm25;
        this.realtime = realtime;
        this.weather = weather;
    }

    public int getIsForeign() {
        return isForeign;
    }

    public void setIsForeign(int isForeign) {
        this.isForeign = isForeign;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Life getLife() {
        return life;
    }

    public void setLife(Life life) {
        this.life = life;
    }

    public PM25 getPm25() {
        return pm25;
    }

    public void setPm25(PM25 pm25) {
        this.pm25 = pm25;
    }

    public Realtime getRealtime() {
        return realtime;
    }

    public void setRealtime(Realtime realtime) {
        this.realtime = realtime;
    }

    public List<Weather> getWeather() {
        return weather;
    }

    public void setWeather(List<Weather> weather) {
        this.weather = weather;
    }

    @Override
    public String toString() {
        return "Data{" +
                "isForeign=" + isForeign +
                ", date='" + date + '\'' +
                ", life=" + life +
                ", pm25=" + pm25 +
                ", realtime=" + realtime +
                ", weather=" + weather +
                '}';
    }
}
