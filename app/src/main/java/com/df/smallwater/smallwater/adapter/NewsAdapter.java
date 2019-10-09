package com.df.smallwater.smallwater.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.df.smallwater.smallwater.R;
import com.df.smallwater.smallwater.api.ApiConstants;
import com.df.smallwater.smallwater.bean.Xinwen;

import java.util.List;

/**
 * Created by DF on 2018/4/10.
 */

public class NewsAdapter extends BaseQuickAdapter<Xinwen.XinwenlistBean, BaseViewHolder> {




    public NewsAdapter(int layoutResId, @Nullable List<Xinwen.XinwenlistBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Xinwen.XinwenlistBean item) {


        ImageView iv_image = helper.getView(R.id.news_image);
        Glide.with(mContext).load(item.getPic()).into(iv_image);
        helper.setText(R.id.news_title,item.getTitle());
        helper.setText(R.id.tv_time,item.getTime());
    }
}
