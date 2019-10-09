package com.df.smallwater.smallwater.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.df.smallwater.smallwater.R;
import com.df.smallwater.smallwater.bean.Game;

import java.util.List;

/**
 * Created by DF on 2018/6/23.
 */

public class GameAdapter extends BaseQuickAdapter<Game, BaseViewHolder> {

    public GameAdapter(int layoutResId, @Nullable List<Game> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Game item) {

        helper.setText(R.id.tv_title,item.getName());
        helper.setText(R.id.tv_content,item.getContent());
        helper.setImageResource(R.id.iv_img,item.getImg());
    }
}
