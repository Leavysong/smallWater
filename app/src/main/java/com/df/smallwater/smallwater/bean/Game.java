package com.df.smallwater.smallwater.bean;

/**
 * Created by DF on 2018/6/23.
 */

public class Game {
    String name ;
    String content ;
    int  img ;

    public Game() {
    }

    public Game(String name, String content, int img) {

        this.name = name;
        this.content = content;
        this.img = img;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }
}
