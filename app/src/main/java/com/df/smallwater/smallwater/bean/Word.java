package com.df.smallwater.smallwater.bean;

import java.util.List;

/**
 * Created by DF on 2018/6/2.
 */

public class Word {


    /**
     * result : 0
     * allcount : 1
     * msg : 已经是最新词汇，加油！~
     * wordlist : [{"id":3,"pronunciation_uk":"[ə]","cizu":"a apple","mean":"art. 一（个） 每一（个） 任一（个）","word":"a","pic":"http://pacnmzg6f.bkt.clouddn.com/w1529427874981.jpg","pronunciation_us":"[eɪ]"}]
     */

    private int result;
    private int allcount;
    private String msg;
    private List<WordlistBean> wordlist;

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

    public List<WordlistBean> getWordlist() {
        return wordlist;
    }

    public void setWordlist(List<WordlistBean> wordlist) {
        this.wordlist = wordlist;
    }

    public static class WordlistBean {
        /**
         * id : 3
         * pronunciation_uk : [ə]
         * cizu : a apple
         * mean : art. 一（个） 每一（个） 任一（个）
         * word : a
         * pic : http://pacnmzg6f.bkt.clouddn.com/w1529427874981.jpg
         * pronunciation_us : [eɪ]
         */

        private int id;
        private String pronunciation_uk;
        private String cizu;
        private String mean;
        private String word;
        private String pic;
        private String pronunciation_us;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getPronunciation_uk() {
            return pronunciation_uk;
        }

        public void setPronunciation_uk(String pronunciation_uk) {
            this.pronunciation_uk = pronunciation_uk;
        }

        public String getCizu() {
            return cizu;
        }

        public void setCizu(String cizu) {
            this.cizu = cizu;
        }

        public String getMean() {
            return mean;
        }

        public void setMean(String mean) {
            this.mean = mean;
        }

        public String getWord() {
            return word;
        }

        public void setWord(String word) {
            this.word = word;
        }

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }

        public String getPronunciation_us() {
            return pronunciation_us;
        }

        public void setPronunciation_us(String pronunciation_us) {
            this.pronunciation_us = pronunciation_us;
        }
    }
}
