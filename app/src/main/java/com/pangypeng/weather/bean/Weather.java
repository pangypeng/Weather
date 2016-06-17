package com.pangypeng.weather.bean;

import java.io.Serializable;
import java.util.List;

/**
 * 作者 pangypeng
 * 时间 2016/6/9 19:33
 * 文件 Weather
 * 描述
 */
public class Weather implements Serializable {
    /**
     * {
         date: "2016-06-09",
         info: {},
         week: "四",
         nongli: "五月初五"
         },
     */
    private String date;
    private Info info;
    private String week;
    private String nongli;
    public Weather(){}

    public Weather(String date, Info info, String week, String nongli) {
        this.date = date;
        this.info = info;
        this.week = week;
        this.nongli = nongli;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Info getInfo() {
        return info;
    }

    public void setInfo(Info info) {
        this.info = info;
    }

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }

    public String getNongli() {
        return nongli;
    }

    public void setNongli(String nongli) {
        this.nongli = nongli;
    }

    @Override
    public String toString() {
        return "Weather{" +
                "date='" + date + '\'' +
                ", info=" + info +
                ", week='" + week + '\'' +
                ", nongli='" + nongli + '\'' +
                '}';
    }

    /**
     *
     */
    public class Info implements Serializable{
        /**
         * info: {
             dawn: [],
             night: [],
             day: []
         },
         */
        private List<String> dawn;
        private List<String> night;
        private List<String> day;

        public Info(){}

        public Info(List<String> dawn, List<String> night, List<String> day) {
            this.dawn = dawn;
            this.night = night;
            this.day = day;
        }

        public List<String> getDawn() {
            return dawn;
        }

        public void setDawn(List<String> dawn) {
            this.dawn = dawn;
        }

        public List<String> getNight() {
            return night;
        }

        public void setNight(List<String> night) {
            this.night = night;
        }

        public List<String> getDay() {
            return day;
        }

        public void setDay(List<String> day) {
            this.day = day;
        }

        @Override
        public String toString() {
            return "Info{" +
                    "dawn=" + dawn +
                    ", night=" + night +
                    ", day=" + day +
                    '}';
        }
    }
}
