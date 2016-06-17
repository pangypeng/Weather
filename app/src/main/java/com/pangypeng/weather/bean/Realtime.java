package com.pangypeng.weather.bean;

import android.view.Window;

import java.io.Serializable;

/**
 * 作者 pangypeng
 * 时间 2016/6/9 19:42
 * 文件 Weather
 * 描述
 */
public class Realtime implements Serializable {
    /**
     * realtime: {
         wind: {},
         time: "16:00:00",
         weather: {},
         dataUptime: 1465460881,
         date: "2016-06-09",
         city_code: "101010100",
         city_name: "北京",
         week: 4,
         moon: "五月初五"
         },
     */
    private Wind wind;
    private String time;
    private Weather weather;
    private long dataUptime;
    private String date;
    private String city_code;
    private String city_name;
    private int week;
    private String moon;

    public Realtime(){}

    public Realtime(Wind wind, String time, Weather weather, long dataUptime, String date, String city_code, String city_name, int week, String moon) {
        this.wind = wind;
        this.time = time;
        this.weather = weather;
        this.dataUptime = dataUptime;
        this.date = date;
        this.city_code = city_code;
        this.city_name = city_name;
        this.week = week;
        this.moon = moon;
    }

    public Wind getWind() {
        return wind;
    }

    public void setWind(Wind wind) {
        this.wind = wind;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Weather getWeather() {
        return weather;
    }

    public void setWeather(Weather weather) {
        this.weather = weather;
    }

    public long getDataUptime() {
        return dataUptime;
    }

    public void setDataUptime(long dataUptime) {
        this.dataUptime = dataUptime;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCity_code() {
        return city_code;
    }

    public void setCity_code(String city_code) {
        this.city_code = city_code;
    }

    public String getCity_name() {
        return city_name;
    }

    public void setCity_name(String city_name) {
        this.city_name = city_name;
    }

    public int getWeek() {
        return week;
    }

    public void setWeek(int week) {
        this.week = week;
    }

    public String getMoon() {
        return moon;
    }

    public void setMoon(String moon) {
        this.moon = moon;
    }

    @Override
    public String toString() {
        return "Realtime{" +
                "wind=" + wind +
                ", time='" + time + '\'' +
                ", weather=" + weather +
                ", dataUptime=" + dataUptime +
                ", date='" + date + '\'' +
                ", city_code='" + city_code + '\'' +
                ", city_name='" + city_name + '\'' +
                ", week=" + week +
                ", moon='" + moon + '\'' +
                '}';
    }

    /**
     * 风
     */
    public class Wind implements Serializable{
        /**
         * wind: {
             windspeed: "25.0",
             direct: "西南风",
             power: "4级",
             offset: null
             },
         */
        private String windspeed;
        private String direct;
        private String power;
        private String offset;

        public Wind(){}

        public Wind(String windspeed, String direct, String power, String offset) {
            this.windspeed = windspeed;
            this.direct = direct;
            this.power = power;
            this.offset = offset;
        }

        public String getWindspeed() {
            return windspeed;
        }

        public void setWindspeed(String windspeed) {
            this.windspeed = windspeed;
        }

        public String getDirect() {
            return direct;
        }

        public void setDirect(String direct) {
            this.direct = direct;
        }

        public String getPower() {
            return power;
        }

        public void setPower(String power) {
            this.power = power;
        }

        public String getOffset() {
            return offset;
        }

        public void setOffset(String offset) {
            this.offset = offset;
        }

        @Override
        public String toString() {
            return "Wind{" +
                    "windspeed='" + windspeed + '\'' +
                    ", direct='" + direct + '\'' +
                    ", power='" + power + '\'' +
                    ", offset='" + offset + '\'' +
                    '}';
        }
    }

    /**
     *
     */
    public class Weather implements Serializable{
        /**
         * weather: {
             humidity: "29",
             img: "0",
             info: "晴",
             temperature: "33"
             },
         */
        private String humidity;
        private String img;
        private String info;
        private String temperature;

        public Weather(){

        }

        public Weather(String humidity, String img, String info, String temperature) {
            this.humidity = humidity;
            this.img = img;
            this.info = info;
            this.temperature = temperature;
        }

        public String getHumidity() {
            return humidity;
        }

        public void setHumidity(String humidity) {
            this.humidity = humidity;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getInfo() {
            return info;
        }

        public void setInfo(String info) {
            this.info = info;
        }

        public String getTemperature() {
            return temperature;
        }

        public void setTemperature(String temperature) {
            this.temperature = temperature;
        }

        @Override
        public String toString() {
            return "Weather{" +
                    "humidity='" + humidity + '\'' +
                    ", img='" + img + '\'' +
                    ", info='" + info + '\'' +
                    ", temperature='" + temperature + '\'' +
                    '}';
        }
    }


}
