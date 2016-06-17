package com.pangypeng.weather.bean;

import java.io.Serializable;

/**
 * 作者 pangypeng
 * 时间 2016/6/9 19:42
 * 文件 Weather
 * 描述
 */
public class PM25 {
    /**
     * pm25: {
         key: "",
         show_desc: 0,
         pm25: {},
         dateTime: "2016年06月09日16时",
         cityName: "北京"
         },
     */
    private String key;
    private int show_desc;
    private String dateTime;
    private String cityName;
    private NestedPM25 pm25;

    public PM25(){}

    public PM25(String key, int show_desc, String dateTime, String cityName, NestedPM25 pm25) {
        this.key = key;
        this.show_desc = show_desc;
        this.dateTime = dateTime;
        this.cityName = cityName;
        this.pm25 = pm25;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public int getShow_desc() {
        return show_desc;
    }

    public void setShow_desc(int show_desc) {
        this.show_desc = show_desc;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public NestedPM25 getPm25() {
        return pm25;
    }

    public void setPm25(NestedPM25 pm25) {
        this.pm25 = pm25;
    }

    @Override
    public String toString() {
        return "PM25{" +
                "key='" + key + '\'' +
                ", show_desc=" + show_desc +
                ", dateTime='" + dateTime + '\'' +
                ", cityName='" + cityName + '\'' +
                ", pm25=" + pm25 +
                '}';
    }

    /**
     * 内部类
     */
    public class NestedPM25 implements Serializable{
        /**
         * pm25: {
         curPm: "108",
         pm25: "58",
         pm10: "108",
         level: 3,
         quality: "轻度污染",
         des: "易感人群症状有轻度加剧，健康人群出现刺激症状。建议儿童、老年人及心脏病和呼吸系统疾病患者应减少长时间、高强度的户外锻炼。"
         },
         */
        private String curPm;
        private String pm25;
        private String pm10;
        private int level;
        private String quality;
        private String des;
        public NestedPM25(){}

        public NestedPM25(String curPm, String pm25, String pm10, int level, String quality, String des) {
            this.curPm = curPm;
            this.pm25 = pm25;
            this.pm10 = pm10;
            this.level = level;
            this.quality = quality;
            this.des = des;
        }

        public String getCurPm() {
            return curPm;
        }

        public void setCurPm(String curPm) {
            this.curPm = curPm;
        }

        public String getPm25() {
            return pm25;
        }

        public void setPm25(String pm25) {
            this.pm25 = pm25;
        }

        public String getPm10() {
            return pm10;
        }

        public void setPm10(String pm10) {
            this.pm10 = pm10;
        }

        public int getLevel() {
            return level;
        }

        public void setLevel(int level) {
            this.level = level;
        }

        public String getQuality() {
            return quality;
        }

        public void setQuality(String quality) {
            this.quality = quality;
        }

        public String getDes() {
            return des;
        }

        public void setDes(String des) {
            this.des = des;
        }

        @Override
        public String toString() {
            return "NestedPM25{" +
                    "curPm='" + curPm + '\'' +
                    ", pm25='" + pm25 + '\'' +
                    ", pm10='" + pm10 + '\'' +
                    ", level=" + level +
                    ", quality='" + quality + '\'' +
                    ", des='" + des + '\'' +
                    '}';
        }
    }
}
