package com.df.smallwater.smallwater.bean;

import java.util.List;

/**
 * Created by DF on 2018/6/3.
 */

public class Hanzi {


    /**
     * hanzilist : [{"id":9,"zi":"丨","cizu":" 丨 丨","bushou":"丨","pic":"","bihua":1,"pinyin":"gǔn"}]
     * result : 0
     * allcount : 1
     * msg : 已经更新单词，加油！~
     */

    private int result;
    private int allcount;
    private String msg;
    private List<HanzilistBean> hanzilist;

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public int getAllcount() {
        return allcount;
    }

    public void setAllcount(int allcount) {
        this.allcount = allcount;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<HanzilistBean> getHanzilist() {
        return hanzilist;
    }

    public void setHanzilist(List<HanzilistBean> hanzilist) {
        this.hanzilist = hanzilist;
    }

    public static class HanzilistBean {
        /**
         * id : 9
         * zi : 丨
         * cizu :  丨 丨
         * bushou : 丨
         * pic :
         * bihua : 1
         * pinyin : gǔn
         */

        private int id;
        private String zi;
        private String cizu;
        private String bushou;
        private String pic;
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

        public String getCizu() {
            return cizu;
        }

        public void setCizu(String cizu) {
            this.cizu = cizu;
        }

        public String getBushou() {
            return bushou;
        }

        public void setBushou(String bushou) {
            this.bushou = bushou;
        }

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
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
