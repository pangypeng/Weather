package com.pangypeng.weather.bean;

import com.pangypeng.weather.R;

import java.io.Serializable;
import java.util.List;
import java.util.concurrent.CompletionService;

/**
 * 作者 pangypeng
 * 时间 2016/6/9 19:42
 * 文件 Weather
 * 描述
 */
public class Life {
    /**
     * life: {
         date: "2016-6-9",
         info: {}
         },
     */
    private String date;
    private Info info;
    public Life(){}

    public Life(String date, Info info) {
        this.date = date;
        this.info = info;
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

    @Override
    public String toString() {
        return "Life{" +
                "date='" + date + '\'' +
                ", info=" + info +
                '}';
    }

    /**
     *
     */
    public class Info implements Serializable{
        /**
         * info: {
             kongtiao: [],
             yundong: [],
             ziwaixian: [],
             ganmao: [],
             xiche: [],
             wuran: [],
             chuanyi: []
             }
         */
        private List<String> kongtiao;
        private List<String> yundong;
        private List<String> ziwaixian;
        private List<String> ganmao;
        private List<String> xiche;
        private List<String> wuran;
        private List<String> chuanyi;
        public Info(){}

        public Info(List<String> kongtiao, List<String> yundong, List<String> ziwaixian, List<String> ganmao, List<String> xiche, List<String> wuran, List<String> chuanyi) {
            this.kongtiao = kongtiao;
            this.yundong = yundong;
            this.ziwaixian = ziwaixian;
            this.ganmao = ganmao;
            this.xiche = xiche;
            this.wuran = wuran;
            this.chuanyi = chuanyi;
        }

        public List<String> getKongtiao() {
            return kongtiao;
        }

        public void setKongtiao(List<String> kongtiao) {
            this.kongtiao = kongtiao;
        }

        public List<String> getYundong() {
            return yundong;
        }

        public void setYundong(List<String> yundong) {
            this.yundong = yundong;
        }

        public List<String> getZiwaixian() {
            return ziwaixian;
        }

        public void setZiwaixian(List<String> ziwaixian) {
            this.ziwaixian = ziwaixian;
        }

        public List<String> getGanmao() {
            return ganmao;
        }

        public void setGanmao(List<String> ganmao) {
            this.ganmao = ganmao;
        }

        public List<String> getXiche() {
            return xiche;
        }

        public void setXiche(List<String> xiche) {
            this.xiche = xiche;
        }

        public List<String> getWuran() {
            return wuran;
        }

        public void setWuran(List<String> wuran) {
            this.wuran = wuran;
        }

        public List<String> getChuanyi() {
            return chuanyi;
        }

        public void setChuanyi(List<String> chuanyi) {
            this.chuanyi = chuanyi;
        }

        @Override
        public String toString() {
            return "Info{" +
                    "kongtiao=" + kongtiao +
                    ", yundong=" + yundong +
                    ", ziwaixian=" + ziwaixian +
                    ", ganmao=" + ganmao +
                    ", xiche=" + xiche +
                    ", wuran=" + wuran +
                    ", chuanyi=" + chuanyi +
                    '}';
        }

        public List<String> getList(int position){
            switch (position){
                case 0:
                    return chuanyi;
                case 1:
                    return ganmao;
                case 2:
                    return kongtiao;
                case 3:
                    return xiche;
                case 4:
                    return yundong;
                case 5:
                    return ziwaixian;
            }
            return null;
        }
        public int getLifeImg(int position){
            switch (position){
                case 0:
                    return R.mipmap.dress;
                case 1:
                    return R.mipmap.hospital;
                case 2:
                    return R.mipmap.temperature;
                case 3:
                    return R.mipmap.car;
                case 4:
                    return R.mipmap.run;
                case 5:
                    return R.mipmap.umbrella;
            }
            return -1;
        }
        public String getLife(int position){
            switch (position){
                case 0:
                    return "穿衣指数：";
                case 1:
                    return "感冒指数：";
                case 2:
                    return "空调指数：";
                case 3:
                    return "汽车指数：";
                case 4:
                    return "运动指数：";
                case 5:
                    return "紫外线指数：";
            }
            return "";
        }
    }
}
