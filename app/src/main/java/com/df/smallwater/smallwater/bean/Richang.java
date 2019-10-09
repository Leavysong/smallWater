package com.df.smallwater.smallwater.bean;

import java.util.List;

/**
 * Created by DF on 2018/5/30.
 */

public class Richang {


    /**
     * allcount : 1
     * page : 1
     * pagenum : 1
     * richanglist : [{"content":"日常内容...324553","id":14,"time":"2018-05-30 03:45:48","title":"9078078907809","banjiid":12,"pic":"http://localhost:8080/smallwater/img/richang/11527666348528.jpg"}]
     */

    private int allcount;
    private int page;
    private int pagenum;
    private List<RichanglistBean> richanglist;

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

    public List<RichanglistBean> getRichanglist() {
        return richanglist;
    }

    public void setRichanglist(List<RichanglistBean> richanglist) {
        this.richanglist = richanglist;
    }

    public static class RichanglistBean {
        /**
         * content : 日常内容...324553
         * id : 14
         * time : 2018-05-30 03:45:48
         * title : 9078078907809
         * banjiid : 12
         * pic : http://localhost:8080/smallwater/img/richang/11527666348528.jpg
         */

        private String content;
        private int id;
        private String time;
        private String title;
        private int banjiid;
        private String pic;

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

        public int getBanjiid() {
            return banjiid;
        }

        public void setBanjiid(int banjiid) {
            this.banjiid = banjiid;
        }

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }
    }
}
