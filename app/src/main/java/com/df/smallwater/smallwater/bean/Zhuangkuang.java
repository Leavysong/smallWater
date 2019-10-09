package com.df.smallwater.smallwater.bean;

import java.util.List;

/**
 * Created by DF on 2018/5/30.
 */

public class Zhuangkuang {


    /**
     * allcount : 1
     * page : 1
     * pagenum : 1
     * zhuangkuanglist : [{"content":"编辑宝贝的近期状态。","id":22,"username":"15244691383","time":"2018-05-30 03:48:33"}]
     */

    private int allcount;
    private int page;
    private int pagenum;
    private List<ZhuangkuanglistBean> zhuangkuanglist;

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

    public List<ZhuangkuanglistBean> getZhuangkuanglist() {
        return zhuangkuanglist;
    }

    public void setZhuangkuanglist(List<ZhuangkuanglistBean> zhuangkuanglist) {
        this.zhuangkuanglist = zhuangkuanglist;
    }

    public static class ZhuangkuanglistBean {
        /**
         * content : 编辑宝贝的近期状态。
         * id : 22
         * username : 15244691383
         * time : 2018-05-30 03:48:33
         */

        private String content;
        private int id;
        private String username;
        private String time;

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

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }
    }
}
