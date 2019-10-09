package com.df.smallwater.smallwater.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.df.smallwater.smallwater.R;
import com.df.smallwater.smallwater.bean.Gonggao;
import com.df.smallwater.smallwater.bean.Hanzi;

import java.util.List;

/**
 * Created by DF on 2018/4/10.
 */

public class hanziAdapter extends BaseQuickAdapter<String, BaseViewHolder> {




    public hanziAdapter(int layoutResId, @Nullable List<String> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {


        helper.setText(R.id.tv_zi,"第"+helper.getPosition()+"课  识字"+item);
    }
}
