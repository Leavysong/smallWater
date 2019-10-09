package com.df.smallwater.smallwater.bean;

/**
 * Created by DF on 2018/6/18.
 */

public class Title {

    String title = "";

    String pic = "" ;

    public Title() {
    }

    public Title(String title, String pic) {

        this.title = title;
        this.pic = pic;
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
}
