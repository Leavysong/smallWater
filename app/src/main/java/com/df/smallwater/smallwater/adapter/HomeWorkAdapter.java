package com.df.smallwater.smallwater.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.df.smallwater.smallwater.R;
import com.df.smallwater.smallwater.bean.Homework;

import java.util.List;

/**
 * Created by DF on 2018/4/10.
 */

public class HomeWorkAdapter extends BaseQuickAdapter<Homework.HomeworkBean, BaseViewHolder> {




    public HomeWorkAdapter(int layoutResId, @Nullable List<Homework.HomeworkBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Homework.HomeworkBean item) {
        String shuxue = item.getShuxue().replace("_","  、  ");
        String yingyu = item.getYingyu().replace("_","  、  ");

        String yuwen = item.getYuwen().replace("_","  、  ");


        helper.setText(R.id.homework_yuwen,yuwen);
        helper.setText(R.id.homework_yingyu,yingyu);
        helper.setText(R.id.homework_shuxue,shuxue);
        helper.addOnClickListener(R.id.rl_yuwen);
        helper.addOnClickListener(R.id.rl_yingyu);
        helper.addOnClickListener(R.id.rl_shuxue);

    }
}
