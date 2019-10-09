package com.df.smallwater.smallwater.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.df.smallwater.smallwater.R;
import com.df.smallwater.smallwater.bean.Gonggao;

import java.util.List;

/**
 * Created by DF on 2018/4/10.
 */

public class gonggaoAdapter extends BaseQuickAdapter<Gonggao.GonggaolistBean, BaseViewHolder> {




    public gonggaoAdapter(int layoutResId, @Nullable List<Gonggao.GonggaolistBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Gonggao.GonggaolistBean item) {
        helper.setText(R.id.gonggao_title,item.getContent());
        helper.setText(R.id.gonggao_time,item.getTime());
    }
}
