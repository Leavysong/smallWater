package com.df.smallwater.smallwater.bean;

import java.util.List;

/**
 * Created by DF on 2018/6/2.
 */

public class Sign {

    /**
     * result : 0
     * count : 1
     * qingjialist : [{"content":"正常上学","id":1,"time":"2018-06-02","username":"15244691383","name":"李易双","banjiid":12}]
     */

    private int result;
    private int count;
    private List<QingjialistBean> qingjialist;

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<QingjialistBean> getQingjialist() {
        return qingjialist;
    }

    public void setQingjialist(List<QingjialistBean> qingjialist) {
        this.qingjialist = qingjialist;
    }

    public static class QingjialistBean {
        /**
         * content : 正常上学
         * id : 1
         * time : 2018-06-02
         * username : 15244691383
         * name : 李易双
         * banjiid : 12
         */

        private String content;
        private int id;
        private String time;
        private String username;
        private String name;
        private int banjiid;

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getBanjiid() {
            return banjiid;
        }

        public void setBanjiid(int banjiid) {
            this.banjiid = banjiid;
        }
    }
}
