package com.df.smallwater.smallwater.bean;

import java.util.List;

/**
 * Created by DF on 2018/5/30.
 */

public class Xinwen {

    /**
     * xinwenlist : [{"content":"新闻内容...+ imgFile.getName()+ imgFile.getName()+ imgFile.getName()","id":18,"time":"2018-05-07 11:55:51","title":"+ imgFile.getName()+","pic":"http://localhost:8080/smallwater/img/xinwen/1525708551159.jpg","youeryuanid":1}]
     * allcount : 1
     * page : 1
     * pagenum : 1
     */

    private int allcount;
    private int page;
    private int pagenum;
    private List<XinwenlistBean> xinwenlist;

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

    public List<XinwenlistBean> getXinwenlist() {
        return xinwenlist;
    }

    public void setXinwenlist(List<XinwenlistBean> xinwenlist) {
        this.xinwenlist = xinwenlist;
    }

    public static class XinwenlistBean {
        /**
         * content : 新闻内容...+ imgFile.getName()+ imgFile.getName()+ imgFile.getName()
         * id : 18
         * time : 2018-05-07 11:55:51
         * title : + imgFile.getName()+
         * pic : http://localhost:8080/smallwater/img/xinwen/1525708551159.jpg
         * youeryuanid : 1
         */

        private String content;
        private int id;
        private String time;
        private String title;
        private String pic;
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

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }

        public int getYoueryuanid() {
            return youeryuanid;
        }

        public void setYoueryuanid(int youeryuanid) {
            this.youeryuanid = youeryuanid;
        }
    }
}
