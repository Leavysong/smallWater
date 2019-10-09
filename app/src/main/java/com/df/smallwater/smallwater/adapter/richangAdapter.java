package com.df.smallwater.smallwater.adapter;

import android.media.Image;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.df.smallwater.smallwater.R;
import com.df.smallwater.smallwater.bean.Richang;

import java.util.List;

/**
 * Created by DF on 2018/4/10.
 */

public class richangAdapter extends BaseQuickAdapter<Richang.RichanglistBean, BaseViewHolder> {




    public richangAdapter(int layoutResId, @Nullable List<Richang.RichanglistBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Richang.RichanglistBean item) {
        ImageView iv = helper.getView(R.id.richang_image);
        Glide.with(mContext).load(item.getPic()).into(iv);

        helper.setText(R.id.richang_title,item.getTitle());
        helper.setText(R.id.tv_time,item.getTime());
    }
}
