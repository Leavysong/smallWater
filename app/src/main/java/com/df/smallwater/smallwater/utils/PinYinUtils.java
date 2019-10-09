package com.df.smallwater.smallwater.utils;

import com.df.smallwater.smallwater.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by DF on 2018/4/17.
 */

public class PinYinUtils {

    private static PinYinUtils p ;

    public static List<String> SHENGMU_LIST = new ArrayList<>();
    public static List<Integer> SHENGMU_RES_LIST = new ArrayList<>();
    public static List<String> SHENGMUCARD_RES_LIST = new ArrayList<>();
    public static List<Integer> SHENGMU_TALK_LIST = new ArrayList<>();
    public static List<String> All_PINYIN_LIST = new ArrayList<>();


    public static List<String> YUNMU_LIST = new ArrayList<>();
    public static List<Integer> YUNMU_RES_LIST = new ArrayList<>();
    public static List<String> YUNMUCARD_RES_LIST = new ArrayList<>();
    public static List<Integer> YUNMU_TALK_LIST = new ArrayList<>();

    public static Map<String,Integer> PINYIN_LIST = new HashMap<>();
    public static Map<String,String> PINYIN_CARD_LIST = new HashMap<>();
    public static Map<String,Integer> PINYIN_TALK_LIST = new HashMap<>();
    public static Map<String,Integer > All_ICON_LIST = new HashMap<>();

    public static synchronized PinYinUtils getInstance(){
        if(p==null){
            init();
            p = new PinYinUtils();
            return p ;
        }else{
            return p ;
        }
    }


    private PinYinUtils(){

    }


    public List<String> getSHENGMU_LIST() {
        return SHENGMU_LIST;
    }

    public void setSHENGMU_LIST(List<String> SHENGMU_LIST) {
        this.SHENGMU_LIST = SHENGMU_LIST;
    }

    public List<Integer> getSHENGMU_RES_LIST() {
        return SHENGMU_RES_LIST;
    }

    public void setSHENGMU_RES_LIST(List<Integer> SHENGMU_RES_LIST) {
        this.SHENGMU_RES_LIST = SHENGMU_RES_LIST;
    }

    public List<String> getSHENGMUCARD_RES_LIST() {
        return SHENGMUCARD_RES_LIST;
    }

    public void setSHENGMUCARD_RES_LIST(List<String> SHENGMUCARD_RES_LIST) {
        this.SHENGMUCARD_RES_LIST = SHENGMUCARD_RES_LIST;
    }

    public List<Integer> getSHENGMU_TALK_LIST() {
        return SHENGMU_TALK_LIST;
    }

    public void setSHENGMU_TALK_LIST(List<Integer> SHENGMU_TALK_LIST) {
        this.SHENGMU_TALK_LIST = SHENGMU_TALK_LIST;
    }

    public List<String> getYUNMU_LIST() {
        return YUNMU_LIST;
    }

    public void setYUNMU_LIST(List<String> YUNMU_LIST) {
        this.YUNMU_LIST = YUNMU_LIST;
    }

    public List<Integer> getYUNMU_RES_LIST() {
        return YUNMU_RES_LIST;
    }

    public void setYUNMU_RES_LIST(List<Integer> YUNMU_RES_LIST) {
        this.YUNMU_RES_LIST = YUNMU_RES_LIST;
    }

    public List<String> getYUNMUCARD_RES_LIST() {
        return YUNMUCARD_RES_LIST;
    }

    public void setYUNMUCARD_RES_LIST(List<String> YUNMUCARD_RES_LIST) {
        this.YUNMUCARD_RES_LIST = YUNMUCARD_RES_LIST;
    }

    public List<Integer> getYUNMU_TALK_LIST() {
        return YUNMU_TALK_LIST;
    }

    public void setYUNMU_TALK_LIST(List<Integer> YUNMU_TALK_LIST) {
        this.YUNMU_TALK_LIST = YUNMU_TALK_LIST;
    }

    public Map<String, Integer> getPINYIN_LIST() {
        return PINYIN_LIST;
    }

    public void setPINYIN_LIST(Map<String, Integer> PINYIN_LIST) {
        this.PINYIN_LIST = PINYIN_LIST;
    }

    public Map<String, String> getPINYIN_CARD_LIST() {
        return PINYIN_CARD_LIST;
    }

    public void setPINYIN_CARD_LIST(Map<String, String> PINYIN_CARD_LIST) {
        this.PINYIN_CARD_LIST = PINYIN_CARD_LIST;
    }

    public Map<String, Integer> getPINYIN_TALK_LIST() {
        return PINYIN_TALK_LIST;
    }

    public void setPINYIN_TALK_LIST(Map<String, Integer> PINYIN_TALK_LIST) {
        this.PINYIN_TALK_LIST = PINYIN_TALK_LIST;
    }

    public List<String> getAll_PINYIN_LIST() {
        return All_PINYIN_LIST;
    }

    public void setAll_PINYIN_LIST(List<String> all_PINYIN_LIST) {
        All_PINYIN_LIST = all_PINYIN_LIST;
    }

    public static void init(){
        SHENGMU_LIST.add("b");
        SHENGMU_LIST.add("p");
        SHENGMU_LIST.add("m");
        SHENGMU_LIST.add("f");

        SHENGMU_LIST.add("d");
        SHENGMU_LIST.add("t");
        SHENGMU_LIST.add("n");
        SHENGMU_LIST.add("l");

        SHENGMU_LIST.add("g");
        SHENGMU_LIST.add("k");
        SHENGMU_LIST.add("h");
        SHENGMU_LIST.add("j");

        SHENGMU_LIST.add("q");
        SHENGMU_LIST.add("x");
        SHENGMU_LIST.add("zh");
        SHENGMU_LIST.add("ch");

        SHENGMU_LIST.add("sh");
        SHENGMU_LIST.add("z");
        SHENGMU_LIST.add("c");
        SHENGMU_LIST.add("s");

        SHENGMU_LIST.add("r");
        SHENGMU_LIST.add("y");
        SHENGMU_LIST.add("w");

        SHENGMU_RES_LIST.add(R.mipmap.xb);
        SHENGMU_RES_LIST.add(R.mipmap.xp);
        SHENGMU_RES_LIST.add(R.mipmap.xm);
        SHENGMU_RES_LIST.add(R.mipmap.xf);

        SHENGMU_RES_LIST.add(R.mipmap.xd);
        SHENGMU_RES_LIST.add(R.mipmap.xt);
        SHENGMU_RES_LIST.add(R.mipmap.xn);
        SHENGMU_RES_LIST.add(R.mipmap.xl);

        SHENGMU_RES_LIST.add(R.mipmap.xg);
        SHENGMU_RES_LIST.add(R.mipmap.xk);
        SHENGMU_RES_LIST.add(R.mipmap.xh);
        SHENGMU_RES_LIST.add(R.mipmap.xj);

        SHENGMU_RES_LIST.add(R.mipmap.xq);
        SHENGMU_RES_LIST.add(R.mipmap.xx);
        SHENGMU_RES_LIST.add(R.mipmap.xzh);
        SHENGMU_RES_LIST.add(R.mipmap.xch);

        SHENGMU_RES_LIST.add(R.mipmap.xsh);
        SHENGMU_RES_LIST.add(R.mipmap.xz);
        SHENGMU_RES_LIST.add(R.mipmap.xc);
        SHENGMU_RES_LIST.add(R.mipmap.xs);

        SHENGMU_RES_LIST.add(R.mipmap.xr);
        SHENGMU_RES_LIST.add(R.mipmap.xy);
        SHENGMU_RES_LIST.add(R.mipmap.xw);

        SHENGMUCARD_RES_LIST.add("http://pacnmzg6f.bkt.clouddn.com/bcard.png");
        SHENGMUCARD_RES_LIST.add("http://pacnmzg6f.bkt.clouddn.com/pcard.png");
        SHENGMUCARD_RES_LIST.add("http://pacnmzg6f.bkt.clouddn.com/mcard.png");
        SHENGMUCARD_RES_LIST.add("http://pacnmzg6f.bkt.clouddn.com/fcard.png");

        SHENGMUCARD_RES_LIST.add("http://pacnmzg6f.bkt.clouddn.com/dcard.png");
        SHENGMUCARD_RES_LIST.add("http://pacnmzg6f.bkt.clouddn.com/tcard.png");
        SHENGMUCARD_RES_LIST.add("http://pacnmzg6f.bkt.clouddn.com/ncard.png");
        SHENGMUCARD_RES_LIST.add("http://pacnmzg6f.bkt.clouddn.com/lcard.png");

        SHENGMUCARD_RES_LIST.add("http://pacnmzg6f.bkt.clouddn.com/gcard.png");
        SHENGMUCARD_RES_LIST.add("http://pacnmzg6f.bkt.clouddn.com/kcard.png");
        SHENGMUCARD_RES_LIST.add("http://pacnmzg6f.bkt.clouddn.com/hcard.png");
        SHENGMUCARD_RES_LIST.add("http://pacnmzg6f.bkt.clouddn.com/jcard.png");

        SHENGMUCARD_RES_LIST.add("http://pacnmzg6f.bkt.clouddn.com/qcard.png");
        SHENGMUCARD_RES_LIST.add("http://pacnmzg6f.bkt.clouddn.com/xcard.png");
        SHENGMUCARD_RES_LIST.add("http://pacnmzg6f.bkt.clouddn.com/zhcard.png");
        SHENGMUCARD_RES_LIST.add("http://pacnmzg6f.bkt.clouddn.com/chcard.png");

        SHENGMUCARD_RES_LIST.add("http://pacnmzg6f.bkt.clouddn.com/shcard.png");
        SHENGMUCARD_RES_LIST.add("http://pacnmzg6f.bkt.clouddn.com/zcard.png");
        SHENGMUCARD_RES_LIST.add("http://pacnmzg6f.bkt.clouddn.com/ccard.png");
        SHENGMUCARD_RES_LIST.add("http://pacnmzg6f.bkt.clouddn.com/scard.png");

        SHENGMUCARD_RES_LIST.add("http://pacnmzg6f.bkt.clouddn.com/rcard.png");
        SHENGMUCARD_RES_LIST.add("http://pacnmzg6f.bkt.clouddn.com/ycard.png");
        SHENGMUCARD_RES_LIST.add("http://pacnmzg6f.bkt.clouddn.com/wcard.png");

        SHENGMU_TALK_LIST.add(R.raw.b);
        SHENGMU_TALK_LIST.add(R.raw.p);
        SHENGMU_TALK_LIST.add(R.raw.m);
        SHENGMU_TALK_LIST.add(R.raw.f);

        SHENGMU_TALK_LIST.add(R.raw.d);
        SHENGMU_TALK_LIST.add(R.raw.t);
        SHENGMU_TALK_LIST.add(R.raw.n);
        SHENGMU_TALK_LIST.add(R.raw.l);

        SHENGMU_TALK_LIST.add(R.raw.g);
        SHENGMU_TALK_LIST.add(R.raw.k);
        SHENGMU_TALK_LIST.add(R.raw.h);
        SHENGMU_TALK_LIST.add(R.raw.j);

        SHENGMU_TALK_LIST.add(R.raw.q);
        SHENGMU_TALK_LIST.add(R.raw.x);
        SHENGMU_TALK_LIST.add(R.raw.zh);
        SHENGMU_TALK_LIST.add(R.raw.ch);

        SHENGMU_TALK_LIST.add(R.raw.sh);
        SHENGMU_TALK_LIST.add(R.raw.z);
        SHENGMU_TALK_LIST.add(R.raw.c);
        SHENGMU_TALK_LIST.add(R.raw.s);

        SHENGMU_TALK_LIST.add(R.raw.r);
        SHENGMU_TALK_LIST.add(R.raw.y);
        SHENGMU_TALK_LIST.add(R.raw.w);

        YUNMU_LIST.add("a");
        YUNMU_LIST.add("o");
        YUNMU_LIST.add("e");
        YUNMU_LIST.add("i");

        YUNMU_LIST.add("u");
        YUNMU_LIST.add("v");
        YUNMU_LIST.add("ai");
        YUNMU_LIST.add("ei");

        YUNMU_LIST.add("ui");
        YUNMU_LIST.add("ao");
        YUNMU_LIST.add("ou");
        YUNMU_LIST.add("iu");

        YUNMU_LIST.add("ie");
        YUNMU_LIST.add("ve");
        YUNMU_LIST.add("er");
        YUNMU_LIST.add("an");

        YUNMU_LIST.add("en");
        YUNMU_LIST.add("in");
        YUNMU_LIST.add("un");
        YUNMU_LIST.add("vn");

        YUNMU_LIST.add("ang");
        YUNMU_LIST.add("eng");
        YUNMU_LIST.add("ing");
        YUNMU_LIST.add("ong");


        YUNMU_RES_LIST.add(R.mipmap.xa);
        YUNMU_RES_LIST.add(R.mipmap.xo);
        YUNMU_RES_LIST.add(R.mipmap.xe);
        YUNMU_RES_LIST.add(R.mipmap.xi);

        YUNMU_RES_LIST.add(R.mipmap.xu);
        YUNMU_RES_LIST.add(R.mipmap.xv);
        YUNMU_RES_LIST.add(R.mipmap.xai);
        YUNMU_RES_LIST.add(R.mipmap.xei);

        YUNMU_RES_LIST.add(R.mipmap.xui);
        YUNMU_RES_LIST.add(R.mipmap.xao);
        YUNMU_RES_LIST.add(R.mipmap.xou);
        YUNMU_RES_LIST.add(R.mipmap.xiu);

        YUNMU_RES_LIST.add(R.mipmap.xie);
        YUNMU_RES_LIST.add(R.mipmap.xve);
        YUNMU_RES_LIST.add(R.mipmap.xer);
        YUNMU_RES_LIST.add(R.mipmap.xan);

        YUNMU_RES_LIST.add(R.mipmap.xen);
        YUNMU_RES_LIST.add(R.mipmap.xin);
        YUNMU_RES_LIST.add(R.mipmap.xun);
        YUNMU_RES_LIST.add(R.mipmap.xvn);

        YUNMU_RES_LIST.add(R.mipmap.xang);
        YUNMU_RES_LIST.add(R.mipmap.xeng);
        YUNMU_RES_LIST.add(R.mipmap.xing);
        YUNMU_RES_LIST.add(R.mipmap.xong);


        YUNMUCARD_RES_LIST.add("http://pacnmzg6f.bkt.clouddn.com/acard.png");
        YUNMUCARD_RES_LIST.add("http://pacnmzg6f.bkt.clouddn.com/ocard.png");
        YUNMUCARD_RES_LIST.add("http://pacnmzg6f.bkt.clouddn.com/ecard.png");
        YUNMUCARD_RES_LIST.add("http://pacnmzg6f.bkt.clouddn.com/icard.png");

        YUNMUCARD_RES_LIST.add("http://pacnmzg6f.bkt.clouddn.com/ucard.png");
        YUNMUCARD_RES_LIST.add("http://pacnmzg6f.bkt.clouddn.com/vcard.png");
        YUNMUCARD_RES_LIST.add("http://pacnmzg6f.bkt.clouddn.com/aicard.png");
        YUNMUCARD_RES_LIST.add("http://pacnmzg6f.bkt.clouddn.com/eicard.png");

        YUNMUCARD_RES_LIST.add("http://pacnmzg6f.bkt.clouddn.com/uicard.png");
        YUNMUCARD_RES_LIST.add("http://pacnmzg6f.bkt.clouddn.com/aocard.png");
        YUNMUCARD_RES_LIST.add("http://pacnmzg6f.bkt.clouddn.com/oucard.png");
        YUNMUCARD_RES_LIST.add("http://pacnmzg6f.bkt.clouddn.com/iucard.png");

        YUNMUCARD_RES_LIST.add("http://pacnmzg6f.bkt.clouddn.com/iecard.png");
        YUNMUCARD_RES_LIST.add("http://pacnmzg6f.bkt.clouddn.com/vecard.png");
        YUNMUCARD_RES_LIST.add("http://pacnmzg6f.bkt.clouddn.com/ercard.png");
        YUNMUCARD_RES_LIST.add("http://pacnmzg6f.bkt.clouddn.com/ancard.png");

        YUNMUCARD_RES_LIST.add("http://pacnmzg6f.bkt.clouddn.com/encard.png");
        YUNMUCARD_RES_LIST.add("http://pacnmzg6f.bkt.clouddn.com/incard.png");
        YUNMUCARD_RES_LIST.add("http://pacnmzg6f.bkt.clouddn.com/uncard.png");
        YUNMUCARD_RES_LIST.add("http://pacnmzg6f.bkt.clouddn.com/vncard.png");

        YUNMUCARD_RES_LIST.add("http://pacnmzg6f.bkt.clouddn.com/angcard.png");
        YUNMUCARD_RES_LIST.add("http://pacnmzg6f.bkt.clouddn.com/engcard.png");
        YUNMUCARD_RES_LIST.add("http://pacnmzg6f.bkt.clouddn.com/ingcard.png");
        YUNMUCARD_RES_LIST.add("http://pacnmzg6f.bkt.clouddn.com/ongcard.png");

        YUNMU_TALK_LIST.add(R.raw.a);
        YUNMU_TALK_LIST.add(R.raw.o);
        YUNMU_TALK_LIST.add(R.raw.e);
        YUNMU_TALK_LIST.add(R.raw.i);

        YUNMU_TALK_LIST.add(R.raw.u);
        YUNMU_TALK_LIST.add(R.raw.v);
        YUNMU_TALK_LIST.add(R.raw.ai);
        YUNMU_TALK_LIST.add(R.raw.ei);

        YUNMU_TALK_LIST.add(R.raw.ui);
        YUNMU_TALK_LIST.add(R.raw.ao);
        YUNMU_TALK_LIST.add(R.raw.ou);
        YUNMU_TALK_LIST.add(R.raw.iu);

        YUNMU_TALK_LIST.add(R.raw.ie);
        YUNMU_TALK_LIST.add(R.raw.ve);
        YUNMU_TALK_LIST.add(R.raw.er);
        YUNMU_TALK_LIST.add(R.raw.an);

        YUNMU_TALK_LIST.add(R.raw.en);
        YUNMU_TALK_LIST.add(R.raw.in);
        YUNMU_TALK_LIST.add(R.raw.un);
        YUNMU_TALK_LIST.add(R.raw.vn);

        YUNMU_TALK_LIST.add(R.raw.ang);
        YUNMU_TALK_LIST.add(R.raw.eng);
        YUNMU_TALK_LIST.add(R.raw.ing);
        YUNMU_TALK_LIST.add(R.raw.ong);


        for (int i  = 0 ; i < SHENGMU_LIST.size() ; i ++){
            PINYIN_LIST.put(SHENGMU_LIST.get(i),SHENGMU_RES_LIST.get(i));
        }

        for (int i  = 0 ; i < YUNMU_LIST.size() ; i ++){
            PINYIN_LIST.put(YUNMU_LIST.get(i),YUNMU_RES_LIST.get(i));
        }



        for (int i  = 0 ; i < SHENGMU_LIST.size() ; i ++){
            PINYIN_CARD_LIST.put(SHENGMU_LIST.get(i),SHENGMUCARD_RES_LIST.get(i));
        }

        for (int i  = 0 ; i < YUNMU_LIST.size() ; i ++){
            PINYIN_CARD_LIST.put(YUNMU_LIST.get(i),YUNMUCARD_RES_LIST.get(i));
        }


        for (int i  = 0 ; i < SHENGMU_LIST.size() ; i ++){
            PINYIN_TALK_LIST.put(SHENGMU_LIST.get(i),SHENGMU_TALK_LIST.get(i));
        }

        for (int i  = 0 ; i < YUNMU_LIST.size() ; i ++){
            PINYIN_TALK_LIST.put(YUNMU_LIST.get(i),YUNMU_TALK_LIST.get(i));
        }

        for (int i  = 0 ; i < YUNMU_LIST.size() ; i ++){
            All_PINYIN_LIST.add(YUNMU_LIST.get(i));
        }

        for (int i  = 0 ; i < SHENGMU_LIST.size() ; i ++){
            All_PINYIN_LIST.add(SHENGMU_LIST.get(i));
        }


        for (int i  = 0 ; i < SHENGMU_LIST.size() ; i ++){
            PINYIN_LIST.put(SHENGMU_LIST.get(i),SHENGMU_RES_LIST.get(i));
        }

        for (int i  = 0 ; i < YUNMU_LIST.size() ; i ++){
            PINYIN_LIST.put(YUNMU_LIST.get(i),YUNMU_RES_LIST.get(i));
        }



    }
}
