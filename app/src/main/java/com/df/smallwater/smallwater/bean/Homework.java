package com.df.smallwater.smallwater.bean;

import java.util.List;

/**
 * Created by DF on 2018/5/30.
 */

public class Homework {


    /**
     * result : 0
     * homework : [{"shuxue":"1+1_2+1_2+2_2+3_3+1","yuwen":"金_木_水_火_土","id":29,"time":"2018-06-15","yingyu":"apple_banana_pen_dog_pig","banjiid":12}]
     */

    private int result;
    private List<HomeworkBean> homework;

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public List<HomeworkBean> getHomework() {
        return homework;
    }

    public void setHomework(List<HomeworkBean> homework) {
        this.homework = homework;
    }

    public static class HomeworkBean {
        /**
         * shuxue : 1+1_2+1_2+2_2+3_3+1
         * yuwen : 金_木_水_火_土
         * id : 29
         * time : 2018-06-15
         * yingyu : apple_banana_pen_dog_pig
         * banjiid : 12
         */

        private String shuxue;
        private String yuwen;
        private int id;
        private String time;
        private String yingyu;
        private int banjiid;

        public String getShuxue() {
            return shuxue;
        }

        public void setShuxue(String shuxue) {
            this.shuxue = shuxue;
        }

        public String getYuwen() {
            return yuwen;
        }

        public void setYuwen(String yuwen) {
            this.yuwen = yuwen;
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

        public String getYingyu() {
            return yingyu;
        }

        public void setYingyu(String yingyu) {
            this.yingyu = yingyu;
        }

        public int getBanjiid() {
            return banjiid;
        }

        public void setBanjiid(int banjiid) {
            this.banjiid = banjiid;
        }
    }
}
