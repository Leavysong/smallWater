package com.df.smallwater.smallwater.bean;

import java.util.List;

/**
 * Created by DF on 2018/6/16.
 */

public class Zidian {


    private List<ZidianBean> zidian;

    public List<ZidianBean> getZidian() {
        return zidian;
    }

    public void setZidian(List<ZidianBean> zidian) {
        this.zidian = zidian;
    }

    public static class ZidianBean {
        /**
         * id : 18
         * zi : 卡
         * bushou : 丨
         * bihua : 5
         * pinyin : kǎ,qiǎ
         */

        private int id;
        private String zi;
        private String bushou;
        private int bihua;
        private String pinyin;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getZi() {
            return zi;
        }

        public void setZi(String zi) {
            this.zi = zi;
        }

        public String getBushou() {
            return bushou;
        }

        public void setBushou(String bushou) {
            this.bushou = bushou;
        }

        public int getBihua() {
            return bihua;
        }

        public void setBihua(int bihua) {
            this.bihua = bihua;
        }

        public String getPinyin() {
            return pinyin;
        }

        public void setPinyin(String pinyin) {
            this.pinyin = pinyin;
        }
    }
}
