package com.df.smallwater.smallwater.bean;

import java.util.List;

/**
 * Created by DF on 2018/5/30.
 */

public class Youeryuan {

    /**
     * youeryuan : {"content":"123456789f","id":1,"personal":"123","phone":"12345678","username":"","address":"123456","name":"123456","password":""}
     * youeryuanpic : [{"id":8,"title":"但是发射点发射点","pic":"http://localhost:8080/smallwater/img/youeryuan/1525708542977.jpg","youeryuanid":1},{"id":9,"title":"石帆胜丰沙发上犯得上电风扇发射点发顺丰","pic":"http://localhost:8080/smallwater/img/youeryuan/1527180902717.png","youeryuanid":1},{"id":10,"pic":"http://localhost:8080/smallwater/img/youeryuan/1527180919106.jpg","youeryuanid":1}]
     */

    private YoueryuanBean youeryuan;
    private List<YoueryuanpicBean> youeryuanpic;

    public YoueryuanBean getYoueryuan() {
        return youeryuan;
    }

    public void setYoueryuan(YoueryuanBean youeryuan) {
        this.youeryuan = youeryuan;
    }

    public List<YoueryuanpicBean> getYoueryuanpic() {
        return youeryuanpic;
    }

    public void setYoueryuanpic(List<YoueryuanpicBean> youeryuanpic) {
        this.youeryuanpic = youeryuanpic;
    }

    public static class YoueryuanBean {
        /**
         * content : 123456789f
         * id : 1
         * personal : 123
         * phone : 12345678
         * username :
         * address : 123456
         * name : 123456
         * password :
         */

        private String content;
        private int id;
        private String personal;
        private String phone;
        private String username;
        private String address;
        private String name;
        private String password;

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

        public String getPersonal() {
            return personal;
        }

        public void setPersonal(String personal) {
            this.personal = personal;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
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

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }

    public static class YoueryuanpicBean {
        /**
         * id : 8
         * title : 但是发射点发射点
         * pic : http://localhost:8080/smallwater/img/youeryuan/1525708542977.jpg
         * youeryuanid : 1
         */

        private int id;
        private String title;
        private String pic;
        private int youeryuanid;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
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
