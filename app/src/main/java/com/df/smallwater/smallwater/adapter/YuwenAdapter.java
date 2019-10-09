package com.df.smallwater.smallwater.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.df.smallwater.smallwater.R;
import com.df.smallwater.smallwater.bean.Zidian;

import java.util.List;

/**
 * Created by DF on 2018/4/10.
 */

public class YuwenAdapter extends BaseQuickAdapter<Zidian.ZidianBean, BaseViewHolder> {




    public YuwenAdapter(int layoutResId, @Nullable List<Zidian.ZidianBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Zidian.ZidianBean item) {


        helper.setText(R.id.tv_zi,item.getZi());
        helper.setText(R.id.tv_bihua,"笔画 ： "+item.getBihua());
        helper.setText(R.id.tv_bushou,"部首 ： "+item.getBushou());
        helper.setText(R.id.tv_pinyin,"拼音 ： "+item.getPinyin());

    }
}
