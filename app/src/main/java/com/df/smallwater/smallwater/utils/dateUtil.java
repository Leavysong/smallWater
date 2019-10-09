package com.df.smallwater.smallwater.utils;

import com.df.smallwater.smallwater.bean.Hanzi;
import com.df.smallwater.smallwater.bean.Title;
import com.df.smallwater.smallwater.bean.Word;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DF on 2018/6/9.
 */

public class dateUtil {


    private static List<Title> l = new ArrayList<>();

    public static List<Title> gethanzilist(List<Hanzi.HanzilistBean> list){
        l = new ArrayList<>();
        Title result = new Title();
        int count = 0 ;
        for (int i = 0 ; i < list.size() ; i++){
            if(count<6){
                if(i==list.size()-1){
                    result.setTitle(result.getTitle() + list.get(i).getZi() + "。");
                    result.setPic(list.get(i).getPic());
                    l.add(result);
                }else {
                    if (count == 5) {
                        result.setTitle(result.getTitle() + list.get(i).getZi() + "。");
                        result.setPic(list.get(i).getPic());
                    } else {
                        result.setTitle(result.getTitle() + list.get(i).getZi() + "、");
                        result.setPic(list.get(i).getPic());
                    }
                }

            }else{
                l.add(result);
                result = new Title();
                result.setTitle(result.getTitle() + list.get(i).getZi() + "、");
                count = 0 ;
            }
            count ++ ;
        }

        return l;
    }

    public static List<Title> getwordlist(List<Word.WordlistBean> list){
        l = new ArrayList<>();
        Title result = new Title();
        int count = 0 ;
        for (int i = 0 ; i < list.size() ; i++){
            if(count<6){
                if(i==list.size()-1){
                    result.setTitle(result.getTitle() + list.get(i).getWord() + "。");
                    result.setPic(list.get(i).getPic());
                    l.add(result);
                }else {
                    if (count == 5) {
                        result.setTitle(result.getTitle() + list.get(i).getWord() + "。");
                        result.setPic(list.get(i).getPic());
                    } else {
                        result.setTitle(result.getTitle() + list.get(i).getWord() + "、");
                        result.setPic(list.get(i).getPic());
                    }
                }

            }else{
                l.add(result);
                result = new Title();
                result.setTitle(result.getTitle() + list.get(i).getWord() + "、");
                count = 0 ;
            }
            count ++ ;
        }

        return l;
    }
}
