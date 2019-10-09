package com.df.smallwater.smallwater.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.df.smallwater.smallwater.R;
import com.df.smallwater.smallwater.bean.Zhuangkuang;

import java.util.List;

/**
 * Created by DF on 2018/4/10.
 */

public class zhuangkuangAdapter extends BaseQuickAdapter<Zhuangkuang.ZhuangkuanglistBean, BaseViewHolder> {




    public zhuangkuangAdapter(int layoutResId, @Nullable List<Zhuangkuang.ZhuangkuanglistBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Zhuangkuang.ZhuangkuanglistBean item) {
        helper.setText(R.id.zhuangkuang_title,item.getContent());
        helper.setText(R.id.zhuangkuang_time,item.getTime());
    }
}
