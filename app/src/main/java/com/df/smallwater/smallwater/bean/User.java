package com.df.smallwater.smallwater.bean;

/**
 * Created by DF on 2018/5/30.
 */

public class User {


    /**
     * result : 0
     * resultBean : {"mothername":"张三","birthday":"2012-04-03","phone":"12345678","sex":"男","banjiid":12,"code":"8F870Z46","password":"123456","id":1,"username":"15244691383","address":"哈尔滨市南岗区","name":"张小三","wechat":"12345678","youeryuanid":1}
     * msg : 欢迎张小三。
     */

    private int result;
    private ResultBeanBean resultBean;
    private String msg;

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public ResultBeanBean getResultBean() {
        return resultBean;
    }

    public void setResultBean(ResultBeanBean resultBean) {
        this.resultBean = resultBean;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public static class ResultBeanBean {
        /**
         * mothername : 张三
         * birthday : 2012-04-03
         * phone : 12345678
         * sex : 男
         * banjiid : 12
         * code : 8F870Z46
         * password : 123456
         * id : 1
         * username : 15244691383
         * address : 哈尔滨市南岗区
         * name : 张小三
         * wechat : 12345678
         * youeryuanid : 1
         */

        private String mothername;
        private String birthday;
        private String phone;
        private String sex;
        private int banjiid;
        private String code;
        private String password;
        private int id;
        private String username;
        private String address;
        private String name;
        private String wechat;
        private int youeryuanid;

        public String getMothername() {
            return mothername;
        }

        public void setMothername(String mothername) {
            this.mothername = mothername;
        }

        public String getBirthday() {
            return birthday;
        }

        public void setBirthday(String birthday) {
            this.birthday = birthday;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public int getBanjiid() {
            return banjiid;
        }

        public void setBanjiid(int banjiid) {
            this.banjiid = banjiid;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

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

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getWechat() {
            return wechat;
        }

        public void setWechat(String wechat) {
            this.wechat = wechat;
        }

        public int getYoueryuanid() {
            return youeryuanid;
        }

        public void setYoueryuanid(int youeryuanid) {
            this.youeryuanid = youeryuanid;
        }
    }
}
