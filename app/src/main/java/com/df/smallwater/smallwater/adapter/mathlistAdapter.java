package com.df.smallwater.smallwater.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.df.smallwater.smallwater.R;
import com.df.smallwater.smallwater.bean.Title;

import java.util.List;

/**
 * Created by DF on 2018/4/10.
 */


public class mathlistAdapter extends BaseQuickAdapter<Title, BaseViewHolder> {


Context context ;

    public mathlistAdapter(int layoutResId, @Nullable List<Title> data , Context c) {
        super(layoutResId, data);
        this.context = c ;
    }

    @Override
    protected void convert(BaseViewHolder helper, Title item) {

        String title = item.getTitle().substring(0,item.getTitle().indexOf(" "));
        String content = item.getTitle().substring(item.getTitle().indexOf(" "),item.getTitle().length());
        helper.setText(R.id.tv_hanzi_title,title);
        helper.setText(R.id.tv_hanzi_content,content);

        ImageView iv = helper.getView(R.id.iv_hanzi_img);

        Glide.with(context).load(item.getPic()).into(iv);

        CardView cardView  = helper.getView(R.id.cardview);

        cardView.setRadius(35);//设置图片圆角的半径大小

        cardView.setCardElevation(20);//设置阴影部分大小

        cardView.setContentPadding(0,0,0,0);//设置图片距离阴影大小
    }
}
