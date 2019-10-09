package com.df.smallwater.smallwater.bean;

import java.util.List;

/**
 * Created by DF on 2018/6/17.
 */

public class Youdao {


    /**
     * usPhonetic : ə; e
     * webExplains : [{"means":["A (Rainbow单曲)","А","A (音名)"],"key":"A"},{"means":["有些","几个","一些"],"key":"a few"},{"means":["许多","很多","一个又一个的"],"key":"many a"}]
     * deeplink : http://m.youdao.com/dict?le=eng&q=a
     * query : a
     * errorCode : 0
     * dictDeeplink : yddict://m.youdao.com/dict?le=eng&q=a
     * USSpeakUrl : http://openapi.youdao.com/ttsapi?q=a&langType=en&sign=8F76CC24C5D348D1CB1A1D5C9D83B602&salt=1529169424249&voice=6&format=mp3&appKey=18175d27093b4b09
     * ukPhonetic : ə; eɪ
     * phonetic : ə; eɪ
     * translations : ["一个"]
     * le : eng
     * from : en
     * to : zh-CHS
     * explains : ["art. 一；任一；每一"]
     * resultSpeakUrl : http://openapi.youdao.com/ttsapi?q=%E4%B8%80%E4%B8%AA&langType=zh-CHS&sign=8E0D1C94ECCF3B7DCD4AF86A31E228F8&salt=1529169424249&voice=0&format=mp3&appKey=18175d27093b4b09
     * speakUrl : http://openapi.youdao.com/ttsapi?q=a&langType=en&sign=8F76CC24C5D348D1CB1A1D5C9D83B602&salt=1529169424249&voice=0&format=mp3&appKey=18175d27093b4b09
     * UKSpeakUrl : http://openapi.youdao.com/ttsapi?q=a&langType=en&sign=8F76CC24C5D348D1CB1A1D5C9D83B602&salt=1529169424249&voice=5&format=mp3&appKey=18175d27093b4b09
     */

    private String usPhonetic;
    private String deeplink;
    private String query;
    private int errorCode;
    private String dictDeeplink;
    private String USSpeakUrl;
    private String ukPhonetic;
    private String phonetic;
    private String le;
    private String from;
    private String to;
    private String resultSpeakUrl;
    private String speakUrl;
    private String UKSpeakUrl;
    private List<WebExplainsBean> webExplains;
    private List<String> translations;
    private List<String> explains;

    public String getUsPhonetic() {
        return usPhonetic;
    }

    public void setUsPhonetic(String usPhonetic) {
        this.usPhonetic = usPhonetic;
    }

    public String getDeeplink() {
        return deeplink;
    }

    public void setDeeplink(String deeplink) {
        this.deeplink = deeplink;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getDictDeeplink() {
        return dictDeeplink;
    }

    public void setDictDeeplink(String dictDeeplink) {
        this.dictDeeplink = dictDeeplink;
    }

    public String getUSSpeakUrl() {
        return USSpeakUrl;
    }

    public void setUSSpeakUrl(String USSpeakUrl) {
        this.USSpeakUrl = USSpeakUrl;
    }

    public String getUkPhonetic() {
        return ukPhonetic;
    }

    public void setUkPhonetic(String ukPhonetic) {
        this.ukPhonetic = ukPhonetic;
    }

    public String getPhonetic() {
        return phonetic;
    }

    public void setPhonetic(String phonetic) {
        this.phonetic = phonetic;
    }

    public String getLe() {
        return le;
    }

    public void setLe(String le) {
        this.le = le;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getResultSpeakUrl() {
        return resultSpeakUrl;
    }

    public void setResultSpeakUrl(String resultSpeakUrl) {
        this.resultSpeakUrl = resultSpeakUrl;
    }

    public String getSpeakUrl() {
        return speakUrl;
    }

    public void setSpeakUrl(String speakUrl) {
        this.speakUrl = speakUrl;
    }

    public String getUKSpeakUrl() {
        return UKSpeakUrl;
    }

    public void setUKSpeakUrl(String UKSpeakUrl) {
        this.UKSpeakUrl = UKSpeakUrl;
    }

    public List<WebExplainsBean> getWebExplains() {
        return webExplains;
    }

    public void setWebExplains(List<WebExplainsBean> webExplains) {
        this.webExplains = webExplains;
    }

    public List<String> getTranslations() {
        return translations;
    }

    public void setTranslations(List<String> translations) {
        this.translations = translations;
    }

    public List<String> getExplains() {
        return explains;
    }

    public void setExplains(List<String> explains) {
        this.explains = explains;
    }

    public static class WebExplainsBean {
        /**
         * means : ["A (Rainbow单曲)","А","A (音名)"]
         * key : A
         */

        private String key;
        private List<String> means;

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public List<String> getMeans() {
            return means;
        }

        public void setMeans(List<String> means) {
            this.means = means;
        }
    }
}
