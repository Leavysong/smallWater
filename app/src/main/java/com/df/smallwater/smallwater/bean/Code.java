package com.df.smallwater.smallwater.bean;

/**
 * Created by DF on 2018/6/1.
 */

public class Code {

    /**
     * code : {"id":1,"username":"username","stime":"2018-05-30 03:23:30","mtime":"2018-05-22 01:31:28","action":"已激活","code":"8F870Z46","youeryuanid":1}
     */

    private CodeBean code;

    public CodeBean getCode() {
        return code;
    }

    public void setCode(CodeBean code) {
        this.code = code;
    }

    public static class CodeBean {
        /**
         * id : 1
         * username : username
         * stime : 2018-05-30 03:23:30
         * mtime : 2018-05-22 01:31:28
         * action : 已激活
         * code : 8F870Z46
         * youeryuanid : 1
         */

        private int id;
        private String username;
        private String stime;
        private String mtime;
        private String action;
        private String code;
        private int youeryuanid;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getStime() {
            return stime;
        }

        public void setStime(String stime) {
            this.stime = stime;
        }

        public String getMtime() {
            return mtime;
        }

        public void setMtime(String mtime) {
            this.mtime = mtime;
        }

        public String getAction() {
            return action;
        }

        public void setAction(String action) {
            this.action = action;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public int getYoueryuanid() {
            return youeryuanid;
        }

        public void setYoueryuanid(int youeryuanid) {
            this.youeryuanid = youeryuanid;
        }
    }
}