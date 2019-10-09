package com.df.smallwater.smallwater.bean;

/**
 * Created by DF on 2018/6/15.
 */

public class Version {


    /**
     * id : 4
     * versioncode : 3
     * versionname : 1.5.11
     * versioncontent :
     * versiontime : 2018-06-05
     * forceupdate : 0
     * phonetype : 0
     * downurl : http://s760644959.w3.luyouxia.net/smallwater/img/youeryuan/1528110429156.png
     */

    private int id;
    private int versioncode;
    private String versionname;
    private String versioncontent;
    private String versiontime;
    private int forceupdate;
    private int phonetype;
    private String downurl;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getVersioncode() {
        return versioncode;
    }

    public void setVersioncode(int versioncode) {
        this.versioncode = versioncode;
    }

    public String getVersionname() {
        return versionname;
    }

    public void setVersionname(String versionname) {
        this.versionname = versionname;
    }

    public String getVersioncontent() {
        return versioncontent;
    }

    public void setVersioncontent(String versioncontent) {
        this.versioncontent = versioncontent;
    }

    public String getVersiontime() {
        return versiontime;
    }

    public void setVersiontime(String versiontime) {
        this.versiontime = versiontime;
    }

    public int getForceupdate() {
        return forceupdate;
    }

    public void setForceupdate(int forceupdate) {
        this.forceupdate = forceupdate;
    }

    public int getPhonetype() {
        return phonetype;
    }

    public void setPhonetype(int phonetype) {
        this.phonetype = phonetype;
    }

    public String getDownurl() {
        return downurl;
    }

    public void setDownurl(String downurl) {
        this.downurl = downurl;
    }
}
