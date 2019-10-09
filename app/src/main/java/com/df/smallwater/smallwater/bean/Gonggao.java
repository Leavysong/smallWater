package com.df.smallwater.smallwater.bean;

import java.util.List;

/**
 * Created by DF on 2018/5/30.
 */

public class Gonggao {

    /**
     * allcount : 4
     * page : 1
     * pagenum : 1
     * gonggaolist : [{"content":"公告内容...123456123456","id":6,"time":"2018-05-07 04:08:24","youeryuanid":1},{"content":"公告内容...123456123456123456","id":5,"time":"2018-05-07 04:08:20","youeryuanid":1},{"content":"公告内容...432434","id":4,"time":"1524817437966","youeryuanid":1},{"content":"公告内容...4234234999999999","id":3,"time":"1524817547231","youeryuanid":1}]
     */

    private int allcount;
    private int page;
    private int pagenum;
    private List<GonggaolistBean> gonggaolist;

    public int getAllcount() {
        return allcount;
    }

    public void setAllcount(int allcount) {
        this.allcount = allcount;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPagenum() {
        return pagenum;
    }

    public void setPagenum(int pagenum) {
        this.pagenum = pagenum;
    }

    public List<GonggaolistBean> getGonggaolist() {
        return gonggaolist;
    }

    public void setGonggaolist(List<GonggaolistBean> gonggaolist) {
        this.gonggaolist = gonggaolist;
    }

    public static class GonggaolistBean {
        /**
         * content : 公告内容...123456123456
         * id : 6
         * time : 2018-05-07 04:08:24
         * youeryuanid : 1
         */

        private String content;
        private int id;
        private String time;
        private int youeryuanid;

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

        public int getYoueryuanid() {
            return youeryuanid;
        }

        public void setYoueryuanid(int youeryuanid) {
            this.youeryuanid = youeryuanid;
        }
    }
}
