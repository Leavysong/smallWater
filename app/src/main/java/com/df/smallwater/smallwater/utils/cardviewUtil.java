package com.df.smallwater.smallwater.utils;

import android.support.v7.widget.CardView;

/**
 * Created by DF on 2018/6/15.
 */

public class cardviewUtil {


    public static CardView setCard(CardView cardview){


        cardview.setRadius(35);//设置图片圆角的半径大小

        cardview.setCardElevation(20);//设置阴影部分大小

        cardview.setContentPadding(0,0,0,0);//设置图片距离阴影大小


        return cardview;
    }
}
