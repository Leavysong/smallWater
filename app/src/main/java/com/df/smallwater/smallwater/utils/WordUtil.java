package com.df.smallwater.smallwater.utils;

import com.df.smallwater.smallwater.R;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by DF on 2018/6/9.
 */

public class WordUtil {

    private static WordUtil w ;

    public static List<String> WORD_LIST = new ArrayList<>();

    public static List<String> BIG_WORD_LIST = new ArrayList<>();

    public static List<String> WORD_IMG_LIST = new ArrayList<String>();

    public static List<Integer> WORD_ICON_LIST = new ArrayList<Integer>();





    public static synchronized WordUtil getInstance(){
        if(w==null){
            init();
            w = new WordUtil();
            return w ;
        }else{
            return w ;
        }
    }


    public static List<String> getWordList() {
        return WORD_LIST;
    }

    public static void setWordList(List<String> wordList) {
        WORD_LIST = wordList;
    }

    public static List<String> getBigWordList() {
        return BIG_WORD_LIST;
    }

    public static void setBigWordList(List<String> bigWordList) {
        BIG_WORD_LIST = bigWordList;
    }

    public static List<String> getWordImgList() {
        return WORD_IMG_LIST;
    }

    public static void setWordImgList(List<String> wordImgList) {
        WORD_IMG_LIST = wordImgList;
    }

    public static List<Integer> getWordIconList() {
        return WORD_ICON_LIST;
    }

    public static void setWordIconList(List<Integer> wordIconList) {
        WORD_ICON_LIST = wordIconList;
    }




    private static void init(){

        BIG_WORD_LIST.add("A");
        BIG_WORD_LIST.add("B");
        BIG_WORD_LIST.add("C");
        BIG_WORD_LIST.add("D");
        BIG_WORD_LIST.add("E");
        BIG_WORD_LIST.add("F");
        BIG_WORD_LIST.add("G");
        BIG_WORD_LIST.add("H");
        BIG_WORD_LIST.add("I");
        BIG_WORD_LIST.add("J");
        BIG_WORD_LIST.add("K");
        BIG_WORD_LIST.add("L");
        BIG_WORD_LIST.add("M");
        BIG_WORD_LIST.add("N");
        BIG_WORD_LIST.add("O");
        BIG_WORD_LIST.add("P");
        BIG_WORD_LIST.add("Q");
        BIG_WORD_LIST.add("R");
        BIG_WORD_LIST.add("S");
        BIG_WORD_LIST.add("T");
        BIG_WORD_LIST.add("U");
        BIG_WORD_LIST.add("V");
        BIG_WORD_LIST.add("W");
        BIG_WORD_LIST.add("X");
        BIG_WORD_LIST.add("Y");
        BIG_WORD_LIST.add("Z");

        WORD_LIST.add("a");
        WORD_LIST.add("b");
        WORD_LIST.add("c");
        WORD_LIST.add("d");
        WORD_LIST.add("e");
        WORD_LIST.add("f");
        WORD_LIST.add("g");
        WORD_LIST.add("h");
        WORD_LIST.add("i");
        WORD_LIST.add("j");
        WORD_LIST.add("k");
        WORD_LIST.add("l");
        WORD_LIST.add("m");
        WORD_LIST.add("n");
        WORD_LIST.add("o");
        WORD_LIST.add("p");
        WORD_LIST.add("q");
        WORD_LIST.add("r");
        WORD_LIST.add("s");
        WORD_LIST.add("t");
        WORD_LIST.add("u");
        WORD_LIST.add("v");
        WORD_LIST.add("w");
        WORD_LIST.add("x");
        WORD_LIST.add("y");
        WORD_LIST.add("z");


        WORD_IMG_LIST.add("http://pacnmzg6f.bkt.clouddn.com/worda.png");
        WORD_IMG_LIST.add("http://pacnmzg6f.bkt.clouddn.com/wordb.png");
        WORD_IMG_LIST.add("http://pacnmzg6f.bkt.clouddn.com/wordc.png");
        WORD_IMG_LIST.add("http://pacnmzg6f.bkt.clouddn.com/wordd.png");
        WORD_IMG_LIST.add("http://pacnmzg6f.bkt.clouddn.com/worde.png");
        WORD_IMG_LIST.add("http://pacnmzg6f.bkt.clouddn.com/wordf.png");
        WORD_IMG_LIST.add("http://pacnmzg6f.bkt.clouddn.com/wordg.png");
        WORD_IMG_LIST.add("http://pacnmzg6f.bkt.clouddn.com/wordh.png");
        WORD_IMG_LIST.add("http://pacnmzg6f.bkt.clouddn.com/wordi.png");
        WORD_IMG_LIST.add("http://pacnmzg6f.bkt.clouddn.com/wordj.png");
        WORD_IMG_LIST.add("http://pacnmzg6f.bkt.clouddn.com/wordk.png");
        WORD_IMG_LIST.add("http://pacnmzg6f.bkt.clouddn.com/wordl.png");
        WORD_IMG_LIST.add("http://pacnmzg6f.bkt.clouddn.com/wordm.png");
        WORD_IMG_LIST.add("http://pacnmzg6f.bkt.clouddn.com/wordn.png");
        WORD_IMG_LIST.add("http://pacnmzg6f.bkt.clouddn.com/wordo.png");
        WORD_IMG_LIST.add("http://pacnmzg6f.bkt.clouddn.com/wordp.png");
        WORD_IMG_LIST.add("http://pacnmzg6f.bkt.clouddn.com/wordq.png");
        WORD_IMG_LIST.add("http://pacnmzg6f.bkt.clouddn.com/wordr.png");
        WORD_IMG_LIST.add("http://pacnmzg6f.bkt.clouddn.com/words.png");
        WORD_IMG_LIST.add("http://pacnmzg6f.bkt.clouddn.com/wordt.png");
        WORD_IMG_LIST.add("http://pacnmzg6f.bkt.clouddn.com/wordu.png");
        WORD_IMG_LIST.add("http://pacnmzg6f.bkt.clouddn.com/wordv.png");
        WORD_IMG_LIST.add("http://pacnmzg6f.bkt.clouddn.com/wordw.png");
        WORD_IMG_LIST.add("http://pacnmzg6f.bkt.clouddn.com/wordx.png");
        WORD_IMG_LIST.add("http://pacnmzg6f.bkt.clouddn.com/wordy.png");
        WORD_IMG_LIST.add("http://pacnmzg6f.bkt.clouddn.com/wordz.png");



        WORD_ICON_LIST.add(R.mipmap.aicon);
        WORD_ICON_LIST.add(R.mipmap.bicon);
        WORD_ICON_LIST.add(R.mipmap.cicon);
        WORD_ICON_LIST.add(R.mipmap.dicon);
        WORD_ICON_LIST.add(R.mipmap.eicon);
        WORD_ICON_LIST.add(R.mipmap.ficon);
        WORD_ICON_LIST.add(R.mipmap.gicon);
        WORD_ICON_LIST.add(R.mipmap.hicon);
        WORD_ICON_LIST.add(R.mipmap.iicon);
        WORD_ICON_LIST.add(R.mipmap.jicon);
        WORD_ICON_LIST.add(R.mipmap.kicon);
        WORD_ICON_LIST.add(R.mipmap.licon);
        WORD_ICON_LIST.add(R.mipmap.micon);
        WORD_ICON_LIST.add(R.mipmap.nicon);
        WORD_ICON_LIST.add(R.mipmap.oicon);
        WORD_ICON_LIST.add(R.mipmap.picon);
        WORD_ICON_LIST.add(R.mipmap.qicon);
        WORD_ICON_LIST.add(R.mipmap.ricon);
        WORD_ICON_LIST.add(R.mipmap.sicon);
        WORD_ICON_LIST.add(R.mipmap.ticon);
        WORD_ICON_LIST.add(R.mipmap.uicon);
        WORD_ICON_LIST.add(R.mipmap.vicon);
        WORD_ICON_LIST.add(R.mipmap.wicon);
        WORD_ICON_LIST.add(R.mipmap.xicon);
        WORD_ICON_LIST.add(R.mipmap.yicon);
        WORD_ICON_LIST.add(R.mipmap.zicon);







    }



}
