package com.df.smallwater.smallwater.utils;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by DF on 2018/6/9.
 */

public class MathUtil {

    private static MathUtil m ;

    public static List<String> MATH_LIST = new ArrayList<>();

    public static List<String> MATH_MEAN_LIST = new ArrayList<>();

    public static List<String> MATH_IMG_LIST = new ArrayList<String>();


    public static List<String> getMathList() {
        return MATH_LIST;
    }

    public static void setMathList(List<String> mathList) {
        MATH_LIST = mathList;
    }

    public static List<String> getMathMeanList() {
        return MATH_MEAN_LIST;
    }

    public static void setMathMeanList(List<String> mathMeanList) {
        MATH_MEAN_LIST = mathMeanList;
    }

    public static List<String> getMathImgList() {
        return MATH_IMG_LIST;
    }

    public static void setMathImgList(List<String> mathImgList) {
        MATH_IMG_LIST = mathImgList;
    }

    public static synchronized MathUtil getInstance(){
        if(m==null){
            init();
            m = new MathUtil();
            return m ;
        }else{
            return m ;
        }
    }




    private static void init(){

        MATH_LIST.add("0");
        MATH_LIST.add("1");
        MATH_LIST.add("2");
        MATH_LIST.add("3");
        MATH_LIST.add("4");
        MATH_LIST.add("5");
        MATH_LIST.add("6");
        MATH_LIST.add("7");
        MATH_LIST.add("8");
        MATH_LIST.add("9");

        MATH_MEAN_LIST.add("0个小朋友");
        MATH_MEAN_LIST.add("1个苹果");
        MATH_MEAN_LIST.add("2辆小车");
        MATH_MEAN_LIST.add("3条毛巾");
        MATH_MEAN_LIST.add("4只小猫");
        MATH_MEAN_LIST.add("5根铅笔");
        MATH_MEAN_LIST.add("6把椅子");
        MATH_MEAN_LIST.add("7张桌子");
        MATH_MEAN_LIST.add("8件衣服");
        MATH_MEAN_LIST.add("9个朋友");

        MATH_IMG_LIST.add("http://pacnmzg6f.bkt.clouddn.com/img0.png");
        MATH_IMG_LIST.add("http://pacnmzg6f.bkt.clouddn.com/img1.png");
        MATH_IMG_LIST.add("http://pacnmzg6f.bkt.clouddn.com/img2.png");
        MATH_IMG_LIST.add("http://pacnmzg6f.bkt.clouddn.com/img3.png");
        MATH_IMG_LIST.add("http://pacnmzg6f.bkt.clouddn.com/img4.png");
        MATH_IMG_LIST.add("http://pacnmzg6f.bkt.clouddn.com/img5.png");
        MATH_IMG_LIST.add("http://pacnmzg6f.bkt.clouddn.com/img6.png");
        MATH_IMG_LIST.add("http://pacnmzg6f.bkt.clouddn.com/img7.png");
        MATH_IMG_LIST.add("http://pacnmzg6f.bkt.clouddn.com/img8.png");
        MATH_IMG_LIST.add("http://pacnmzg6f.bkt.clouddn.com/img9.png");







    }



}
